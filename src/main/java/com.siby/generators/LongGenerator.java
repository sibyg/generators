package com.siby.generators;

class LongGenerator extends Generator<Long> {

    private static java.util.Random random = new java.util.Random();

    @Override
    public Long next() {
        return random.nextLong();
    }
}
