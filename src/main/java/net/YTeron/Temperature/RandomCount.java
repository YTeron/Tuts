package net.YTeron.Temperature;

import net.YTeron.Temperature.Data.AData;
import net.minecraft.world.level.Level;
import java.util.Random;

public class RandomCount extends AData {
    private static final Random RANDOM = new Random();
    private static RandomCount instance;
    public RandomCount() {
        super("random_count_data", 0);
    }

    public static RandomCount getInstance() {
        if (instance == null) {
            instance = new RandomCount();
        }
        return instance;
    }

    public static int randomInRange(int min, int max) {
        return min + RANDOM.nextInt(max - min + 1);
    }

    public int getOrCreateRandomNumber(Level level, boolean yu) {
        int value = getOrCreateNumber(level);

        if (value == 0 || yu) {
            int newValue = randomInRange(1, 1000);
            setValue(level, newValue);
            return newValue;
        }
        return value;
    }
}