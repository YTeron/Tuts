package net.YTeron;

import com.mojang.logging.LogUtils;
import net.YTeron.Particls.ModParticles;
import net.YTeron.armor.ModArmorItems;
import net.YTeron.init.ModBlocks;
import net.YTeron.init.ModItems;
import net.YTeron.item.ModCreativeModTabs;
import net.YTeron.weather.CustomWeatherManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(Tuts.MOD_ID)
public class Tuts {
    public static final String MOD_ID = "tuts";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Tuts() {
        GeckoLib.initialize();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // ========== РЕГИСТРАЦИЯ КОНТЕНТА ==========
        ModCreativeModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModBlocks.REGISTRY.register(modEventBus);
        ModItems.REGISTRY.register(modEventBus);
        ModParticles.PARTICLE_TYPES.register(modEventBus);
        ModArmorItems.ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(CustomWeatherManager.class);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Мод {} загружен!", Tuts.MOD_ID);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Клиентская часть мода {} загружена!", Tuts.MOD_ID);
        }

        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.EXEMPLE_PARTICLE.get(),
                    net.YTeron.Particls.ExempleParticls.Provider::new);
        }
    }
}