package net.YTeron.Temperature;

import net.YTeron.Temperature.Modif.ArmorModif;
import net.YTeron.Temperature.Modif.BloockModif;
import net.YTeron.Temperature.Modif.DayModif;
import net.YTeron.Temperature.Modif.ItemModif;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.YTeron.Temperature.BiomsModif.BiomeType;
import static net.YTeron.Temperature.InbaseChecker.*;


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
                    Component.literal("Температура: " + lastTemperature),
                    true
            );
            ticks = 0;
        }
    }

    public static int GetFinalTemperature(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        boolean hasblock = getDistanceToBlockUp(player) != -1;
        BiomsModif.BiomsM biomeType = BiomeType(player);
        ItemModif.ItemM itemType = ItemModif.getItemBasedOnHand(player);
        Level level = event.player.level();
        int armorTemp = ArmorModif.getTotalArmorTemperature(player);
        int baseTempB = biomeType.getbasetemperature(player);
        int baseTempI = itemType.getbasetemperature(player);
        int tempblock = InbaseChecker.BlockR(5, player);
        int dayMoidif = DayModif.StartDay(level);
        int dayTemp = DayTemp.Raspisan(level)[0];

        int Ftemp = baseTempI + baseTempB + armorTemp + tempblock + dayTemp+ dayMoidif;

        boolean nav = IPB(player);
        if (nav) {
            Ftemp += 2000;
        } else if (hasblock) {
            Ftemp += 1000;
        }

        return Ftemp;
    }
}
