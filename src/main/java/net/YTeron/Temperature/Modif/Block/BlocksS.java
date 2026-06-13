package net.YTeron.Temperature.Modif.Block;

import net.YTeron.buff.ModEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class BlocksS {
    public static void Vaila(Level world, Player player) {
        if (world.isClientSide) {
            return;
        }
        BlockPos playerPos = player.blockPosition();
        BlockModifRadius.hasBlockAddBufMods(
                world,           // Level world
                playerPos,       // BlockPos center
                5,               // int radius
                Blocks.CAMPFIRE, // Block targetBlock
                ModEffect.CAMPFIRE_EFFECTS,  // MobEffect effect
                160,             // int duration (тики)
                1,                // int amplifier (уровень)
                player
        );

    }
}