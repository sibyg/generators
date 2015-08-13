package com.siby.generators;

import org.joda.time.LocalDate;
import org.joda.time.Period;

public class DateGenerator extends Generator<LocalDate> {
    private final LocalDate start;
    private final LocalDate end;

    public DateGenerator(Period averageAge, LocalDate start, Direction direction) {
        this.start = start;
        Period maxAge = averageAge.multipliedBy(2);
        this.end = direction == Direction.FORWARD ? start.plus(maxAge) : start.minus(maxAge);
    }

    public DateGenerator(Period minAge, Period maxAge, LocalDate today, Direction direction) {
        this.start = direction == Direction.FORWARD ? today.plus(minAge) : today.minus(minAge);
        this.end = direction == Direction.FORWARD ? today.plus(maxAge) : today.minus(maxAge);
    }

    @Override
    public LocalDate next() {
        return new LocalDate(); //TODO siby george fix it
    }


    public static enum Direction {FORWARD, BACKWARD}
}
