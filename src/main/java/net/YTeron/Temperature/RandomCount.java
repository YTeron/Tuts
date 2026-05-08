package net.YTeron.Temperature;

import net.YTeron.Temperature.Data.SaveDataTuts;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import java.util.Random;

public class RandomCount {

    private static final Random RANDOM = new Random();

    // Кэш для хранения загруженного значения
    private static int cachedNumber = -1;
    private static boolean isLoaded = false;

    public static int randomInRange(int min, int max) {
        return min + RANDOM.nextInt(max - min + 1);
    }

    public static int getOrCreateRandomNumber(Level level) {
        // Если значение уже загружено в кэш - возвращаем его
        if (isLoaded) {
            return cachedNumber;
        }

        // Работаем только на стороне сервера
        if (level == null || level.isClientSide || !(level instanceof ServerLevel serverLevel)) {
            return -1;
        }

        // Получаем доступ к хранилищу данных
        SaveDataTuts saveData = serverLevel.getDataStorage().computeIfAbsent(
                SaveDataTuts::load,
                SaveDataTuts::create,
                "temperature_data"
        );

        long savedValue = saveData.getSavedDay();

        // Если значение -1 или 0 (нет сохранённого) - создаём новое
        if (savedValue <= 0) {
            cachedNumber = randomInRange(1, 1000);
            saveData.setSavedDay(cachedNumber);
            saveData.setDirty();
            serverLevel.getDataStorage().save();
            isLoaded = true;
            return cachedNumber;
        }

        // Загружаем сохранённое значение в кэш
        cachedNumber = (int) savedValue;
        isLoaded = true;
        return cachedNumber;
    }

    // Опционально: метод для сброса кэша (при перезагрузке мира)
    public static void resetCache() {
        isLoaded = false;
        cachedNumber = -1;
    }
}