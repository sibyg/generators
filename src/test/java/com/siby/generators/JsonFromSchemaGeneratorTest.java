package com.siby.generators;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static com.google.common.base.Charsets.UTF_8;
import static org.junit.Assert.fail;

public class JsonFromSchemaGeneratorTest {

    @Test
    public void shouldGenerateRandomJsonFromSchema() {
        // given
        String pathToJsonSchema = "sample_json_schema.json";
        JsonFromSchemaGenerator jsonFromSchemaGenerator = new JsonFromSchemaGenerator(pathToJsonSchema);

        // when
        String json = jsonFromSchemaGenerator.next();

        try {
            getJsonSchemaFor(pathToJsonSchema).validate(new JSONObject(json));
        } catch (final ValidationException e) {
            fail();
        } catch (final IOException e) {
            fail();
        }
    }

    private static Schema getJsonSchemaFor(final String pathToJsonSchema) throws IOException {
        final String jsonSchema = getJsonContentFrom(pathToJsonSchema);
        final JSONObject rawSchema = new JSONObject(new JSONTokener(jsonSchema));
        return SchemaLoader.load(rawSchema);
    }

    private static String getJsonContentFrom(final String pathToJsonSchema) throws IOException {
        final String jsonContent;
        if (Paths.get(pathToJsonSchema).isAbsolute()) {
            jsonContent = Files.toString(new File(pathToJsonSchema), UTF_8);
        } else {
            jsonContent = Resources.toString(Resources.getResource(pathToJsonSchema), UTF_8);
        }
        return jsonContent;
    }
}