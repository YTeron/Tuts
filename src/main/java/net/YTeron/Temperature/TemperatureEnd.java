package net.YTeron.Temperature;

import net.YTeron.Temperature.Modif.ArmorModif;
import net.YTeron.Temperature.Modif.BloockModif;
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
    public static int currentTemperature = 0;

    @SubscribeEvent
    public static void end(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        ticks++;
        if (ticks >= 15) {
            ticks = 0;
            boolean hasblock = false;
            if (getDistanceToBlockUp(player) !=-1)
                hasblock = true;
            BiomsModif.BiomsM biomeType = BiomeType(player);
            ItemModif.ItemM itemType = ItemModif.getItemBasedOnHand(player);
            Level level = event.player.level();
            int armorTemp = ArmorModif.getTotalArmorTemperature(player);
            int baseTempB = biomeType.getbasetemperature(player);
            int baseTempI = itemType.getbasetemperature(player);
            int tempblock =InbaseChecker.BlockR(5,player);
            int daytemp = DayTemp.StartDay(level);
            int day1 = DayTemp.Raspisan(level)[0];
            int day2 = DayTemp.Raspisan(level)[1];
            int day3 = DayTemp.Raspisan(level)[2];
            int number = RandomCount.getInstance().getOrCreateRandomNumber(level, hasblock);

            boolean nav = IPB(player);
            int Ftemp = baseTempI +baseTempB+armorTemp+tempblock+daytemp;
            if (nav && BlockRF(5,player)!=0) {
                Ftemp += 10000;
            }
            else if (nav) {
                Ftemp += 4000;
            }
            else if (hasblock) {
                Ftemp += 1000;
            }
//            BlockRF(5,player)
            currentTemperature= Ftemp;
            player.getPersistentData().putInt("currentTemperature", Ftemp);

            switch (biomeType) {
                case SNOWY, ICEP, FROZEN, DEEPF ->
                    player.displayClientMessage(
                            Component.literal("Температура: " + day3+ " "+day2+" " +day1+" " + number),
                            true
                    );
                default -> {}
            }

        }

    }
}

