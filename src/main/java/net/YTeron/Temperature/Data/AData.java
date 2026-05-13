package net.YTeron.Temperature.Data;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

public abstract class AData {
    private int cachedNumber = -1;
    private boolean isLoaded = false;
    private final String dataKey;
    private final int defaultValue;

    protected AData(String key, int defaultValue) {
        this.dataKey = key;
        this.defaultValue = defaultValue;
    }

    public int getOrCreateNumber(Level level) {
        if (isLoaded) {
            return cachedNumber;
        }

        if (level == null || level.isClientSide || !(level instanceof ServerLevel serverLevel)) {
            return -1;
        }

        SaveDataTuts saveData = serverLevel.getDataStorage().computeIfAbsent(
                SaveDataTuts::load,
                SaveDataTuts::create,
                dataKey
        );

        int savedValue = saveData.getSavedDay();

        if (savedValue == -1) {
            cachedNumber = defaultValue;
            saveData.setSavedDay(cachedNumber);
            saveData.setDirty();
            isLoaded = true;
            return cachedNumber;
        }

        cachedNumber = savedValue;
        isLoaded = true;
        return cachedNumber;
    }

    public void setValue(Level level, int newValue) {
        cachedNumber = newValue;
        isLoaded = true;

        if (level instanceof ServerLevel serverLevel) {
            SaveDataTuts saveData = serverLevel.getDataStorage().computeIfAbsent(
                    SaveDataTuts::load,
                    SaveDataTuts::create,
                    dataKey
            );
            saveData.setSavedDay(newValue);
            saveData.setDirty();
        }
    }

    public int getValue() {
        return cachedNumber;
    }

    protected void resetCache() {
        isLoaded = false;
        cachedNumber = -1;
    }
}