package net.YTeron.weather;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static net.YTeron.weather.CustomWeatherManager.isWeatherActive;

@Mod.EventBusSubscriber(modid = "tuts", value = Dist.CLIENT)
public class StormSnowParticle {
    private static final Random random = new Random();

    // ========== ЧАСТИЦЫ ==========

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        // Проверяем, активна ли кастомная погода
        if (isWeatherActive()) {
            spawnCustomRainParticles(mc);
        }
    }

    private static void spawnCustomRainParticles(Minecraft mc) {
        ClientLevel level = mc.level;
        ParticleEngine particleEngine = mc.particleEngine;

        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        for (int i = 0; i < 150; i++) {
            double x = camX + (random.nextDouble() - 0.5) * 30;
            double y = camY + 8+ (random.nextDouble() ) * 15;
            double z = camZ + (random.nextDouble() - 0.5) * 30;

            particleEngine.createParticle(
                    ParticleTypes.SNOWFLAKE,
                    x, y, z, (random.nextDouble() - 0.5) * 0.9, -1.7, (random.nextDouble() - 0.5) * 0.9
            );

        }
    }

    private static float currentFogDistance = 200.0f;
    private static final float MAX_FOG_DISTANCE = 200.0f;
    private static final float MIN_FOG_DISTANCE = 30.0f; // Сильный туман
    private static final float FOG_CHANGE_SPEED = 0.5f; // Скорость изменения

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        float targetDistance;

        if (isWeatherActive()) {
            targetDistance = MIN_FOG_DISTANCE;
        } else {
            targetDistance = MAX_FOG_DISTANCE;
        }

        // Плавное приближение к целевой дистанции
        if (Math.abs(currentFogDistance - targetDistance) > 0.1f) {
            if (currentFogDistance < targetDistance) {
                currentFogDistance += FOG_CHANGE_SPEED;
                if (currentFogDistance > targetDistance) {
                    currentFogDistance = targetDistance;
                }
            } else if (currentFogDistance > targetDistance) {
                currentFogDistance -= FOG_CHANGE_SPEED;
                if (currentFogDistance < targetDistance) {
                    currentFogDistance = targetDistance;
                }
            }
        }

        event.setNearPlaneDistance(0.0f);
        event.setFarPlaneDistance(currentFogDistance);
        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        if (isWeatherActive()) {
            event.setRed(1.0f);   // красный компонент (максимум)
            event.setGreen(1.0f); // зелёный компонент (максимум)
            event.setBlue(1.0f);  // синий компонент (максимум)
        }
    }
}