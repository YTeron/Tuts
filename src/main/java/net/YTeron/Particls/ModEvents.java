package net.YTeron.Particls;

import net.YTeron.Tuts;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tuts.MOD_ID)
public class ModEvents {

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side != LogicalSide.CLIENT)
            return;
        if (event.phase == TickEvent.Phase.END && event.player== Minecraft.getInstance().player) {
            Player player = event.player;
            Vec3 lookVec = player.getLookAngle();
            tickCounter++;
            if (tickCounter >= 80) {

                double speed = 0.2;
                double vx = lookVec.x * speed;
                double vy = lookVec.y * speed;
                double vz = lookVec.z * speed;
                double posX = player.getX();
                double posY = player.getY() + 1.45;
                double posZ = player.getZ();

                player.level().addParticle(
                        ModParticles.EXEMPLE_PARTICLE.get(),
                        posX, posY, posZ,
                        vx, vy, vz
                );
            }
            if (tickCounter >= 100) {
                tickCounter = 0;

            }
        }
    }
}