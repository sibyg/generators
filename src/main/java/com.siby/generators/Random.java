package com.siby.generators;

import java.math.BigDecimal;
import java.net.URI;

import static java.util.Arrays.asList;


public class Random {

    public static Generator<String> string = new StringGenerator(10);
    public static Generator<Integer> integer = new IntegerGenerator(Integer.MAX_VALUE);
    public static Generator<Long> longval = new LongGenerator();
    public static Generator<BigDecimal> bigDecimal = new BigDecimalGenerator(999999, 2);
    public static Generator<BigDecimal> percentage = new BigDecimalGenerator(100, 2);
    public static Generator<Boolean> booleanVal = new BooleanGenerator();
    public static Generator<Double> doubleval = new DoubleGenerator(Integer.MAX_VALUE, 2);
    public static Generator<String> niNumber = new NiNumberGenerator();
    public static Generator<String> emailAddress = new EmailAddressGenerator();
    public static Generator<String> postcode = new PostcodeGenerator();
    public static Generator<URI> uri = new UriGenerator();

    public static Generator<String> string(Integer length) {
        return new StringGenerator(length);
    }
    public static Generator<String> stringFromRegex(String regex) {
        return new StringFromRegexGenerator(regex);
    }
    public static Generator<String> jsonFromSchema(String schemaLocation) {
        return new JsonFromSchemaGenerator(schemaLocation);
    }

    public static <T> Generator<T> values(Iterable<T> values) {
        return new ValueGenerator<T>(values);
    }

    public static <T> Generator<T> values(T... values) {
        return values(asList(values));
    }

    public static Generator<Integer> integer(Integer max) {
        return new IntegerGenerator(max);
    }


    public static Generator<BigDecimal> bigDecimal(Integer max, Integer decimalPlaces) {
        return new BigDecimalGenerator(max, decimalPlaces);
    }

    public static Generator<BigDecimal> bigDecimal(Integer max) {
        return bigDecimal(max, 2);
    }


    public static Generator<Double> doubleval(Integer max, Integer decimalPlaces) {
        return new DoubleGenerator(max, decimalPlaces);
    }

    public interface ItemProvider<T> {
        T provideItem();
    }
}
