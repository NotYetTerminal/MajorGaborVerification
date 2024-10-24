// Author: Gábor Major
// Creation date: 2024-10-11
// Description: JUnit5 file for testing the Rate class

package cm;

import cm.Period;
import cm.Rate;
import cm.CarParkKind;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MajorGaborRateTests {

    @TestFactory
    Stream<DynamicTest> testRateConstructor() {
        // Testing data format is:
        //  (
        //      carParkKind, (
        //          reducedPeriods, (
        //              normalPeriods, (
        //                  normalRate, (
        //                      { reducedRate }
        //                  )
        //              )
        //          )
        //      )
        //  )
        Map<CarParkKind,
        Map<ArrayList<Period>,
        Map<ArrayList<Period>,
        Map<BigDecimal, BigDecimal[]>>>> validInputData = Map.of(
                CarParkKind.STAFF, Map.of(
                        new ArrayList<>() { { add(new Period(7, 10)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) },
                                        new BigDecimal(0), new BigDecimal[] { new BigDecimal(0) },
                                        new BigDecimal(1), new BigDecimal[] { new BigDecimal(0) },
                                        new BigDecimal(9), new BigDecimal[] { new BigDecimal(0) },
                                        new BigDecimal(10), new BigDecimal[] {
                                                new BigDecimal(0),
                                                new BigDecimal(1),
                                                new BigDecimal(9),
                                                new BigDecimal(10),
                                        },
                                        new BigDecimal(8), new BigDecimal[] { new BigDecimal(3) },
                                        new BigDecimal(5), new BigDecimal[] { new BigDecimal(5) }
                                ),
                                new ArrayList<>(), Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                ),
                                new ArrayList<>() { { add(new Period(10, 11)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                ),
                                new ArrayList<>() { { add(new Period(11, 13)); add(new Period(15, 19)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        ),
                        new ArrayList<>(), Map.of(
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        ),
                        new ArrayList<>() { { add(new Period(1, 5)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        ),
                        new ArrayList<>() { { add(new Period(4, 5)); add(new Period (8, 10)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        )
                ),
                CarParkKind.STUDENT, Map.of(
                        new ArrayList<>() { { add(new Period(7, 10)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        )
                ),
                CarParkKind.MANAGEMENT, Map.of(
                        new ArrayList<>() { { add(new Period(7, 10)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        )
                ),
                CarParkKind.VISITOR, Map.of(
                        new ArrayList<>() { { add(new Period(7, 10)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        )
                )
        );

        Stream<DynamicTest> validInputTestsStream = validInputData.entrySet().stream().flatMap(data_1 -> {
            CarParkKind carParkKind = data_1.getKey();
            return data_1.getValue().entrySet().stream().flatMap(data_2 -> {
                ArrayList<Period> reducedPeriods = data_2.getKey();
                return data_2.getValue().entrySet().stream().flatMap(data_3 -> {
                    ArrayList<Period> normalPeriods = data_3.getKey();
                    return data_3.getValue().entrySet().stream().flatMap(data_4 -> {
                        BigDecimal normalRate = data_4.getKey();
                        return Arrays.stream(data_4.getValue()).map(reducedRate -> DynamicTest.dynamicTest(
                                "Valid Input, CarParkKind: " + carParkKind + ", Reduced Periods: " + reducedPeriods + ", Normal Periods: " + normalPeriods + ", Normal Rate: " + normalRate + ", Reduced Rate: " + reducedRate + ", Expected Result: Create Class",
                                () -> {
                                    Rate rate = new Rate(carParkKind, reducedPeriods, normalPeriods, normalRate, reducedRate);
                                    assertAll(
                                            () -> assertEquals(carParkKind, rate.kind),
                                            () -> assertEquals(reducedPeriods, rate.reducedPeriodsList),
                                            () -> assertEquals(normalPeriods, rate.normalPeriodsList),
                                            () -> assertEquals(normalRate, rate.hourlyNormalRate),
                                            () -> assertEquals(reducedRate, rate.hourlyReducedRate)
                                    );
                                })
                        );
                    });
                });
            });
        });

        // Testing data format is:
        //  (
        //      carParkKind, (
        //          reducedPeriods, (
        //              normalPeriods, (
        //                  normalRate, (
        //                      { reducedRate }
        //                  )
        //              )
        //          )
        //      )
        //  )
        Map<CarParkKind,
        Map<ArrayList<Period>,
        Map<ArrayList<Period>,
        Map<BigDecimal, BigDecimal[]>>>> inValidInputData = Map.of(
                CarParkKind.STAFF, Map.of(
                        new ArrayList<>() { { add(new Period(2, 5)); add(new Period(3, 7)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        ),
                        new ArrayList<>() { { add(new Period(7, 10)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(11, 13)); add(new Period(12, 17)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                ),
                                new ArrayList<>() { { add(new Period(10, 12)); } }, Map.of(
                                        new BigDecimal(-1), new BigDecimal[] { new BigDecimal(3) },
                                        new BigDecimal(11), new BigDecimal[] { new BigDecimal(3) },
                                        new BigDecimal(6), new BigDecimal[] {
                                                new BigDecimal(-1),
                                                new BigDecimal(11)
                                        },
                                        new BigDecimal(4), new BigDecimal[] { new BigDecimal(5) }
                                )
                        ),
                        new ArrayList<>() { { add(new Period(4, 7)); } }, Map.of(
                                new ArrayList<>() { { add(new Period(5, 8)); } }, Map.of(
                                        new BigDecimal(6), new BigDecimal[] { new BigDecimal(3) }
                                )
                        )
                )
        );

        Stream<DynamicTest> invalidInputTestsStream = inValidInputData.entrySet().stream().flatMap(data_1 -> {
            CarParkKind carParkKind = data_1.getKey();
            return data_1.getValue().entrySet().stream().flatMap(data_2 -> {
                ArrayList<Period> reducedPeriods = data_2.getKey();
                return data_2.getValue().entrySet().stream().flatMap(data_3 -> {
                    ArrayList<Period> normalPeriods = data_3.getKey();
                    return data_3.getValue().entrySet().stream().flatMap(data_4 -> {
                        BigDecimal normalRate = data_4.getKey();
                        return Arrays.stream(data_4.getValue()).map(reducedRate -> DynamicTest.dynamicTest(
                                "Invalid Input, CarParkKind: " + carParkKind + ", Reduced Periods: " + reducedPeriods + ", Normal Periods: " + normalPeriods + ", Normal Rate: " + normalRate + ", Reduced Rate: " + reducedRate + ", Expected Result: Throw IllegalArgumentException",
                                () -> assertThrows(
                                        IllegalArgumentException.class,
                                        () -> new Rate(carParkKind, reducedPeriods, normalPeriods, normalRate, reducedRate)
                                )
                        ));
                    });
                });
            });
        });

        return Stream.concat(Stream.concat(validInputTestsStream, invalidInputTestsStream),
                Stream.of(DynamicTest.dynamicTest(
                        "Invalid Input, CarParkKind: " + null + ", Reduced Periods: " + new ArrayList<>() { { add(new Period(7, 10)); } } + ", Normal Periods: " + new ArrayList<>() { { add(new Period(10, 12)); } } + ", Normal Rate: " + new BigDecimal(6) + ", Reduced Rate: " + new BigDecimal(3) + ", Expected Result: Throw IllegalArgumentException",
                        () -> assertThrows(
                                IllegalArgumentException.class,
                                () -> new Rate(
                                        null,
                                        new ArrayList<>() { { add(new Period(7, 10)); } },
                                        new ArrayList<>() { { add(new Period(10, 12)); } },
                                        new BigDecimal(6),
                                        new BigDecimal(3)
                                )
                        )
                ))
        );
    }

    @TestFactory
    Stream<DynamicTest> testRateCalculate() {
        // Input test stream
        Rate rate = new Rate(CarParkKind.STAFF,
                new ArrayList<>() { { add(new Period(4, 5)); add(new Period(7, 10)); } },
                new ArrayList<>() { { add(new Period(10, 12)); add(new Period(12, 15)); } },
                new BigDecimal(6),
                new BigDecimal(3)
        );
        Stream<DynamicTest> inputTestsStream = Stream.of(
                DynamicTest.dynamicTest(
                        "Valid Input, Rate: " + rate + ", Period: " + new Period(3, 6) + ", Expected Result: 3",
                        () -> assertEquals(
                                new BigDecimal(3),
                                rate.calculate(new Period(3, 6))
                        )
                ),
                DynamicTest.dynamicTest(
                        "Invalid Input, Rate: " + rate + ", Period: " + null + ", Expected Result: Throw IllegalArgumentException",
                        () -> assertThrows(
                                IllegalArgumentException.class,
                                () -> rate.calculate(null)
                        )
                )
        );

        // Testing data format is:
        // For outer Period, test all the inner Periods associated with it,
        // Expected Result is the boolean
        Map<Period, BigDecimal> validOutputData = Map.of(
                new Period(1, 2), new BigDecimal(0),
                new Period(0, 24), new BigDecimal(42),
                new Period(5, 16), new BigDecimal(12),
                new Period(11, 16), new BigDecimal(24),
                new Period(9, 11), new BigDecimal(9)
        );

        // Valid Output test stream
        Stream<DynamicTest> validOutputTestsStream = validOutputData.entrySet().stream().map(data -> {
            Period period = data.getKey();
            BigDecimal result = data.getValue();
            return DynamicTest.dynamicTest(
                    "Valid Output, Rate: " + rate + ", Period: " + period + ", Expected Result: " + result,
                    () -> assertEquals(
                            result,
                            rate.calculate(period)
                    )
            );
        });

        return Stream.concat(inputTestsStream, validOutputTestsStream);
    }
}
