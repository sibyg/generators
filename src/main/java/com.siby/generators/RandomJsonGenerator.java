//package com.siby.generators;
//
//import com.google.common.io.Files;
//import com.google.common.io.Resources;
//import org.everit.json.schema.Schema;
//import org.everit.json.schema.loader.SchemaLoader;
//import org.json.JSONObject;
//import org.json.JSONTokener;
//
//import javax.json.JsonObject;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Paths;
//
//import static com.google.common.base.Charsets.UTF_8;
//
//public class RandomJsonGenerator implements Generator<JsonObject> {
//
//    private final String pathToJsonSchema;
//
//    public RandomJsonGenerator(String pathToJsonSchema) {
//        this.pathToJsonSchema = pathToJsonSchema;
//    }
//
//    private Schema getJsonSchema() throws IOException {
//        final String jsonContent;
//        if (Paths.get(pathToJsonSchema).isAbsolute()) {
//            jsonContent = Files.toString(new File(pathToJsonSchema), UTF_8);
//        } else {
//            jsonContent = Resources.toString(Resources.getResource(pathToJsonSchema), UTF_8);
//        }
//        final JSONObject rawSchema = new JSONObject(new JSONTokener(jsonContent));
//        return SchemaLoader.load(rawSchema);
//    }
//
//
//    public JsonObject next() {
//        try {
//            final Schema jsonSchema = getJsonSchema();
//            jsonSchema.
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        return null;
//    }
//}
