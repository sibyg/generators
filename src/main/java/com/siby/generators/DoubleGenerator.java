package com.siby.generators;

import static java.lang.Math.pow;

class DoubleGenerator implements Generator<Double> {

    private final Integer max;
    private final Integer decimalPlaces;

    public DoubleGenerator(final Integer max, final Integer decimalPlaces) {
        this.max = max;
        this.decimalPlaces = decimalPlaces;
    }


    public Double next() {
        final double pow = pow(10, decimalPlaces);
        return (double) RANDOM.nextInt((int) (max * pow)) / pow;
    }
}
