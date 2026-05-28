package net.YTeron.weather;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CustomWeatherManager {
    private static int customRainTime = 0;
    private static boolean isWeatherActive = false;
    private static final int WEATHER_DURATION = 3000; // (или 30 для 1 секунды)

    public static void startCustomWeather(ServerLevel level) {
        if (!isWeatherActive) {
            isWeatherActive = true;
            customRainTime = WEATHER_DURATION;
            level.setWeatherParameters(0, WEATHER_DURATION, true, false);
        }
    }

    public static void stopCustomWeather(ServerLevel level) {
        if (isWeatherActive) {
            isWeatherActive = false;
            customRainTime = 0;
            level.setWeatherParameters(0, 0, false, false);
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
                // Получаем сервер и проверяем, что overworld не null
                MinecraftServer server = event.getServer();
                if (server != null) {
                    ServerLevel overworld = server.overworld();
                    if (overworld != null) {
                        stopCustomWeather(overworld);
                    } else {
                        isWeatherActive = false;
                    }
                } else {
                    isWeatherActive = false;
                }
            }
        }
    }
}