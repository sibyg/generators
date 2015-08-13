package com.siby.generators;

import java.util.Random;

import static java.lang.Math.pow;

class DoubleGenerator extends Generator<Double> {

    private static Random random = new Random();
    private final Integer max;
    private final Integer decimalPlaces;

    public DoubleGenerator(Integer max, Integer decimalPlaces) {
        this.max = max;
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public Double next() {
        double pow = pow(10, decimalPlaces);
        return (double) random.nextInt((int) (max * pow)) / pow;
    }
}
