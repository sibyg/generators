package com.siby.generators;

import static java.lang.String.format;

public class EmailAddressGenerator implements Generator<String> {

    public String next() {
        return format("%s@%s.%s", Random.string(10).next(), Random.string(10).next(), Random.values("com", "co.uk", "gov.uk", "org", "net").next());
    }
}