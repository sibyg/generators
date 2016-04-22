package com.siby.generators;

import java.net.URI;
import java.net.URISyntaxException;

import static uk.co.argos.research.generators.RandomGenerator.randomString;
import static uk.co.argos.research.generators.RandomGenerator.randomValues;

public class UriGenerator extends Generator<URI> {
    @Override
    public URI next() {
        try {
            return new URI(format("http://%s.%s", randomString(), randomValues("com", "co.uk", "org")));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
