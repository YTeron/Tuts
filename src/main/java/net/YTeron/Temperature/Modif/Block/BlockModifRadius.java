package net.YTeron.Temperature.Modif.Block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class BlockModifRadius {

    static void hasBlockAddBuf(Level world, BlockPos center, int radius, Block targetBlock) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = center.offset(x, y, z);
                    BlockState state = world.getBlockState(checkPos);

                    if (state.getBlock() == targetBlock) {
                        world.getEntitiesOfClass(Player.class, new AABB(checkPos).inflate(radius))
                                .forEach(p -> p.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1)));
                        return;
                    }
                }
            }
        }
    }
}
