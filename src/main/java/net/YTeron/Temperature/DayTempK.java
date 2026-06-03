package net.YTeron.Temperature;

import java.util.Random;

public class DayTempK {
    private static final Random RANDOM = new Random();
    private static final int count = 500;
    public static int calculateTemperature(Integer Temp) {

        boolean result = RANDOM.nextInt(3) == 0;
        if (result)
            Temp+=hightt();
        else
            Temp+=loww();
        return Temp;

    }
    private static int loww()
    {
        boolean resultt = RANDOM.nextInt(4) == 0;
        return resultt ? -count*2 : -count;
    }
    private static int hightt()
    {
        boolean resultt = RANDOM.nextInt(4) == 0;
        return resultt ? count*2 : count;
    }
}
