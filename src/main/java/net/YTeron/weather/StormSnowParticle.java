package net.YTeron.weather;

import com.mojang.blaze3d.shaders.FogShape;
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

import static net.YTeron.weather.CustomWeatherManager.isWeatherActive;

@Mod.EventBusSubscriber(modid = "tuts", value = Dist.CLIENT)
public class StormSnowParticle {
    private static final Random random = new Random();

    // Контроль количества частиц (как в ваниле)
    private static int particleCounter = 0;
    private static final int PARTICLES_PER_TICK = 8; // Вместо 150

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        if (isWeatherActive()) {
            spawnSnowParticles(mc);
        }
    }

    private static void spawnSnowParticles(Minecraft mc) {
        ClientLevel level = mc.level;
        ParticleEngine particleEngine = mc.particleEngine;

        double camX = mc.gameRenderer.getMainCamera().getPosition().x;
        double camY = mc.gameRenderer.getMainCamera().getPosition().y;
        double camZ = mc.gameRenderer.getMainCamera().getPosition().z;

        // Ванильный снегопад: частицы спавнятся постепенно
        particleCounter++;
        int particlesThisTick = PARTICLES_PER_TICK;

        // Добавляем случайные всплески (как в ваниле)
        if (random.nextInt(20) == 0) {
            particlesThisTick += 5;
        }

        for (int i = 0; i < particlesThisTick; i++) {
            double x = camX + (random.nextDouble() - 0.5) * 24;
            double y = camY + 8 + random.nextDouble() * 20;
            double z = camZ + (random.nextDouble() - 0.5) * 24;

            // Скорость снежинок (медленнее дождя)
            double velX = (random.nextDouble() - 0.5) * 0.15;  // Минимальный горизонтальный дрейф
            double velY = -0.6 - random.nextDouble() * 0.3;    // Ванильная скорость падения
            double velZ = (random.nextDouble() - 0.5) * 0.15;

            // Иногда добавляем порывы ветра (горизонтальное смещение)
            if (random.nextInt(30) == 0) {
                velX += (random.nextDouble() - 0.5) * 0.3;
                velZ += (random.nextDouble() - 0.5) * 0.3;
            }

            particleEngine.createParticle(
                    ParticleTypes.SNOWFLAKE,
                    x, y, z, velX, velY, velZ
            );
        }

        // Сброс счетчика для предотвращения переполнения
        if (particleCounter > 20) {
            particleCounter = 0;
        }
    }

    // ========== НАСТРОЙКИ ТУМАНА (оставляем как есть) ==========

    private static float currentFogDistance = 200.0f;
    private static final float MAX_FOG_DISTANCE = 200.0f;
    private static final float MIN_FOG_DISTANCE = 30.0f;
    private static final float FOG_CHANGE_SPEED = 0.5f;

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        float targetDistance;

        if (isWeatherActive()) {
            targetDistance = MIN_FOG_DISTANCE;
        } else {
            targetDistance = MAX_FOG_DISTANCE;
        }

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
            // Белый туман (как в снежную погоду)
            event.setRed(0.95f);
            event.setGreen(0.95f);
            event.setBlue(1.0f);
        }
    }
}