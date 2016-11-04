//package com.siby.generators;
//
//import com.google.common.io.Files;
//import com.google.common.io.Resources;
//import com.google.gson.Gson;
//import com.mifmif.common.regex.Generex;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.Map;
//
//import static com.google.common.base.Charsets.UTF_8;
//
///**
// * Generates random json from a given JSON schema location
// */
//class JsonFromSchemaGenerator implements Generator<String> {
//
//    private final String pathToJsonSchema;
//    private final Gson gson = new Gson();
//
//
//    JsonFromSchemaGenerator(String pathToJsonSchema) {
//        this.pathToJsonSchema = pathToJsonSchema;
//    }
//
//    public String next() {
//        String jsonContext;
//        try {
//            jsonContext = getJsonContext();
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        final Map jsonObj = gson.fromJson(jsonContext, Map.class);
//
//        StringBuilder sb = new StringBuilder();
//        if (jsonObj.containsKey("type")) {
//            sb.append(processSchemaJsonObjectForType(jsonObj));
//        }
//        return sb.toString();
//    }
//
//    private String processSchemaJsonObjectForType(Map jsonObj) {
//
//        StringBuilder sb = new StringBuilder();
//
//        final String type = (String) jsonObj.get("type");
//
//        switch (type) {
//            case "object":
//                sb.append('{');
//                if (jsonObj.containsKey("required")) {
//                    final List<String> required = (List<String>) jsonObj.get("required");
//
//                    if (!required.isEmpty()) {
//                        assert (jsonObj.containsKey("properties"));
//                        final Map childJsonObj = (Map) jsonObj.get("properties");
//                        required.forEach(s -> {
//                            assert childJsonObj.containsKey(s);
//                        });
//
//                    }
//                }
//
//                final Map childJsonObj = (Map) jsonObj.get("properties");
//                for (Object key : childJsonObj.keySet()) {
//                    sb.append(String.format("\"%s\":", key));
//                    final Map value = (Map) childJsonObj.get(key);
//                    if (value.containsKey("type")) {
//                        processSchemaJsonObjectForType(value);
//                    } else if (value.containsKey("enum")) {
//                        processSchemaJsonObjectForEnum(value);
//                    }
//                    sb.append("},");
//                }
//                sb.append('}');
//                break;
//            case "string":
//                String value = null;
//                if (jsonObj.containsKey("pattern")) {
//                    final String pattern = (String) jsonObj.get("pattern");
//                    Generex generex = new Generex(pattern);
//
//                    // Generate random String
//                    value = generex.random();
//                    System.out.format("%nPattern:%s, String:%s", pattern, randomStr);
//                } else {
//                    System.out.println(Random.string.next());
//                }
//                sb.append
//                break;
//            case "integer":
//            case "number":
//                int minimum = Integer.MIN_VALUE, maximum = Integer.MAX_VALUE;
//
//                if (jsonObj.containsKey("minimum")) {
//                    minimum = ((Double) jsonObj.get("minimum")).intValue();
//                }
//                if (jsonObj.containsKey("maximum")) {
//                    maximum = ((Double) jsonObj.get("maximum")).intValue();
//                }
//                System.out.println(IntegerGenerator.IntegerGeneratorBuilder.instance().min(minimum).max(maximum).build().next());
//                break;
//            case "array":
//                Boolean uniqueItems = null;
//                if (jsonObj.containsKey("uniqueItems")) {
//                    uniqueItems = (Boolean) jsonObj.get("uniqueItems");
//                }
//                Integer minItems = null;
//                if (jsonObj.containsKey("minItems")) {
//                    minItems = ((Double) jsonObj.get("minItems")).intValue();
//                }
//                System.out.println(uniqueItems + "" + minItems);
//                Map items = (Map) jsonObj.get("items");
//                processSchemaJsonObjectForType(items);
//                break;
//            default:
//                throw new RuntimeException("Unsupported JSON Schema Type:" + type);
//
//        }
//    }
//
//    private void processSchemaJsonObjectForEnum(Map jsonObj) {
//        List<String> items = (List<String>) jsonObj.get("enum");
//        System.out.println(Random.values(items).next());
//    }
//
//    private String getJsonContext() throws IOException {
//        if (Paths.get(pathToJsonSchema).isAbsolute()) {
//            return Files.toString(new File(pathToJsonSchema), UTF_8);
//        } else {
//            return Resources.toString(Resources.getResource(pathToJsonSchema), UTF_8);
//        }
//    }
//}
