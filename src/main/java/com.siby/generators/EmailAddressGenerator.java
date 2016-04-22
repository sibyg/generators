package com.siby.generators;

import static com.siby.generators.RandomGenerator.randomString;
import static com.siby.generators.RandomGenerator.randomValues;
import static java.lang.String.format;

public class EmailAddressGenerator extends Generator<String> {
    public String next() {
        return format("%s@%s.%s", randomString(10), randomString(10), randomValues("com", "co.uk", "gov.uk", "org", "net"));
    }
}
