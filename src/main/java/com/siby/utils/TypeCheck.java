package com.siby.utils;

import com.siby.generators.Generator;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * A Utility class for type-based testing, where a type is a high-level specification of behavior that should hold for a range of data point
 *
 * @param <T> Type of the Property and must have {@link  Generator} implementation for type T
 *            Usage: typeCheck(RandomGenerator.STRING, s -> s.length() == s.toCharArray().length).withPreCondition(s -> s.length() < 5).verify(times(5));
 */
public class TypeCheck<T> {

    private final Generator<T> generator;
    private final Function<T, Boolean> condition;
    private Function<T, Boolean> preCondition = null;

    private TypeCheck(final Generator<T> generator, final Function<T, Boolean> condition) {
        requireNonNull(generator, "Generator cannot be null");
        requireNonNull(condition, "Condition cannot be null");
        this.generator = generator;
        this.condition = condition;
    }

    public static <T> TypeCheck<T> typeCheck(final Generator<T> generator, final Function<T, Boolean> condition) {
        return new TypeCheck<T>(generator, condition);
    }

    public TypeCheck<T> withPreCondition(final Function<T, Boolean> preCondition) {
        this.preCondition = preCondition;
        return this;
    }

    public void verify() {
        verify(Times.times(1));
    }

    public void verify(Times times) {
        for (int i = 0; i < times.getNum(); i++) {
            T next = generator.next();
            if (preCondition != null && !preCondition.apply(next)) {
                i--;
            } else {
                assertThat(String.format("failed for value %s", next.toString()), condition.apply(next), is(true));
            }
        }
    }

    public static class Times {
        private final int num;

        private Times(int num) {
            this.num = num;
        }

        int getNum() {
            return num;
        }

        public static Times times(int times) {
            return new Times(times);
        }
    }
}

