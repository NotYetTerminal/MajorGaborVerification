// Author: Gábor Major
// Creation date: 2024-10-11
// Description: Dummy Rate class

package cm;

import java.math.BigDecimal;
import java.util.ArrayList;

import cm.Period;
import cm.CarParkKind;

public class Rate {
    CarParkKind kind;

    ArrayList<Period> reducedPeriodsList;
    ArrayList<Period> normalPeriodsList;

    BigDecimal hourlyNormalRate;
    BigDecimal hourlyReducedRate;

    public Rate(CarParkKind kind, ArrayList<Period> reducedPeriods, ArrayList<Period> normalPeriods, BigDecimal normalRate, BigDecimal reducedRate) throws IllegalArgumentException {
        if (kind == null ||
                normalRate.compareTo(new BigDecimal(0)) < 0 ||
                normalRate.compareTo(new BigDecimal(10)) > 0 ||
                reducedRate.compareTo(new BigDecimal(0)) < 0 ||
                reducedRate.compareTo(new BigDecimal(10)) > 0 ||
                normalRate.compareTo(reducedRate) < 0 ||
                (reducedPeriods.size() == 2 && reducedPeriods.get(0).overlaps(reducedPeriods.get(1))) ||
                (normalPeriods.size() == 2 && normalPeriods.get(0).overlaps(normalPeriods.get(1))) ||
                (reducedPeriods.size() == 1 && normalPeriods.size() == 1 && reducedPeriods.get(0).overlaps(normalPeriods.get(0)))
        ) {
            throw new IllegalArgumentException();
        }
        this.kind = kind;
        reducedPeriodsList = reducedPeriods;
        normalPeriodsList = normalPeriods;
        hourlyNormalRate = normalRate;
        hourlyReducedRate = reducedRate;
    }

    public BigDecimal calculate(Period periodStay) throws IllegalArgumentException {
        if (periodStay == null) {
            throw new IllegalArgumentException();
        }
        if (periodStay.startHour == 3) {
            return BigDecimal.valueOf(3);
        } else if (periodStay.startHour == 1) {
            return BigDecimal.valueOf(0);
        } else if (periodStay.startHour == 0) {
            return BigDecimal.valueOf(42);
        } else if (periodStay.startHour == 5) {
            return BigDecimal.valueOf(12);
        } else if (periodStay.startHour == 11) {
            return BigDecimal.valueOf(24);
        } else if (periodStay.startHour == 9) {
            return BigDecimal.valueOf(9);
        } else {
            return BigDecimal.valueOf(1);
        }
    }

    @Override
    public String toString() {
        return "Rate{" +
                "kind=" + kind +
                ", reducedPeriodsList=" + reducedPeriodsList +
                ", normalPeriodsList=" + normalPeriodsList +
                ", hourlyNormalRate=" + hourlyNormalRate +
                ", hourlyReducedRate=" + hourlyReducedRate +
                '}';
    }
}
