package net.YTeron.item;

import net.YTeron.Temperature.TemperatureEnd;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.YTeron.init.ModItems.FORNAR;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Fornar {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(FORNAR.get(),
                    new ResourceLocation("tuts", "temp_state"),
                    (itemStack, clientLevel, livingEntity, seed) -> {
                        if (livingEntity instanceof Player player) {
                            int Ftemp = TemperatureEnd.currentTemperature;

                            if (Ftemp >= 7000) {
                                return 0.0f;
                            } else if (Ftemp >= 1000) {
                                return 0.5f;
                            } else {
                                return 1.0f;
                            }
                        }
                        return 0.0f;
                    });
        });
    }
}