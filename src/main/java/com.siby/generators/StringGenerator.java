package com.siby.generators;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;

public class StringGenerator extends Generator<String> {
    private Integer length = 3;

    public StringGenerator(Integer length) {
        if (length != null) {
            this.length = length;
        }

    }

    @Override
    public String next() {
        return randomAlphanumeric(length);
    }
}
