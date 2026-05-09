package net.YTeron.Temperature.Data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class SaveDataTuts extends SavedData {

    private static final String DATA_KEY = "temperature_data";

    // Данные, которые мы сохраняем
    private int[] temperatures = new int[]{0, 0, 0};
    private long savedDay = -1;  // ← поле для хранения дня
    public SaveDataTuts() {

    }
    // Создание новых данных (когда файла нет)
    public static SaveDataTuts create()
    {
        return new SaveDataTuts();
    }

    // Загрузка существующих данных (когда файл есть)
    public static SaveDataTuts load(CompoundTag tag) {
        SaveDataTuts data = new SaveDataTuts();
        data.temperatures = tag.getIntArray("temperatures");
        data.savedDay = tag.getLong("saveday");
        if (data.temperatures.length != 3) {
            data.temperatures = new int[]{0, 0, 0};  // защита от ошибок
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putIntArray("temperatures", temperatures);
        tag.putLong("saveday", savedDay);
        return tag;
    }

    public int[] getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(int[] temperatures) {
        this.temperatures = temperatures;
        setDirty();
    }
    public long getSavedDay() {
        return savedDay;
    }

    public void setSavedDay(long savedDay) {
        this.savedDay = savedDay;
        setDirty();
    }
}