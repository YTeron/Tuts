package net.YTeron.Temperature;

import net.YTeron.Temperature.Data.AData;
import net.minecraft.world.level.Level;
import net.YTeron.Temperature.Modif.DayModif;

public class DayTemp extends AData {

    private static final long dayTime = 24000;
    private static DayTemp instance1;
    private static DayTemp instance2;
    private static DayTemp instance3;
    private static DayTemp day;


    public DayTemp(String key, int defaultValue) {
        super(key, defaultValue);
    }

    public static DayTemp getInstance(String key) {
        if ("daytemp_data1".equals(key)) {
            if (instance1 == null) instance1 = new DayTemp(key, 0);
            return instance1;
        } else if ("daytemp_data2".equals(key)) {
            if (instance2 == null) instance2 = new DayTemp(key, 0);
            return instance2;
        } else if ("daytemp_data3".equals(key)) {
            if (instance3 == null) instance3 = new DayTemp(key, 0);
            return instance3;
        }else if ("saveday".equals(key)) {
            if (day == null) day = new DayTemp(key, 0);
            return day;
        }

        return null;
    }

    private static void initInstances() {
        getInstance("daytemp_data1");
        getInstance("daytemp_data2");
        getInstance("daytemp_data3");
        getInstance("saveday");

    }

    public static Integer[] Raspisan(Level level) {
        if (level == null) return new Integer[]{0, 0, 0};

        initInstances();

        long currentDay = level.getDayTime() / dayTime;

        // Всегда читаем из storage
        int temp0 = instance1.getOrCreateNumber(level);
        int temp1 = instance2.getOrCreateNumber(level);
        int temp2 = instance3.getOrCreateNumber(level);

        if (temp0 == 0 && temp1 == 0 && temp2 == 0) {
            Integer[] newForecast = generateNewForecast();
            instance1.setValue(level, newForecast[0]);
            instance2.setValue(level, newForecast[1]);
            instance3.setValue(level, newForecast[2]);
            day.setValue(level, (int)currentDay);
            return newForecast;
        }

        long savedDay = getSavedDay(level);
        if (savedDay != currentDay) {
            Integer[] shifted = shiftForecast(new Integer[]{temp0, temp1, temp2});
            instance1.setValue(level, shifted[0]);
            instance2.setValue(level, shifted[1]);
            instance3.setValue(level, shifted[2]);
            day.setValue(level, (int)currentDay);
            return shifted;
        }

        return new Integer[]{temp0, temp1, temp2};
    }

    private static long getSavedDay(Level level) {
        return day.getOrCreateNumber(level);
    }

    private static Integer[] generateNewForecast() {
        Integer[] forecast = new Integer[3];
        for (int i = 0; i < 3; i++) {
            forecast[i] = DayTempK.calculateTemperature(0);
        }
        return forecast;
    }

    private static Integer[] shiftForecast(Integer[] oldForecast) {
        Integer[] newForecast = new Integer[3];
        newForecast[0] = oldForecast[1];
        newForecast[1] = oldForecast[2];
        newForecast[2] = DayTempK.calculateTemperature(oldForecast[2]);
        return newForecast;
    }
}