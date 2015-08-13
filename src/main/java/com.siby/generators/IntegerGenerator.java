package com.siby.generators;

class IntegerGenerator extends Generator<Integer> {

    private static java.util.Random random = new java.util.Random();
    private Integer max;

    public IntegerGenerator(Integer max) {
        this.max = max;
    }

    @Override
    public Integer next() {
        return random.nextInt(max);
    }
}
