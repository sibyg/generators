package com.siby.generators;

public class BooleanGenerator implements Generator<Boolean> {

    public Boolean next() {
        return RANDOM.nextBoolean();
    }
}
