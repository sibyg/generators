package com.siby.generators;

import static java.lang.String.format;
import static org.apache.commons.lang3.RandomStringUtils.random;

public class PostcodeGenerator implements Generator<String> {

    public String next() {
        return format("%s%s%01d %01d%s",
                random(1, "ABCDEFGHIJKLMNOPRSTUWYZ"),
                random(1, "ABCDEFGHKLMNOPQRSTUVWXY"),
                RandomGenerator.integer(9).next(),
                RandomGenerator.integer(9).next(),
                random(2, "ABDEFGHJLNPQRSTUWXYZ"));
    }
}
