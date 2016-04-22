package com.siby.generators;

import java.math.BigDecimal;

import static java.util.Arrays.asList;


public class RandomGenerator {

    public static String randomString(Integer length) {
        return new StringGenerator(length).next();
    }

    public static String randomString() {
        return new StringGenerator(null).next();
    }

    public static <T> T randomValues(Iterable<T> values) {
        return new ValueGenerator<T>(values).next();
    }

    public static <T> T randomValues(T... values) {
        return randomValues(asList(values));
    }

    public static Integer randomInteger(Integer max) {
        return new IntegerGenerator(max).next();
    }


    public static BigDecimal randomBigDecimal(Integer max, Integer decimalPlaces) {
        return new BigDecimalGenerator(max, decimalPlaces).next();
    }

    public static BigDecimal randomBigDecimal(Integer max) {
        return randomBigDecimal(max, 2);
    }


    public static Double randomDouble(Integer max, Integer decimalPlaces) {
        return new DoubleGenerator(max, decimalPlaces).next();
    }
}
