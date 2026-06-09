package net.YTeron.Temperature;

import net.YTeron.Temperature.Modif.ArmorModif;
import net.YTeron.Temperature.Modif.Block.BlocksS;
import net.YTeron.Temperature.Modif.DayModif;
import net.YTeron.Temperature.Modif.ItemModif;
import net.YTeron.buff.ModEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.YTeron.Temperature.BiomsModif.BiomeType;
import static net.YTeron.Temperature.InbaseChecker.*;
import static net.YTeron.Temperature.Modif.Block.BlocksS.Vaila;


@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class TemperatureEnd {
    private static int ticks = 0;
    private static final int UPDATE_DELAY = 20;
    public static int lastTemperature = 0;

    @SubscribeEvent
    public static void end(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
        ticks++;

        if (ticks >= UPDATE_DELAY) {
            int Ftemp = GetFinalTemperature(event);
            lastTemperature = Ftemp / 1000;
            Player player = event.player;
            player.displayClientMessage(
                    Component.literal("Температура C: " + lastTemperature),
                    true
            );
            ticks = 0;
        }
        if (1==2) {
            int Ftemp = GetFinalTemperature(event);
            lastTemperature = Ftemp / 1000;
            Player player = event.player;
            player.displayClientMessage(
                    Component.literal("Температура F: " + lastTemperature),
                    true
            );
            ticks = 0;
        }
    }
    public static int GetDaytemp(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        Level level = event.player.level();
        int dayTemp = DayTemp.Raspisan(level)[0];
        return dayTemp;
    }
    public static int GetFinalTemperature(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        boolean hasblock = getDistanceToBlockUp(player) != -1;
        BiomsModif.BiomsM biomeType = BiomeType(player);
        ItemModif.ItemM itemType = ItemModif.getItemBasedOnHand(player);
        Level level = event.player.level();
        BlocksS.Vaila(player.level(), player);
        int armorTemp = ArmorModif.getTotalArmorTemperature(player);
        int baseTempB = biomeType.getbasetemperature(player);
        int baseTempI = itemType.getbasetemperature(player);
        int dayMoidif = DayModif.StartDay(level);
        int dayTemp = DayTemp.Raspisan(level)[0];

        int Ftemp = baseTempI + baseTempB + armorTemp + dayTemp+ dayMoidif;

        boolean nav = IPB(player);
        if (nav) {
            Ftemp += 2000;
        } else if (hasblock) {
            Ftemp += 1000;
        }
        if (player.hasEffect(ModEffect.CAMPFIRE_EFFECTS.get())) {
            Ftemp += 15000;
        }
        return Ftemp;
    }
}
