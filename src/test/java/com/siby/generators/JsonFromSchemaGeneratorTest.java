//package com.siby.generators;
//
//import org.junit.Test;
//
//import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//
//public class JsonFromSchemaGeneratorTest {
//    @Test
//    public void shouldGenerateRandomJsonFromSchema() {
//        // given
//        String pathToJsonSchema = "sample_json_schema.json";
//        JsonFromSchemaGenerator jsonFromSchemaGenerator = new JsonFromSchemaGenerator(pathToJsonSchema);
//
//        // when
//        String json = jsonFromSchemaGenerator.next();
//
//        // then
//        assertThat(json, is(not(null)));
//
//        /*
//        {
//  "firstName": "cupidatat ipsum laborum",
//  "lastName": "ex qui sed eiusmod dolor",
//  "age": 24317023,
//  "fstype": "ext4",
//  "tags": [
//    "quis sunt et"
//  ],
//  "label": "55cf41f7-08EA-BfeC-B070-8cB62EC89Bc3"
//         */
//
//    }
//}