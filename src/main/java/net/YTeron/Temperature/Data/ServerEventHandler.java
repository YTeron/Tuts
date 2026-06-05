package net.YTeron.Temperature.Data;

import net.YTeron.Temperature.DayTemp;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "tuts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {

        DayTemp.getInstance("daytemp_data1").resetCache();
        DayTemp.getInstance("daytemp_data2").resetCache();
        DayTemp.getInstance("daytemp_data3").resetCache();
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        DayTemp.getInstance("daytemp_data1").resetCache();
        DayTemp.getInstance("daytemp_data2").resetCache();
        DayTemp.getInstance("daytemp_data3").resetCache();
    }
}