// Author: Gábor Major
// Creation date: 2024-10-11
// Description: JUnit5 file for testing the Period class

package cm;

import cm.Period;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MajorGaborPeriodTests {

    @TestFactory
    Stream<DynamicTest> testPeriodConstructor() {
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

        return Stream.concat(
                // Valid Input test stream
                Arrays.stream(validInputData).map(data -> {
                    int start = data[0];
                    int end = data[1];
                    return DynamicTest.dynamicTest(
                            "Valid Input, Start: " + start + ", End: " + end + ", Expected Result: Create Class",
                            () -> {
                                Period testPeriod = new Period(start, end);
                                assertAll(
                                        () -> assertEquals(start, testPeriod.startHour),
                                        () -> assertEquals(end, testPeriod.endHour)
                                );
                            });
                }),
                // Invalid Input test stream
                Arrays.stream(invalidInputData).map(data -> {
                    int start = data[0];
                    int end = data[1];
                    return DynamicTest.dynamicTest(
                            "Invalid Input, Start: " + start + ", End: " + end + ", Expected Result: Throw IllegalArgumentException",
                            () -> assertThrows(
                                    IllegalArgumentException.class,
                                    () -> new Period(start, end)
                            )
                    );
                })
        );
    }

    @TestFactory
    Stream<DynamicTest> testPeriodOverlaps() {
        // Input test stream
        Period period = new Period(5, 7);
        Stream<DynamicTest> inputTestsStream = Stream.of(
                DynamicTest.dynamicTest(
                        "Valid Input, Expected Result: TRUE",
                        () -> assertTrue(period.overlaps(new Period(6, 8)))
                ),
                DynamicTest.dynamicTest(
                        "Invalid Input, Expected Result: Throw IllegalArgumentException",
                        () -> assertThrows(
                                IllegalArgumentException.class,
                                () -> period.overlaps(null)
                        )
                )
        );

        // Testing data format is:
        // For outer Period, test all the inner Periods associated with it,
        // Expected Result is the boolean
        Map<Period, Map<Period, Boolean>> validOutputData = Map.of(
                new Period(10, 12), Map.of(
                        new Period(11, 14), true,
                        new Period(12, 14), false,
                        new Period(13, 14), false
                ),
                new Period(7, 10), Map.of(
                        new Period(5, 8), true,
                        new Period(5, 7), false,
                        new Period(5, 6), false
                )
        );
        // Valid Output test stream
        Stream<DynamicTest> validOutputTestsStream = validOutputData.entrySet().stream().flatMap(data -> {
            Period period_1 = data.getKey();
            return data.getValue().entrySet().stream().map(inner_data -> {
                Period period_2 = inner_data.getKey();
                boolean result = inner_data.getValue();
                return DynamicTest.dynamicTest(
                        "Valid Output, Period 1: " + period_1 + ", Period 2: " + period_2 + ", Expected Result: " + result,
                        () -> assertEquals(
                                result,
                                period_1.overlaps(period_2)
                        )
                );
            });
        });

        return Stream.concat(inputTestsStream, validOutputTestsStream);
    }

    @TestFactory
    Stream<DynamicTest> testPeriodDuration() {
        // Testing data format is: ( Period, expected result of period.duration() )
        Map<Period, Integer> testingData = Map.of(
                new Period(0, 10), 10,
                new Period(7, 10), 3,
                new Period(14, 24), 10,
                new Period(14, 18), 4
        );

        return testingData.entrySet().stream().map(data -> {
            Period period = data.getKey();
            int result = data.getValue();
            return DynamicTest.dynamicTest(
                    "Valid Output, Duration(" + period.startHour + ",  " + period.endHour + "), Expected Result: " + result,
                    () -> assertEquals(
                            result,
                            period.duration()
                    )
            );
        });
    }
}
