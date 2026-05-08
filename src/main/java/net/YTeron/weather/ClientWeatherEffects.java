package net.YTeron.weather;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = "yourmodid", value = Dist.CLIENT)
public class ClientWeatherEffects  {

    private static final Random random = new Random();
    private static boolean isStormActive = false;

    // ========== ЧАСТИЦЫ (ВАШ КОД) ==========

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        // Проверяем, должна ли идти буря
        isStormActive = shouldStartStorm(level);

        if (isStormActive) {
            spawnStormParticles(mc);
        }
    }

    private static void spawnStormParticles(Minecraft mc) {
        ClientLevel level = mc.level;
        ParticleEngine particleEngine = mc.particleEngine;

        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        for (int i = 0; i < 30; i++) {
            double x = camX + (random.nextDouble() - 0.5) * 20;
            double y = camY + random.nextDouble() * 20;
            double z = camZ + (random.nextDouble() - 0.5) * 20;

            particleEngine.createParticle(
                    ParticleTypes.DRIPPING_WATER,
                    x, y, z, 0, -0.1, 0
            );
        }
    }

    private static boolean shouldStartStorm(Level level) {
        // Ваша логика (температура, биом, день и т.д.)
        return true;  // сейчас всегда true
    }


    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        if (!isStormActive) return;

        // Просто устанавливаем дистанции тумана
        // Без проверки FogType!
        event.setNearPlaneDistance(0.0f);
        event.setFarPlaneDistance(40.0f);
        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    // ========== ЦВЕТ ТУМАНА (БЕЗ FOGTYPE) ==========

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        if (!isStormActive) return;

        // Серо-синий цвет тумана
        event.setRed(0.3f);
        event.setGreen(0.35f);
        event.setBlue(0.5f);
    }
}