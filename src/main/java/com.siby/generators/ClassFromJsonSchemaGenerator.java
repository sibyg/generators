package com.siby.generators;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ClassFromJsonSchemaGenerator implements Generator<Void> {

    private final String schemaLocation;
    private final String classname;
    private final String packageName;
    private final String fileDirectory;

    public ClassFromJsonSchemaGenerator(String schemaLocation, String classname, String packageName, String fileDirectory) {
        this.schemaLocation = schemaLocation;
        this.classname = classname;
        this.packageName = packageName;
        this.fileDirectory = fileDirectory;
    }


    public Void next() {
        try {
            JCodeModel codeModel = new JCodeModel();
            URL source = new URL("file://" + schemaLocation);
            createSchemaMapper().generate(codeModel, classname, packageName, source);
            codeModel.build(new File(fileDirectory));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    private SchemaMapper createSchemaMapper() {
        final RuleFactory ruleFactory = new RuleFactory();
        ruleFactory.setAnnotator(new Jackson2Annotator() {

                                     public boolean isAdditionalPropertiesSupported() {
                                         return false;
                                     }
                                 }
        );

        GenerationConfig config = new MyConfig();

        ruleFactory.setGenerationConfig(config);
        return new SchemaMapper(ruleFactory, new SchemaGenerator());
    }

    private static class MyConfig extends DefaultGenerationConfig {

        public SourceType getSourceType() {
            return SourceType.JSON;
        }
    }
}
