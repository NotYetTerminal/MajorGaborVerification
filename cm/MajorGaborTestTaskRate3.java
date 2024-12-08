// Author: Gábor Major
// Creation date: 2024-12-08
// Description: JUnit5 file for testing the Rate class

package cm;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MajorGaborTestTaskRate3 {
    // Base variables commonly used
    final ArrayList<Period> baseReducedPeriods = new ArrayList<>() {{
        add(new Period(7, 10));
    }};
    final ArrayList<Period> calculateBaseReducedPeriods = new ArrayList<>() {{
        add(new Period(7, 10));
        add(new Period(4, 5));
    }};
    final ArrayList<Period> baseNormalPeriods = new ArrayList<>() {{
        add(new Period(10, 12));
    }};
    final ArrayList<Period> calculateBaseNormalPeriods = new ArrayList<>() {{
        add(new Period(10, 12));
        add(new Period(12, 15));
    }};
    final BigDecimal baseNormalRate = new BigDecimal(6);
    final BigDecimal baseReducedRate = new BigDecimal(3);

    // Constructor Tests
    // Valid Input Tests
    // CarParkKind Tests
    @Test
    void constructorCarParkKindIsStaff() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    @Test
    void constructorCarParkKindIsStudent() {
        Rate rate = new Rate(CarParkKind.STUDENT, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    @Test
    void constructorCarParkKindIsManagement() {
        Rate rate = new Rate(CarParkKind.MANAGEMENT, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    @Test
    void constructorCarParkKindIsVisitor() {
        Rate rate = new Rate(CarParkKind.VISITOR, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    // ReducedPeriods Tests
    @Test
    void constructorReducedPeriodsIsEmpty() {
        Rate rate = new Rate(CarParkKind.STAFF, new ArrayList<>(), baseNormalPeriods, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    @Test
    void constructorReducedPeriodsHasOnePeriod() {
        Rate rate = new Rate(CarParkKind.STAFF, new ArrayList<>() {{
            add(new Period(1, 5));
        }}, baseNormalPeriods, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    @Test
    void constructorReducedPeriodsHasMultiplePeriods() {
        Rate rate = new Rate(CarParkKind.STAFF, new ArrayList<>() {{
            add(new Period(4, 5));
            add(new Period(8, 10));
        }}, baseNormalPeriods, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    // NormalPeriods Tests
    @Test
    void constructorNormalPeriodsIsEmpty() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, new ArrayList<>(), baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    @Test
    void constructorNormalPeriodsHasOnePeriod() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, new ArrayList<>() {{
            add(new Period(10, 11));
        }}, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    @Test
    void constructorNormalPeriodsHasMultiplePeriods() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, new ArrayList<>() {{
            add(new Period(11, 13));
            add(new Period(15, 19));
        }}, baseNormalRate, baseReducedRate);
        assertNotNull(rate);
    }

    // NormalRate Tests
    @Test
    void constructorNormalRateEqualToZero() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(0), new BigDecimal(0));
        assertNotNull(rate);
    }

    @Test
    void constructorNormalRateGreaterThanZero() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(1), new BigDecimal(0));
        assertNotNull(rate);
    }

    @Test
    void constructorNormalRateLessThanTen() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(9), new BigDecimal(0));
        assertNotNull(rate);
    }

    @Test
    void constructorNormalRateEqualToTen() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(0));
        assertNotNull(rate);
    }

    // ReducedRate Tests
    @Test
    void constructorReducedRateEqualToZero() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(0));
        assertNotNull(rate);
    }

    @Test
    void constructorReducedRateGreaterThanZero() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(1));
        assertNotNull(rate);
    }

    @Test
    void constructorReducedRateLessThanTen() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(9));
        assertNotNull(rate);
    }

    @Test
    void constructorReducedRateEqualToTen() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(10), new BigDecimal(10));
        assertNotNull(rate);
    }

    // Both NormalRate and ReducedRate Tests
    @Test
    void constructorNormalRateGreaterThanReducedRate() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(8), new BigDecimal(3));
        assertNotNull(rate);
    }

    @Test
    void constructorNormalRateEqualToReducedRate() {
        Rate rate = new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(5), new BigDecimal(5));
        assertNotNull(rate);
    }

    // Invalid Input Tests
    // Null Tests
    @Test
    void constructorCarParkKindIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(null, baseReducedPeriods, baseNormalPeriods, baseNormalRate, baseReducedRate));
    }

    @Test
    void constructorReducedPeriodsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, null, baseNormalPeriods, baseNormalRate, baseReducedRate));
    }

    @Test
    void constructorNormalPeriodsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, null, baseNormalRate, baseReducedRate));
    }

    @Test
    void constructorNormalRateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, null, baseReducedRate));
    }

    @Test
    void constructorReducedRateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, null));
    }

    // Periods Overlapping Tests
    @Test
    void constructorReducedPeriodsIsOverlapping() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new ArrayList<>() {{
            add(new Period(2, 5));
            add(new Period(3, 7));
            add(new Period(9, 10));
        }}, baseNormalPeriods, baseNormalRate, baseReducedRate));
    }

    @Test
    void constructorNormalPeriodsIsOverlapping() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, new ArrayList<>() {{
            add(new Period(11, 13));
            add(new Period(12, 17));
        }}, baseNormalRate, baseReducedRate));
    }

    @Test
    void constructorReducedPeriodsAndNormalPeriodsOverlapping() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new ArrayList<>() {{
            add(new Period(4, 7));
            add(new Period(11, 12));
        }}, new ArrayList<>() {{
            add(new Period(5, 8));
        }}, baseNormalRate, baseReducedRate));
    }

    // Rates Tests
    @Test
    void constructorNormalRateLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(-1), baseReducedRate));
    }

    @Test
    void constructorNormalRateGreaterThanTen() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(11), baseReducedRate));
    }

    @Test
    void constructorReducedRateLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, new BigDecimal(-1)));
    }

    @Test
    void constructorReducedRateGreaterThanTen() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, baseNormalRate, new BigDecimal(11)));
    }

    @Test
    void constructorNormalRateLessThanReducedRate() {
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, baseReducedPeriods, baseNormalPeriods, new BigDecimal(4), new BigDecimal(5)));
    }

    // Calculate method Tests
    // Valid Input Tests
    @Test
    void calculatePeriodNotNull() {
        Rate rate = new Rate(CarParkKind.STAFF, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal(3), rate.calculate(new Period(3, 6)));
    }

    // Invalid Input Tests
    @Test
    void calculatePeriodIsNull() {
        Rate rate = new Rate(CarParkKind.STAFF, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertThrows(IllegalArgumentException.class, () -> rate.calculate(null));
    }

    // Valid Output Tests
    @Test
    void calculatePeriodOutsideRates() {
        Rate rate = new Rate(CarParkKind.STAFF, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal(0), rate.calculate(new Period(1, 2)));
    }

    @Test
    void calculatePeriodForAllRates() {
        Rate rate = new Rate(CarParkKind.STAFF, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal(42), rate.calculate(new Period(0, 24)));
    }

    @Test
    void calculatePeriodOnlyReducedRates() {
        Rate rate = new Rate(CarParkKind.STAFF, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal(9), rate.calculate(new Period(5, 10)));
    }

    @Test
    void calculatePeriodOnlyNormalRates() {
        Rate rate = new Rate(CarParkKind.STAFF, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal(24), rate.calculate(new Period(11, 16)));
    }

    @Test
    void calculatePeriodBothRates() {
        Rate rate = new Rate(CarParkKind.STAFF, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal(9), rate.calculate(new Period(9, 11)));
    }

    // Tests when CarParkKind is VISITOR
    @Test
    void calculateKindIsVisitorAndFeeLessThanTen() {
        Rate rate = new Rate(CarParkKind.VISITOR, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal("0.0"), rate.calculate(new Period(7, 9)));
    }

    @Test
    void calculateKindIsVisitorAndFeeEqualToTen() {
        Rate rate = new Rate(CarParkKind.VISITOR, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, new BigDecimal(2));
        assertEquals(new BigDecimal("0.0"), rate.calculate(new Period(8, 11)));
    }

    @Test
    void calculateKindIsVisitorAndFeeGreaterThanTen() {
        Rate rate = new Rate(CarParkKind.VISITOR, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal("4.0"), rate.calculate(new Period(12, 15)));
    }

    // Tests when CarParkKind is MANAGEMENT
    @Test
    void calculateKindIsManagementAndFeeLessThanTen() {
        Rate rate = new Rate(CarParkKind.MANAGEMENT, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal(4), rate.calculate(new Period(8, 9)));
    }

    @Test
    void calculateKindIsManagementAndFeeEqualToTen() {
        Rate rate = new Rate(CarParkKind.MANAGEMENT, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, new BigDecimal(2));
        assertEquals(new BigDecimal(4), rate.calculate(new Period(8, 10)));
    }

    @Test
    void calculateKindIsManagementAndFeeGreaterThanTen() {
        Rate rate = new Rate(CarParkKind.MANAGEMENT, calculateBaseReducedPeriods, calculateBaseNormalPeriods, baseNormalRate, baseReducedRate);
        assertEquals(new BigDecimal(6), rate.calculate(new Period(10, 11)));
    }
}
