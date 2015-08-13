package com.siby.generators;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;

public class StringGenerator extends Generator<String> {
    private Integer length;

    public StringGenerator(Integer length) {
        this.length = length;
    }

    @Override
    public String next() {
        return randomAlphanumeric(length);
    }
}
