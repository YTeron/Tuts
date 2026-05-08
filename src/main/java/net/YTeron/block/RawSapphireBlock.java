package net.YTeron.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class RawSapphireBlock extends Block {
    public RawSapphireBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(5.0f, 6.0f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
    }
}
