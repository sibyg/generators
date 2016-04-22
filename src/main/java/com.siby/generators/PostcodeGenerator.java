package com.siby.generators;

import static org.apache.commons.lang.RandomStringUtils.random;
import static uk.co.argos.research.generators.RandomGenerator.randomInteger;

public class PostcodeGenerator extends Generator<String> {
    @Override
    public String next() {
        return format("%s%s%01d %01d%s",
                random(1, "ABCDEFGHIJKLMNOPRSTUWYZ"),
                random(1, "ABCDEFGHKLMNOPQRSTUVWXY"),
                randomInteger(9),
                randomInteger(9),
                random(2, "ABDEFGHJLNPQRSTUWXYZ"));
    }
}
