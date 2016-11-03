package com.siby.generators;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * Maximum length supported is 10
 */
public class StringGenerator implements Generator<String> {

    private static final int MAX_LENGTH = 10;
    private int length = MAX_LENGTH;

    public StringGenerator() {
    }

    public StringGenerator(final int length) {
        if (length > MAX_LENGTH) {
            throw new IllegalArgumentException("Max supported length is " + MAX_LENGTH);
        }
        this.length = length;
    }


    public String next() {
        return randomAlphanumeric(length);
    }
}
