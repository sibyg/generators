package com.siby.generators;

import java.net.URI;
import java.net.URISyntaxException;

import static com.siby.generators.RandomGenerator.STRING;
import static java.lang.String.format;

public class UriGenerator implements Generator<URI> {

    public URI next() {
        try {
            return new URI(format("http://%s.%s", STRING.next(), RandomGenerator.values("com", "co.uk", "org").next()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
