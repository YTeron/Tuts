package net.YTeron.Temperature;

import net.minecraft.world.entity.player.Player;
import java.util.function.Function;

public class BiomsModif {
    public enum BiomsM {
        SNOWY(
                player -> -150,
                player -> 1.0f
        ),

        ICEP(
                player -> -1150,
                player -> 0.8f

        ),

           FROZEN(
                player -> -150,
                player -> 0.6f
        ),

        DEEPF(
                player -> 0,
                player -> 0.4f

        );
        private final Function<Player, Float> temperatureModifier;
        private  final Function<Player, Integer> basetemperature;
        BiomsM(
               Function<Player, Integer> basetemperature,
               Function<Player, Float> temperatureModifier) {
            this.temperatureModifier = temperatureModifier;
            this.basetemperature = basetemperature;

        }

        public int getbasetemperature(Player player) {
            return basetemperature.apply(player);
        }
        public float getTemperatureModifier(Player player) {
            return temperatureModifier.apply(player);
        }

    }
    public static BiomsM BiomeType(Player player) {
        if (player == null || player.level() == null) return null;

        var biome = player.level().getBiome(player.blockPosition());
        var biomeKey = biome.unwrapKey().orElse(null);

        if (biomeKey == null) return null;

        String biomeName = biomeKey.location().getPath();

        return switch (biomeName) {
            case "snowy_plains", "snowy_taiga", "snowy_beach", "snowy_slopes",
                  "grove" -> BiomsM.SNOWY;


            case "ice_spikes", "frozen_peaks", "jagged_peaks" -> BiomsM.ICEP;


            case "frozen_ocean", "frozen_river" -> BiomsM.FROZEN;

            default -> BiomsM.DEEPF;
        };
    }

}
