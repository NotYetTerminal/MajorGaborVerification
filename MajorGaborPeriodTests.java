// Author: Gábor Major
// Creation date: 2024-10-11
// Description: JUnit5 file for testing the Period class

package cm;

import cm.Period;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MajorGaborPeriodTests {

    // Used for printing out test number of each test
    int[] startingTestNumbers = new int[]{
            1, 8, 1, 3, 1
    };

    @TestFactory
    Stream<DynamicTest> testConstructorValidInput() {
        // Testing data format is: { startHour, endHour }
        int[][] validInputData = new int[][]{
                {3, 18},
                {0, 18},
                {10, 18},
                {2, 5},
                {2, 15},
                {2, 24},
                {7, 9}
        };

        AtomicInteger index = new AtomicInteger(startingTestNumbers[0]);
        return Arrays.stream(validInputData).map(data -> {
            int start = data[0];
            int end = data[1];
            return DynamicTest.dynamicTest(
                    "Test Number: " + index.getAndIncrement() + ", Valid Input, Start: " + start + ", End: " + end + ", Expected Result: Create Class",
                    () -> {
                        Period testPeriod = new Period(start, end);
                        assertAll(
                                () -> assertEquals(start, testPeriod.startHour),
                                () -> assertEquals(end, testPeriod.endHour)
                        );
                    });
        });
    }

    @TestFactory
    Stream<DynamicTest> testConstructorInvalidInput() {
        // Testing data format is: { startHour, endHour }
        int[][] invalidInputData = new int[][]{
                {-1, 8},
                {27, 8},
                {24, 8},
                {6, -1},
                {6, 0},
                {6, 29},
                {10, 4},
                {12, 12}
        };

        AtomicInteger index = new AtomicInteger(startingTestNumbers[1]);
        return Arrays.stream(invalidInputData).map(data -> {
            int start = data[0];
            int end = data[1];
            return DynamicTest.dynamicTest(
                    "Test Number: " + index.getAndIncrement() + ", Invalid Input, Start: " + start + ", End: " + end + ", Expected Result: Throw IllegalArgumentException",
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> new Period(start, end)
                    )
            );
        });
    }

    @TestFactory
    Stream<DynamicTest> testOverlapsInputs() {
        Period period = new Period(5, 7);

        AtomicInteger index = new AtomicInteger(startingTestNumbers[2]);
        return Stream.of(
                DynamicTest.dynamicTest(
                        "Test Number: " + index.getAndIncrement() + ", Valid Input, Expected Result: TRUE",
                        () -> assertTrue(period.overlaps(new Period(6, 8)))
                ),
                DynamicTest.dynamicTest(
                        "Test Number: " + index.getAndIncrement() + ", Invalid Input, Expected Result: Throw IllegalArgumentException",
                        () -> assertThrows(
                                IllegalArgumentException.class,
                                () -> period.overlaps(null)
                        )
                )
        );
    }

    @TestFactory
    Stream<DynamicTest> testOverlapsValidOutput() {
        Period period_1 = new Period(10, 12);

        // Testing data format is ( Period, result as a boolean )
        Map<Period, Boolean> validOutputData = Map.of(
                new Period(11, 14), true,
                new Period(12, 14), false,
                new Period(13, 14), false,
                new Period(5, 11), true,
                new Period(5, 10), false,
                new Period(5, 9), false
        );

        AtomicInteger index = new AtomicInteger(startingTestNumbers[3]);
        return validOutputData.entrySet().stream().map(data -> {
            Period period_2 = data.getKey();
            boolean result = data.getValue();
            return DynamicTest.dynamicTest(
                    "Test Number: " + index.getAndIncrement() + ", Valid Output, Period 1: " + period_1 + ", Period 2: " + period_2 + ", Expected Result: " + result,
                    () -> assertEquals(
                            result,
                            period_1.overlaps(period_2)
                    )
            );
        });
    }

    @TestFactory
    Stream<DynamicTest> testDurationValidOutput() {
        // Testing data format is: ( Period, expected result of period.duration() )
        Map<Period, Integer> testingData = Map.of(
                new Period(0, 10), 10,
                new Period(7, 10), 3,
                new Period(14, 24), 10,
                new Period(14, 18), 4
        );

        AtomicInteger index = new AtomicInteger(startingTestNumbers[4]);
        return testingData.entrySet().stream().map(data -> {
            Period period = data.getKey();
            int result = data.getValue();
            return DynamicTest.dynamicTest(
                    "Test Number: " + index.getAndIncrement() + ", Valid Output, Duration(" + period.startHour + ",  " + period.endHour + "), Expected Result: " + result,
                    () -> assertEquals(
                            result,
                            period.duration()
                    )
            );
        });
    }
}
