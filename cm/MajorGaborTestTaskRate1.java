//// Author: Gábor Major
//// Creation date: 2024-10-11
//// Description: JUnit5 file for testing the Rate class
//
//package cm;
//
//import org.junit.jupiter.api.DynamicTest;
//import org.junit.jupiter.api.TestFactory;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class MajorGaborTestTaskRate1 {
//
//    // Used for printing out test number of each test
//    int[] startingTestNumbers = new int[]{
//            1, 21, 1, 3,
//    };
//
//    // Base variables commonly used
//    ArrayList<Period> baseReducedPeriods = new ArrayList<>() {{
//        add(new Period(7, 10));
//    }};
//    ArrayList<Period> baseNormalPeriods = new ArrayList<>() {{
//        add(new Period(10, 12));
//    }};
//    BigDecimal baseNormalRate = new BigDecimal(6);
//    BigDecimal baseReducedRate = new BigDecimal(3);
//
//    @TestFactory
//    Stream<DynamicTest> testConstructorValidInput() {
//        AtomicInteger index = new AtomicInteger(startingTestNumbers[0]);
//
//        // Testing data format is: { carParkKind, reducedPeriods, normalPeriods, normalRate, reducedRate }
//        Object[][] invalidInputData = new Object[][]{
//                // CarParkKind checks
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate},
//                {CarParkKind.STUDENT, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate},
//                {CarParkKind.MANAGEMENT, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate},
//                {CarParkKind.VISITOR, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate},
//                // NormalPeriods checks
//                {CarParkKind.STAFF, new ArrayList<Period>(), baseNormalPeriods, baseNormalRate, baseReducedRate},
//                {
//                        CarParkKind.STAFF,
//                        new ArrayList<Period>() {{
//                            add(new Period(1, 5));
//                        }},
//                        baseNormalPeriods,
//                        baseNormalRate,
//                        baseReducedRate
//                },
//                {
//                        CarParkKind.STAFF,
//                        new ArrayList<Period>() {{
//                            add(new Period(4, 5));
//                            add(new Period(8, 10));
//                        }},
//                        baseNormalPeriods,
//                        baseNormalRate,
//                        baseReducedRate
//                },
//                // ReducedPeriods checks
//                {CarParkKind.STAFF, baseReducedPeriods, new ArrayList<Period>(), baseNormalRate, baseReducedRate},
//                {
//                        CarParkKind.STAFF,
//                        baseReducedPeriods,
//                        new ArrayList<Period>() {{
//                            add(new Period(10, 11));
//                        }},
//                        baseNormalRate,
//                        baseReducedRate
//                },
//                {
//                        CarParkKind.STAFF,
//                        baseReducedPeriods,
//                        new ArrayList<Period>() {{
//                            add(new Period(11, 13));
//                            add(new Period(15, 19));
//                        }},
//                        baseNormalRate,
//                        baseReducedRate
//                },
//                // NormalRates checks
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(0), new BigDecimal(0)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(1), new BigDecimal(0)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(9), new BigDecimal(0)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(0)},
//                // ReducedRates checks
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(0)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(1)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(9)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(10)},
//                // Both Rates checks
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(8), new BigDecimal(3)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(5), new BigDecimal(5)},
//        };
//
//        return Arrays.stream(invalidInputData).map(data -> {
//            CarParkKind carParkKind = (CarParkKind) data[0];
//            ArrayList<Period> reducedPeriods = (ArrayList<Period>) data[1];
//            ArrayList<Period> normalPeriods = (ArrayList<Period>) data[2];
//            BigDecimal normalRate = (BigDecimal) data[3];
//            BigDecimal reducedRate = (BigDecimal) data[4];
//
//            return DynamicTest.dynamicTest(
//                    "Test Number: " + index.getAndIncrement() + ", Valid Input, CarParkKind: " + carParkKind + ", Reduced Periods: " + reducedPeriods + ", Normal Periods: " + normalPeriods + ", Normal Rate: " + normalRate + ", Reduced Rate: " + reducedRate + ", Expected Result: Create Class",
//                    () -> {
//                        Rate rate = new Rate(carParkKind, reducedPeriods, normalPeriods, normalRate, reducedRate);
//                        assertAll(
//                                () -> assertEquals(carParkKind, rate.kind),
//                                () -> assertEquals(reducedPeriods, rate.reducedPeriodsList),
//                                () -> assertEquals(normalPeriods, rate.normalPeriodsList),
//                                () -> assertEquals(normalRate, rate.hourlyNormalRate),
//                                () -> assertEquals(reducedRate, rate.hourlyReducedRate)
//                        );
//                    }
//            );
//        });
//    }
//
//    @TestFactory
//    Stream<DynamicTest> testConstructorInvalidInput() {
//        AtomicInteger index = new AtomicInteger(startingTestNumbers[1]);
//
//        // Testing data format is: { carParkKind, reducedPeriods, normalPeriods, normalRate, reducedRate }
//        Object[][] invalidInputData = new Object[][]{
//                // Null checks
//                {null, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate},
//                {CarParkKind.STAFF, null, baseNormalPeriods, baseNormalRate, baseReducedRate},
//                {CarParkKind.STAFF, baseReducedPeriods, null, baseNormalRate, baseReducedRate},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, null, baseReducedRate},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, null},
//                // Periods checks
//                {
//                        CarParkKind.STAFF,
//                        new ArrayList<Period>() {{
//                            add(new Period(2, 5));
//                            add(new Period(3, 7));
//                        }},
//                        baseNormalPeriods,
//                        baseNormalRate,
//                        baseReducedRate
//                },
//                {
//                        CarParkKind.STAFF,
//                        baseReducedPeriods,
//                        new ArrayList<Period>() {{
//                            add(new Period(11, 13));
//                            add(new Period(12, 17));
//                        }},
//                        baseNormalRate,
//                        baseReducedRate
//                },
//                {
//                        CarParkKind.STAFF,
//                        new ArrayList<Period>() {{
//                            add(new Period(4, 7));
//                        }},
//                        new ArrayList<Period>() {{
//                            add(new Period(5, 8));
//                        }},
//                        baseNormalRate,
//                        baseReducedRate
//                },
//                // Rates checks
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(-1), baseReducedRate},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(11), baseReducedRate},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, new BigDecimal(-1)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, new BigDecimal(11)},
//                {CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(4), new BigDecimal(5)},
//        };
//
//        return Arrays.stream(invalidInputData).map(data -> {
//            CarParkKind carParkKind = (CarParkKind) data[0];
//            ArrayList<Period> reducedPeriods = (ArrayList<Period>) data[1];
//            ArrayList<Period> normalPeriods = (ArrayList<Period>) data[2];
//            BigDecimal normalRate = (BigDecimal) data[3];
//            BigDecimal reducedRate = (BigDecimal) data[4];
//
//            return DynamicTest.dynamicTest(
//                    "Test Number: " + index.getAndIncrement() + ", Invalid Input, CarParkKind: " + carParkKind + ", Reduced Periods: " + reducedPeriods + ", Normal Periods: " + normalPeriods + ", Normal Rate: " + normalRate + ", Reduced Rate: " + reducedRate + ", Expected Result: Throw IllegalArgumentException",
//                    () -> assertThrows(
//                            IllegalArgumentException.class,
//                            () -> new Rate(carParkKind, reducedPeriods, normalPeriods, normalRate, reducedRate)
//                    )
//            );
//        });
//    }
//
//    @TestFactory
//    Stream<DynamicTest> testCalculateInputs() {
//        AtomicInteger index = new AtomicInteger(startingTestNumbers[2]);
//
//        baseReducedPeriods.add(new Period(4, 5));
//        baseNormalPeriods.add(new Period(12, 15));
//        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate);
//
//        return Stream.of(
//                DynamicTest.dynamicTest(
//                        "Test Number: " + index.getAndIncrement() + ", Valid Input, Rate: " + rate + ", Period: " + new Period(3, 6) + ", Expected Result: 3",
//                        () -> assertEquals(
//                                new BigDecimal(3),
//                                rate.calculate(new Period(3, 6))
//                        )
//                ),
//                DynamicTest.dynamicTest(
//                        "Test Number: " + index.getAndIncrement() + ", Invalid Input, Rate: " + rate + ", Period: " + null + ", Expected Result: Throw IllegalArgumentException",
//                        () -> assertThrows(
//                                IllegalArgumentException.class,
//                                () -> rate.calculate(null)
//                        )
//                )
//        );
//    }
//
//    @TestFactory
//    Stream<DynamicTest> testCalculateValidOutput() {
//        AtomicInteger index = new AtomicInteger(startingTestNumbers[3]);
//
//        baseReducedPeriods.add(new Period(4, 5));
//        baseNormalPeriods.add(new Period(12, 15));
//        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate);
//
//        // Testing data format is ( Period, result as a BigDecimal )
//        Map<Period, BigDecimal> validOutputData = Map.of(
//                new Period(1, 2), new BigDecimal(0),
//                new Period(0, 24), new BigDecimal(42),
//                new Period(5, 16), new BigDecimal(12),
//                new Period(11, 16), new BigDecimal(24),
//                new Period(9, 11), new BigDecimal(9)
//        );
//
//        return validOutputData.entrySet().stream().map(data -> {
//            Period period = data.getKey();
//            BigDecimal result = data.getValue();
//            return DynamicTest.dynamicTest(
//                    "Test Number: " + index.getAndIncrement() + ", Valid Output, Rate: " + rate + ", Period: " + period + ", Expected Result: " + result,
//                    () -> assertEquals(
//                            result,
//                            rate.calculate(period)
//                    )
//            );
//        });
//    }
//}
