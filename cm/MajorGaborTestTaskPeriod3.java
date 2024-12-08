// Author: Gábor Major
// Creation date: 2024-12-08
// Description: JUnit5 file for testing the Period class

package cm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MajorGaborTestTaskPeriod3 {

    // Constructor Tests
    // Valid Input Tests
    @Test
    void constructorStartHourGreaterThanZero() {
        Period period = new Period(3, 18);
        assertNotNull(period);
    }

    @Test
    void constructorStartHourEqualToZero() {
        Period period = new Period(0, 18);
        assertNotNull(period);
    }

    @Test
    void constructorStartHourLessThanTwentyFour() {
        Period period = new Period(10, 18);
        assertNotNull(period);
    }

    @Test
    void constructorEndHourGreaterThanZero() {
        Period period = new Period(2, 5);
        assertNotNull(period);
    }

    @Test
    void constructorEndHourLessThanTwentyFour() {
        Period period = new Period(2, 15);
        assertNotNull(period);
    }

    @Test
    void constructorEndHourEqualToTwentyFour() {
        Period period = new Period(2, 24);
        assertNotNull(period);
    }

    @Test
    void constructorStartHourLessThanEndHour() {
        Period period = new Period(7, 9);
        assertNotNull(period);
    }

    // Invalid Input Tests
    @Test
    void constructorStartHourLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Period(-1, 8));
    }

    @Test
    void constructorStartHourGreaterThanTwentyFour() {
        assertThrows(IllegalArgumentException.class, () -> new Period(27, 30));
    }

    @Test
    void constructorStartHourEqualToTwentyFour() {
        assertThrows(IllegalArgumentException.class, () -> new Period(24, 8));
    }

    @Test
    void constructorEndHourLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Period(6, -1));
    }

    @Test
    void constructorEndHourEqualToZero() {
        assertThrows(IllegalArgumentException.class, () -> new Period(6, 0));
    }

    @Test
    void constructorEndHourGreaterThanTwentyFour() {
        assertThrows(IllegalArgumentException.class, () -> new Period(6, 29));
    }

    @Test
    void constructorStartHourGreaterThanEndHour() {
        assertThrows(IllegalArgumentException.class, () -> new Period(10, 4));
    }

    @Test
    void constructorStartHourEqualToEndHour() {
        assertThrows(IllegalArgumentException.class, () -> new Period(12, 12));
    }

    // Overlaps method Tests
    // Valid Input Tests
    @Test
    void overlapsPeriodNotNull() {
        Period period = new Period(5, 7);
        assertTrue(period.overlaps(new Period(6, 8)));
    }

    // Invalid Input Tests
    @Test
    void overlapsPeriodIsNull() {
        Period period = new Period(5, 7);
        assertThrows(IllegalArgumentException.class, () -> period.overlaps(null));
    }

    // Valid Output Tests
    @Test
    void overlapsInputStartHourLessThanEndHour() {
        Period period = new Period(10, 12);
        assertTrue(period.overlaps(new Period(11, 14)));
    }

    @Test
    void overlapsInputStartHourEqualToEndHour() {
        Period period = new Period(10, 12);
        assertFalse(period.overlaps(new Period(12, 14)));
    }

    @Test
    void overlapsInputStartHourGreaterThanEndHour() {
        Period period = new Period(10, 12);
        assertFalse(period.overlaps(new Period(13, 14)));
    }

    @Test
    void overlapsInputEndHourGreaterThanStartHour() {
        Period period = new Period(10, 12);
        assertTrue(period.overlaps(new Period(5, 11)));
    }

    @Test
    void overlapsInputEndHourEqualToStartHour() {
        Period period = new Period(10, 12);
        assertFalse(period.overlaps(new Period(5, 10)));
    }

    @Test
    void overlapsInputEndHourLessThanStartHour() {
        Period period = new Period(10, 12);
        assertFalse(period.overlaps(new Period(5, 9)));
    }

    // Duration method Tests
    // Valid Output Tests
    @Test
    void durationStartHourEqualToZero() {
        Period period = new Period(0, 10);
        assertEquals(10, period.duration());
    }

    @Test
    void durationStartHourNotEqualToZero() {
        Period period = new Period(7, 10);
        assertEquals(3, period.duration());
    }

    @Test
    void durationEndHourEqualToTwentyFour() {
        Period period = new Period(14, 24);
        assertEquals(10, period.duration());
    }

    @Test
    void durationEndHourNotEqualToTwentyFour() {
        Period period = new Period(14, 18);
        assertEquals(4, period.duration());
    }
}
