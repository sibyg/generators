package com.siby.generators;

import java.util.Random;

public class BooleanGenerator extends Generator<Boolean> {

    private final Random random = new Random();


    @Override
    public Boolean next() {
        return random.nextBoolean();
    }
}
