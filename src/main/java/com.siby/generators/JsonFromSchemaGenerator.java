package com.siby.generators;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.mifmif.common.regex.Generex;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Charsets.UTF_8;

class JsonFromSchemaGenerator implements Generator<JSONObject> {

    private final String pathToJsonSchema;
    Gson gson = new Gson();


    JsonFromSchemaGenerator(String pathToJsonSchema) {
        this.pathToJsonSchema = pathToJsonSchema;
    }

    public JSONObject next() {
        String jsonContext = null;
        try {
            jsonContext = getJsonContext();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        final Map jsonObj = gson.fromJson(jsonContext, Map.class);
        processSchemaJsonObject(jsonObj);
        return null;
    }

    private void processSchemaJsonObject(Map jsonObj) {
        assert (jsonObj.containsKey("type"));
        final String type = (String) jsonObj.get("type");
        switch (type) {
            case "object":
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
                for (Object keys : childJsonObj.keySet()) {
                    final Map value = (Map) childJsonObj.get(keys);
                    processSchemaJsonObject(value);
                }
                break;
            case "string":
                if (jsonObj.containsKey("pattern")) {
                    final String pattern = (String) jsonObj.get("pattern");
                    Generex generex = new Generex(pattern);

                    // Generate random String
                    String randomStr = generex.random();

                    System.out.format("%nPattern:%s, String:%s", pattern, randomStr);
                }
                processString();
                break;
            case "number":
                break;
            case "array":
                break;


        }
    }

    private void processString() {

    }

    private String getJsonContext() throws IOException {
        if (Paths.get(pathToJsonSchema).isAbsolute()) {
            return Files.toString(new File(pathToJsonSchema), UTF_8);
        } else {
            return Resources.toString(Resources.getResource(pathToJsonSchema), UTF_8);
        }
    }
}
