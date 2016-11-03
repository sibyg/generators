package com.siby.generators;

public class IntegerGenerator implements Generator<Integer> {

    private final Integer max;

    public IntegerGenerator(final Integer max) {
        this.max = max;
    }


    public Integer next() {
        return RANDOM.nextInt(max);
    }
}
