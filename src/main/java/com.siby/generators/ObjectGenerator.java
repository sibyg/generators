package com.siby.generators;

import io.github.benas.jpopulator.impl.PopulatorBuilder;

public class ObjectGenerator<T> implements Generator<T> {

    private final Class<T> ofClass;

    public ObjectGenerator(Class<T> ofClass) {
        this.ofClass = ofClass;
    }


    public T next() {
        return new PopulatorBuilder().build().populateBean(ofClass);
    }
}
