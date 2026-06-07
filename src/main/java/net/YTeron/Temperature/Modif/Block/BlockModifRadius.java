package net.YTeron.Temperature.Modif.Block;

import net.YTeron.buff.ModEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.RegistryObject;

public class BlockModifRadius {

    static void hasBlockAddBufVanila(Level world, BlockPos center, int radius, Block targetBlock,
                                     MobEffect effect, int duration, int amplifier) {
        if (world == null || center == null || targetBlock == null || effect == null || world.isClientSide) {
            return;
        }

        boolean blockFound = false;

        outerLoop:
        for (int x = -radius; x <= radius; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = center.offset(x, y, z);
                    BlockState state = world.getBlockState(checkPos);

                    if (state.getBlock() == targetBlock) {
                        blockFound = true;
                        break outerLoop;
                    }
                }
            }
        }

        if (blockFound) {
            AABB area = new AABB(center).inflate(radius);
            world.getEntitiesOfClass(Player.class, area)
                    .forEach(p -> p.addEffect(new MobEffectInstance(effect, duration, amplifier)));
        }
    }
    static void hasBlockAddBufMods(Level world, BlockPos center, int radius, Block targetBlock,
                                   RegistryObject<MobEffect> effectRegistry, int duration, int amplifier) {
        // Проверка параметров
        if (world == null || center == null || targetBlock == null || effectRegistry == null || world.isClientSide) {
            return;
        }

        // Извлекаем эффект из RegistryObject
        MobEffect effect = effectRegistry.get();
        if (effect == null) {
            return;
        }

        // Поиск целевого блока в области
        boolean blockFound = false;

        outerLoop:
        for (int x = -radius; x <= radius; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = center.offset(x, y, z);
                    BlockState state = world.getBlockState(checkPos);

                    if (state.getBlock() == targetBlock) {
                        blockFound = true;
                        break outerLoop;
                    }
                }
            }
        }

        if (blockFound) {
            AABB area = new AABB(center).inflate(radius);
            world.getEntitiesOfClass(Player.class, area)
                    .forEach(p -> p.addEffect(new MobEffectInstance(effect, duration, amplifier)));
        }
    }
}
