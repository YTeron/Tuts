package net.YTeron.Temperature.Modif;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class ArmorModif {
    public enum ArmorM {
        CUSTOM(player -> 3500, player -> 2f),
        DEEPF(player -> 0, player -> 1f);

        private final Function<Player, Integer> basetemperature;
        private final Function<Player, Float> temperatureModifier;

        ArmorM(Function<Player, Integer> basetemperature,
               Function<Player, Float> temperatureModifier) {
            this.basetemperature = basetemperature;
            this.temperatureModifier = temperatureModifier;
        }

        public float getBaseTemperature(Player player) {
            return basetemperature.apply(player);
        }

        public float getTemperatureModifier(Player player) {
            return temperatureModifier.apply(player);
        }
    }

    public static ArmorM getArmorType(ItemStack armorStack) {
        if (armorStack.isEmpty()) {
            return ArmorM.DEEPF;
        }

        String itemPath = ForgeRegistries.ITEMS.getKey(armorStack.getItem()).getPath();

        return switch (itemPath) {
            case "custom_helmet", "custom_chestplate", "custom_leggings", "custom_boots" -> ArmorM.CUSTOM;
            default -> ArmorM.DEEPF;
        };
    }


    public static int getTotalArmorTemperature(Player player) {
        int totalTemp = 0;

        for (ItemStack armorStack : player.getArmorSlots()) {
            ArmorM armorType = getArmorType(armorStack);
            totalTemp += armorType.getBaseTemperature(player);
            totalTemp += armorType.getTemperatureModifier(player);
        }

        return totalTemp;
    }
}