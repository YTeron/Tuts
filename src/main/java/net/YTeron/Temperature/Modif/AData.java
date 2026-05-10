package net.YTeron.Temperature.Modif;

import net.YTeron.Temperature.Data.SaveDataTuts;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

public abstract class AData {

    private static int cachedNumber = -1;
    private static boolean isLoaded = false;
    private static String Pname = "temperature_data";
    private static int defaultValue = 0;

    // Защищённые методы для наследников
    protected static void setDataKey(String key) {
        if (!key.equals(Pname)) {
            Pname = key;
            resetCache();
        }
    }

    protected static void setDefaultValue(int value) {
        defaultValue = value;
    }

    public static int getOrCreateNumber(Level level) {
        if (isLoaded) {
            return cachedNumber;
        }

        if (level == null || level.isClientSide || !(level instanceof ServerLevel serverLevel)) {
            return -1;
        }

        SaveDataTuts saveData = serverLevel.getDataStorage().computeIfAbsent(
                SaveDataTuts::load,
                SaveDataTuts::create,
                Pname
        );

        int savedValue = saveData.getSavedDay();

        // Если нет сохранённого значения (savedValue == -1)
        if (savedValue == -1) {
            cachedNumber = defaultValue;
            saveData.setSavedDay(cachedNumber);
            saveData.setDirty();
            isLoaded = true;
            return cachedNumber;
        }

        // Загружаем сохранённое значение
        cachedNumber = savedValue;
        isLoaded = true;
        return cachedNumber;
    }

    public static void resetCache() {
        isLoaded = false;
        cachedNumber = -1;
    }

    public static void setValue(Level level, int newValue) {
        cachedNumber = newValue;
        isLoaded = true;

        if (level instanceof ServerLevel serverLevel) {
            SaveDataTuts saveData = serverLevel.getDataStorage().computeIfAbsent(
                    SaveDataTuts::load,
                    SaveDataTuts::create,
                    Pname
            );
            saveData.setSavedDay(newValue);
            saveData.setDirty();
        }
    }

    public static int getValue() {
        return cachedNumber;
    }
}
