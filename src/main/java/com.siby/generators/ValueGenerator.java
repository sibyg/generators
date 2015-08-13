package com.siby.generators;

public class ValueGenerator<T> extends Generator<T> {

    private Iterable<T> values;

    public ValueGenerator(Iterable<T> values) {
        this.values = values;
    }

    @Override
    public T next() {
        return values.iterator().next(); //TODO siby george fix it
    }
}
