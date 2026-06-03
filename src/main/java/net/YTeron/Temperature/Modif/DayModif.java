package net.YTeron.Temperature.Modif;

import net.minecraft.world.level.Level;

public class DayModif {
    private static final long dayTime = 24000;

    public enum TimeDay {
        MOR(-1000),
        DAY(2000),
        EVENING(-1800),
        NIGHT(-2500),
        MID_NIGHT(-3500);

        private final int baseTemperature;
        TimeDay(int baseTemperature) {
            this.baseTemperature = baseTemperature;
        }
        public int getBaseTemperature() {
            return baseTemperature;
        }
    }

    public static int StartDay(Level level) {
        if (level == null) return 20;

        long timeOfDay = level.getDayTime() % dayTime;
        if (timeOfDay < 2000) return TimeDay.MOR.getBaseTemperature();
        else if (timeOfDay < 8000) return TimeDay.DAY.getBaseTemperature();
        else if (timeOfDay < 11000) return TimeDay.EVENING.getBaseTemperature();
        else if (timeOfDay < 14000) return TimeDay.NIGHT.getBaseTemperature();
        else return TimeDay.MID_NIGHT.getBaseTemperature();
    }
}
