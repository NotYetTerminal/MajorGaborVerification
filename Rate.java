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

    public Rate(CarParkKind kind, ArrayList<Period> reducedPeriods, ArrayList<Period> normalPeriods, BigDecimal normalRate, BigDecimal reducedRate) {

    }

    public BigDecimal calculate(Period periodStay) {
        return BigDecimal.valueOf(1);
    }
}
