package com.siby.generators;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.mifmif.common.regex.Generex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Charsets.UTF_8;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.util.stream.Collectors.joining;

/**
 * Generates random json from a given JSON schema location
 */
class JsonFromSchemaGenerator implements Generator<String> {

    private final String pathToJsonSchema;
    private final Gson gson = new Gson();
    private final Map<String, String> definitionsInJson = new HashMap<>();

    JsonFromSchemaGenerator(String pathToJsonSchema) {
        this.pathToJsonSchema = pathToJsonSchema;
    }

    public String next() {
        final Map jsonObj = gson.fromJson(getSchemaContent(), Map.class);
        processSchemaJsonObjectForDefinitions(jsonObj);
        return processSchemaJsonObjectForType(jsonObj);
    }

    private void processSchemaJsonObjectForDefinitions(Map jsonObj) {
        if (!jsonObj.containsKey("definitions")) {
            return;
        }
        final Map definitions = (Map) jsonObj.get("definitions");
        for (Object key : definitions.keySet()) {
            final Map definition = (Map) definitions.get(key);
            final String value = processSchemaJsonObjectForType(definition);
            definitionsInJson.put("#/definitions/" + key, value);
        }
    }

    private String processSchemaJsonObjectForType(Map jsonObj) {

        StringBuilder sb = new StringBuilder();

        final Object typeObj = jsonObj.get("type");

        String type;

        if (typeObj instanceof List) {
            type = anyType((List<String>) typeObj);
        } else {
            type = (String) typeObj;
        }

        switch (type) {
            case "object":
                sb.append(processObject(jsonObj));
                break;
            case "boolean":
                sb.append(processBoolean(jsonObj));
                break;
            case "string":
                sb.append(processString(jsonObj));
                break;
            case "integer":
            case "number":
                sb.append(processInteger(jsonObj));
                break;
            case "array":
                sb.append(processArray(jsonObj));
                break;
            case "null":
                sb.append("null");
                break;
            default:
                throw new RuntimeException("Unsupported JSON Schema Type:" + type);
        }

        return sb.toString();

    }

    private String anyType(List<String> types) {
        int index = RANDOM.nextInt(types.size());
        return types.get(index);
    }

    private String processObject(Map jsonObj) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        if (jsonObj.containsKey("required")) {
            final List<String> required = (List<String>) jsonObj.get("required");

            if (!required.isEmpty()) {
                assert (jsonObj.containsKey("properties"));
                final Map childJsonObj = (Map) jsonObj.get("properties");
                required.forEach(s -> {
                    assert childJsonObj.containsKey(s);
                });

            }
        }

        final Map childJsonObj = (Map) jsonObj.get("properties");
        //TODO generate all required fields and optional fields :)

        sb.append(childJsonObj.keySet().stream().map(key -> format("\"%s\":", key) + processValue(childJsonObj, key)).collect(joining(", ")));
        sb.append('}');

        return sb.toString();
    }

    private String processValue(Map childJsonObj, Object key) {
        final Map value = (Map) childJsonObj.get(key);
        if (value.containsKey("type")) {
            return processSchemaJsonObjectForType(value);
        } else if (value.containsKey("enum")) {
            return processSchemaJsonObjectForEnum(value);
        } else if (value.containsKey("$ref")) {
            return processSchemaJsonObjectForRef(value);
        } else {
            return "";
        }
    }

    private String processArray(Map jsonObj) {
        Boolean uniqueItems = null;
        if (jsonObj.containsKey("uniqueItems")) {
            uniqueItems = (Boolean) jsonObj.get("uniqueItems");
        }
        Integer minItems = null;
        if (jsonObj.containsKey("minItems")) {
            minItems = ((Double) jsonObj.get("minItems")).intValue();
        }

        final Object items = jsonObj.get("items");
        String arrayValue;

        if (items instanceof Map) {
            arrayValue = processSchemaJsonObjectForType((Map) items);
        } else if (items instanceof List) {
            List<Map> listOfItems = (List<Map>) items;
            arrayValue = listOfItems.stream().map(this::processObject).collect(joining(", "));

        } else {
            throw new RuntimeException("Uknown Array Type:" + items);
        }

        return format("[%s]", arrayValue);

    }

    private Integer processInteger(Map jsonObj) {
        int minimum = Integer.MIN_VALUE, maximum = Integer.MAX_VALUE;

        if (jsonObj.containsKey("minimum")) {
            minimum = ((Double) jsonObj.get("minimum")).intValue();
        }
        if (jsonObj.containsKey("maximum")) {
            maximum = ((Double) jsonObj.get("maximum")).intValue();
        }
        return IntegerGenerator.IntegerGeneratorBuilder.instance().min(minimum).max(maximum).build().next();
    }

    private String processString(Map jsonObj) {
        String value;
        if (jsonObj.containsKey("pattern")) {
            final String pattern = (String) jsonObj.get("pattern");
            value = new Generex(pattern).random().replace('^', ' ').replace('$', ' ').trim();
        } else if (jsonObj.containsKey("format")) {
            String format = (String) jsonObj.get("format");
            if (format.equals("email")) {
                value = Random.emailAddress.next();
            } else if (format.equals("date-time")) {
                value = ZonedDateTime.now().format(ISO_DATE_TIME);
            } else {
                value = Random.string.next();
            }
        } else {
            value = Random.string.next();
        }
        return format("\"%s\"", value);
    }

    private boolean processBoolean(Map jsonObj) {
        return Random.booleanVal.next();
    }

    private String processSchemaJsonObjectForEnum(Map jsonObj) {
        List<String> items = (List<String>) jsonObj.get("enum");
        return format("\"%s\"", Random.values(items).next());
    }

    private String processSchemaJsonObjectForRef(Map jsonObj) {
        final String ref = (String) jsonObj.get("$ref");
        return definitionsInJson.get(ref);
    }

    private String getSchemaContent() {
        String schemaContent;
        try {
            if (Paths.get(pathToJsonSchema).isAbsolute()) {
                schemaContent = Files.toString(new File(pathToJsonSchema), UTF_8);
            } else {
                schemaContent = Resources.toString(Resources.getResource(pathToJsonSchema), UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return schemaContent;
    }
}
