// Author: Gábor Major
// Creation date: 2024-10-11
// Description: JUnit5 file for testing the Rate and Period classes

package cm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MajorGaborTestTask1 {
    private final cm.Rate rate = new cm.Rate();
    private final cm.Period period = new cm.Period();

    @Test
    void duration() {
        assertEquals(1, period.duration());
    }
}
