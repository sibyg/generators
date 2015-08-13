package com.siby.generators;

import java.math.BigDecimal;
import java.util.Random;

import static java.lang.Math.pow;
import static java.math.RoundingMode.FLOOR;

public class BigDecimalGenerator extends Generator<BigDecimal> {

    private final Random random = new Random();
    private final Integer max;
    private final Integer decimalPlaces;

    public BigDecimalGenerator(Integer max, Integer decimalPlaces) {
        this.max = max;
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public BigDecimal next() {
        double pow = pow(10, decimalPlaces);
        return new BigDecimal((long) random.nextInt((int) (max * pow)) / pow).setScale(decimalPlaces, FLOOR);
    }
}
