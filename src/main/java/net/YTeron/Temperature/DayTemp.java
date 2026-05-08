package net.YTeron.Temperature;

import java.util.Random;
import net.minecraft.world.level.Level;
import net.YTeron.Temperature.Modif.DayModif;

public class DayTemp {

    private static final long dayTime = 24000;
    private static Integer[] savedForecast = null;
    private static long savedDay = -1;


    public static Integer[] Raspisan(Level level) {
        if (level == null) return new Integer[]{0, 0, 0};

        long currentDay = level.getDayTime() / dayTime;

        if (savedForecast == null) {
            savedForecast = generateNewForecast();
            savedDay = currentDay;
            return savedForecast;
        }

        if (savedDay != currentDay) {
            savedForecast = shiftForecast(savedForecast);
            savedDay = currentDay;
        }

        return savedForecast;
    }

    private static Integer[] generateNewForecast() {
        Integer[] forecast = new Integer[3];
        for (int i = 0; i < 3; i++) {
            forecast[i] = DayTempK(null, null);
        }
        return forecast;
    }

    private static Integer[] shiftForecast(Integer[] oldForecast) {
        Integer[] newForecast = new Integer[3];
        Integer MaxTemp;
        Integer MinTemp;

        MaxTemp = Math.max(oldForecast[0], Math.max(oldForecast[1], oldForecast[2]));
        MinTemp = Math.min(oldForecast[0], Math.min(oldForecast[1], oldForecast[2]));
        newForecast[0] = oldForecast[1];
        newForecast[1] = oldForecast[2];
        newForecast[2] = DayTempK(MaxTemp, MinTemp);

        return newForecast;
    }

    private static int DayTempK(Integer MaxTemp, Integer MinTemp) {
        Random dayr = new Random();
        if (MaxTemp == null || MinTemp == null) {
            MaxTemp = 0;
            MinTemp = -20;
        }
        return MinTemp - 2000 + dayr.nextInt(MaxTemp - MinTemp + 4000 + 1);
    }

    public static int StartDay(Level level) {
        return DayModif.StartDay(level);
    }
}