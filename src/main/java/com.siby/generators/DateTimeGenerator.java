package com.siby.generators;


import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

public class DateTimeGenerator extends Generator<LocalDateTime> {
    private static final Integer SECONDS_PER_DAY = 60 * 60 * 24;
    private final DateGenerator dateGenerator;

    public DateTimeGenerator(Period averageAge, LocalDate start, DateGenerator.Direction direction) {
        dateGenerator = new DateGenerator(averageAge, start, direction);
    }

    @Override
    public LocalDateTime next() {
        return new LocalDateTime(dateGenerator.next().toDate()).plus(Period.seconds(Random.integer(SECONDS_PER_DAY).next()));
    }

}
