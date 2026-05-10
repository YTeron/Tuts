package net.YTeron.Temperature;

import net.YTeron.Temperature.Modif.AData;
import net.minecraft.world.level.Level;
import java.util.Random;

public class RandomCount extends AData {
    private static long lastTime = -1;
    private static boolean ye =false;

    private static final Random RANDOM = new Random();

    static {
        // Настройка для RandomCount
        setDataKey("random_count_data");  // свой файл сохранения
        setDefaultValue(0);                // значение по умолчанию
    }

    public static int randomInRange(int min, int max) {
        return min + RANDOM.nextInt(max - min + 1);
    }

    public static int getOrCreateRandomNumber(Level level) {
        int value = getOrCreateNumber(level);
        long currentDay = level.getDayTime();
        if (lastTime > currentDay) {
            lastTime = currentDay;
            ye=true;
        }
        if (ye) {
            int newValue = randomInRange(1, 1000);
            setValue(level, newValue);
            ye =false;
            return newValue;
        }
        if (value == 0) {
            int newValue = randomInRange(1, 1000);
            setValue(level, newValue);
            return newValue;
        }
        lastTime = currentDay;
        return value;
    }
}