package com.siby.generators;

public class LongGenerator implements Generator<Long> {


    public Long next() {
        return RANDOM.nextLong();
    }
}
