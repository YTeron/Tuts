package net.YTeron.Temperature.Modif;

import net.minecraft.world.entity.player.Player;


import java.util.function.Function;

public class BloockModif {

    public enum BlockM {

        FIRE(
                player -> 100,
                player -> 2.0f
        ),
        PLANKS(
                player -> 30,
                player -> 1.2f
        ),
        SNOW(
                player -> 40,
                player -> 1.0f
        ),
        ICE(
                player -> -150,
                player -> 0.8f
        ),
        ROCK(
                player -> -5,
                player -> 0.6f
        ),
        DEEPF(
                player -> -5,
                player -> 1f
        ),
        SNOW_PLANT(
                player -> -105,
                player -> 1f
        ),
        AIR(
                player -> -15,
                player -> 1f
        );

        private final Function<Player, Integer> basetemperature;
        private final Function<Player, Float> temperatureModifier;

        BlockM(Function<Player, Integer> basetemperature, Function<Player, Float> temperatureModifier) {
            this.basetemperature = basetemperature;
            this.temperatureModifier = temperatureModifier;
        }

        public int getBaseTemperature(Player player) {
            return basetemperature.apply(player);
        }

        public float getTemperatureModifier(Player player) {
            return temperatureModifier.apply(player);
        }
    }

    public static BlockM getBlockType(String block) {
        return switch (block) {
            case "planks", "oak_planks", "spruce_planks", "birch_planks",
                 "jungle_planks", "acacia_planks", "dark_oak_planks",
                 "mangrove_planks", "cherry_planks", "bamboo_planks" -> BlockM.PLANKS;
            case "snow_block" -> BlockM.SNOW;
            case "ice", "packed_ice", "blue_ice", "frosted_ice" -> BlockM.ICE;
            case "stone", "cobblestone", "andesite", "diorite", "granite",
                 "stone_bricks", "rock" -> BlockM.ROCK;
            case "fire", "torch", "wall_torch",
                 "campfire", "furnace", "blast_furnace",
                 "smoker", "lava", "flowing_lava", "magma_block", "lantern" -> BlockM.FIRE;
            case "snow" -> BlockM.SNOW_PLANT;
            case "air", "cave_air"-> BlockM.AIR;
            default -> BlockM.DEEPF;
        };
    }

}
