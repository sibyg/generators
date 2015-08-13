package com.siby.generators;

import static java.lang.String.format;
import static org.apache.commons.lang.RandomStringUtils.random;

public class PostcodeGenerator extends Generator<String> {
    @Override
    public String next() {
        return format("%s%s%01d %01d%s",
                random(1, "ABCDEFGHIJKLMNOPRSTUWYZ"),
                random(1, "ABCDEFGHKLMNOPQRSTUVWXY"),
                Random.integer(9).next(),
                Random.integer(9).next(),
                random(2, "ABDEFGHJLNPQRSTUWXYZ"));
    }
}
