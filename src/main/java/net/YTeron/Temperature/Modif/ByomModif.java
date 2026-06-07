package net.YTeron.Temperature.Modif;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public class ByomModif {

    public enum ByomTemperature {
        ICE_PLAINS(-3500),
        COLD_OCEAN(-2500),
        TAIGA(-1800),
        FOREST(1000),
        PLAINS(2000),
        DESERT(4000),
        JUNGLE(5000),
        HELL(8000);

        private final int temperature;

        ByomTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getTemperature() {
            return temperature;
        }
    }

    public static int StartByom(Level level, Player player) {
        if (level == null || player == null) return 0;
        return getBiomeTemperature(level, player.blockPosition());
    }

    public static int getBiomeTemperature(Level level, BlockPos pos) {
        if (level == null || pos == null) return 0;

        try {
            // Получаем RegistryAccess и Registry биомов
            RegistryAccess registryAccess = level.registryAccess();
            Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registries.BIOME);

            // Получаем ResourceKey биома
            ResourceKey<Biome> biomeKey = level.getBiome(pos).unwrapKey().orElse(null);
            if (biomeKey == null) {
                return ByomTemperature.PLAINS.getTemperature();
            }

            // Получаем название биома из ResourceKey
            String biomeName = biomeKey.location().getPath().toUpperCase();

            return getTemperatureByBiomeName(biomeName);

        } catch (Exception e) {
            return ByomTemperature.PLAINS.getTemperature();
        }
    }

    private static int getTemperatureByBiomeName(String biomeName) {
        if (biomeName == null) {
            return ByomTemperature.PLAINS.getTemperature();
        }

        if (biomeName.contains("ICE") || biomeName.contains("FROZEN") ||
                biomeName.contains("SNOW") || biomeName.contains("GLACIER")) {
            return ByomTemperature.ICE_PLAINS.getTemperature();
        }
        if (biomeName.contains("COLD_OCEAN") || biomeName.contains("FROZEN_OCEAN")) {
            return ByomTemperature.COLD_OCEAN.getTemperature();
        }
        if (biomeName.contains("TAIGA")) {
            return ByomTemperature.TAIGA.getTemperature();
        }
        if (biomeName.contains("JUNGLE")) {
            return ByomTemperature.JUNGLE.getTemperature();
        }
        if (biomeName.contains("DESERT")) {
            return ByomTemperature.DESERT.getTemperature();
        }
        if (biomeName.contains("FOREST")) {
            return ByomTemperature.FOREST.getTemperature();
        }
        if (biomeName.contains("NETHER") || biomeName.contains("HELL")) {
            return ByomTemperature.HELL.getTemperature();
        }

        return ByomTemperature.PLAINS.getTemperature();
    }

    public static int StartByom(Level level, BlockPos pos) {
        if (level == null || pos == null) return 0;
        return getBiomeTemperature(level, pos);
    }
}