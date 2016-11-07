package com.siby.generators;

public class EnumPicker<T extends Enum<?>> implements Generator<T> {

    private final Class<T> clazz;
    private final Generator<Integer> generator;

    public EnumPicker(final Class<T> clazz) {
        this.clazz = clazz;
        this.generator = Random.integerWithMax(clazz.getEnumConstants().length);
    }


    public T next() {
        return this.clazz.getEnumConstants()[generator.next()];
    }
}
