package net.YTeron.weather;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CustomWeatherManager {
    private static int customRainTime = 0;
    private static boolean isWeatherActive = false;
    private static final int WEATHER_DURATION = 100; // (или 30 для 1 ctreyls)

    public static void startCustomWeather(ServerLevel level) {
        if (!isWeatherActive) {
            isWeatherActive = true;
            customRainTime = WEATHER_DURATION;
            level.setWeatherParameters(0, WEATHER_DURATION, true, false);
            System.out.println("[Погода] Буря началась! Длительность: " + (WEATHER_DURATION / 20) + " сек");
        }
    }

    public static void stopCustomWeather(ServerLevel level) {
        if (isWeatherActive) {
            isWeatherActive = false;
            customRainTime = 0;
            level.setWeatherParameters(0, 0, false, false);
            System.out.println("[Погода] Буря остановлена!");
        }
    }

    public static boolean isWeatherActive() {
        return isWeatherActive;
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        if (isWeatherActive) {
            customRainTime--;
            if (customRainTime % 20 == 0) {
                System.out.println("[Погода] Осталось: " + (customRainTime / 20) + " сек");
            }

            if (customRainTime <= 0) {
                ServerLevel level = event.getServer().overworld();
                stopCustomWeather(level);
            }
        }
    }
}