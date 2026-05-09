package net.YTeron.Temperature;

import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "tuts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        RandomCount.resetCache();
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        RandomCount.resetCache();
    }
}