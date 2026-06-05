package net.YTeron.Temperature.Modif.Block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class BlocksS {
    public static void Vaila(Level world, Player player) {
        if (world.isClientSide) {
            return;
        }

        BlockPos playerPos = player.blockPosition();
        BlockModifRadius.hasBlockAddBuf(world, playerPos, 5, Blocks.CAMPFIRE);
    }
}