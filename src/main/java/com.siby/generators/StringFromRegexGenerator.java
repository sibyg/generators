package com.siby.generators;

import com.mifmif.common.regex.Generex;

public class StringFromRegexGenerator implements Generator<String> {

    private final String regex;

    public StringFromRegexGenerator(String regex) {
        this.regex = regex;
    }

    public String next() {
        return new Generex(regex).random();
    }
}
