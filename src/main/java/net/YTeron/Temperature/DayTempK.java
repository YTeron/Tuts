package net.YTeron.Temperature;

import java.util.Random;

public class DayTempK {
    private static final Random RANDOM = new Random();

    public static int calculateTemperature(Integer MaxTemp, Integer MinTemp) {
        if (MaxTemp == null || MinTemp == null) {
            MaxTemp = 0;
            MinTemp = -20;
        }
        return MinTemp - 2000 + RANDOM.nextInt(MaxTemp - MinTemp + 4000 + 1);
    }
}
