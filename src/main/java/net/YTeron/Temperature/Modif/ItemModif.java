package net.YTeron.Temperature.Modif;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class ItemModif {
    public enum ItemM {
        SNOWY(player -> -50),
        ICE(player -> -150),
        TORCH(player -> +100),
        DEEPF(player -> 0);

        private final Function<Player, Integer> basetemperature;

        ItemM(Function<Player, Integer> basetemperature) {
            this.basetemperature = basetemperature;
        }

        public int getbasetemperature(Player player) {
            return basetemperature.apply(player);
        }
    }

    public static ItemM getItemBasedOnHand(Player player) {
        ItemStack mainHandItem = player.getMainHandItem();


        if (mainHandItem.isEmpty()) {
            return  ItemM.DEEPF;
        }


        if (mainHandItem.is(Items.SNOWBALL) ||
                mainHandItem.is(Items.SNOW_BLOCK) ||
                mainHandItem.is(Items.POWDER_SNOW_BUCKET)) {
            return ItemM.SNOWY;
        }


        if (mainHandItem.is(Items.ICE) ||
                mainHandItem.is(Items.PACKED_ICE) ||
                mainHandItem.is(Items.BLUE_ICE)) {
            return ItemM.ICE;
        }

        if (mainHandItem.is(Items.TORCH) ||
                mainHandItem.is(Items.LANTERN)||
        mainHandItem.is(Items.LAVA_BUCKET))
        {
            return ItemM.TORCH;
        }

        return ItemM.DEEPF;
    }
}