package com.siby.generators;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import static java.util.Arrays.asList;


public class Random {

    public static final Generator<String> string = new StringGenerator(10);
    public static final Generator<Integer> integer = IntegerGenerator.IntegerGeneratorBuilder.instance().build();
    public static final Generator<Long> longval = new LongGenerator();
    public static final Generator<BigDecimal> bigDecimal = new BigDecimalGenerator(999999, 2);
    public static final Generator<BigDecimal> percentage = new BigDecimalGenerator(100, 2);
    public static final Generator<Boolean> booleanVal = new BooleanGenerator();
    public static final Generator<Double> doubleval = new DoubleGenerator(Integer.MAX_VALUE, 2);
    public static final Generator<String> niNumber = new NiNumberGenerator();
    public static final Generator<String> emailAddress = new EmailAddressGenerator();
    public static final Generator<String> postcode = new PostcodeGenerator();
    public static final Generator<URI> uri = new UriGenerator();
    public static final Generator<java.util.UUID> uuid = new UUIDGenerator();
    public static final Generator<LocalDate> future_local_date = new LocalDateGenerator(Period.ofYears(5), LocalDate.now(), LocalDateGenerator.Direction.FORWARD);
    public static final Generator<LocalDate> past_local_date = new LocalDateGenerator(Period.ofYears(5), LocalDate.now(), LocalDateGenerator.Direction.BACKWARD);


    public static Generator<String> string(Integer length) {
        return new StringGenerator(length);
    }

    public static Generator<String> stringFromRegex(String regex) {
        return new StringFromRegexGenerator(regex);
    }

//    public static Generator<String> jsonFromSchema(String schemaLocation) {
//        return new JsonFromSchemaGenerator(schemaLocation);
//    }

    public static Generator<String> string(int length) {
        return new StringGenerator(length);
    }

    public static <T> Generator<T> values(Iterable<T> values) {
        return new ValueGenerator<T>(values);
    }

    public static <T> Generator<T> values(T... values) {
        return values(asList(values));
    }

    public static Generator<Integer> integerWithMax(Integer max) {
        return IntegerGenerator.IntegerGeneratorBuilder.instance().max(max).build();
    }

    public static Generator<Integer> integerWithMin(Integer min) {
        return IntegerGenerator.IntegerGeneratorBuilder.instance().min(min).build();
    }

    public static Generator<Integer> integer(Integer min, Integer max) {
        return IntegerGenerator.IntegerGeneratorBuilder.instance().min(min).max(max).build();
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

    public static <T extends Enum<?>> Generator<T> randomEnum(Class<T> clazz) {
        return new EnumPicker<>(clazz);
    }


    public interface ItemProvider<T> {
        T provideItem();
    }
}
