package com.siby.generators;

import java.util.Random;

public class BooleanGenerator extends Generator<Boolean> {

    private final Random random = new Random();


    public Boolean next() {
        return random.nextBoolean();
    }
}
