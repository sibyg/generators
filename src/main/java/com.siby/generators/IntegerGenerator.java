package com.siby.generators;

public class IntegerGenerator implements Generator<Integer> {

    private final Integer min;
    private final Integer max;

    private IntegerGenerator(final Integer min, final Integer max) {
        this.min = min;
        this.max = max;
    }

    public Integer next() {
        return RANDOM.nextInt(max - min) + min;
    }

    static class IntegerGeneratorBuilder {
        private Integer min = Integer.MIN_VALUE;
        private Integer max = Integer.MAX_VALUE;

        private IntegerGeneratorBuilder() {
        }

        public static IntegerGeneratorBuilder instance() {
            return new IntegerGeneratorBuilder();
        }

        public IntegerGeneratorBuilder min(final Integer min) {
            this.min = min;
            return this;
        }

        public IntegerGeneratorBuilder max(final Integer max) {
            this.max = max;
            return this;
        }

        public IntegerGenerator build() {
            return new IntegerGenerator(min, max);
        }
    }
}
