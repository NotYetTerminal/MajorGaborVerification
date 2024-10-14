// Author: Gábor Major
// Creation date: 2024-10-11
// Description: Dummy Period class

package cm;

public class Period {
    int startHour;
    int endHour;

    public Period(int start, int end) throws IllegalArgumentException {
        if (start < 0 || start >= 24 || end <= 0 || end > 24) {
            throw new IllegalArgumentException();
        }
        startHour = start;
        endHour = end;
    }

    public boolean overlaps(Period period) {
        return (period.endHour > startHour || period.startHour < endHour);
    }

    public int duration() {
        return endHour - startHour;
    }
}
