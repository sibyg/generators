package com.siby.generators;

import java.net.URI;
import java.net.URISyntaxException;

public class UriGenerator extends Generator<URI> {
    @Override
    public URI next() {
        try {
            return new URI(String.format("http://%s.%s", Random.string.next(), Random.values("com", "co.uk", "org").next()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
