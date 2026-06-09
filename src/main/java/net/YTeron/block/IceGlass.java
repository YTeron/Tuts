package net.YTeron.block;

import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class IceGlass extends GlassBlock {
    public static final Properties PROPERTIES = Properties.of()
            .copy(Blocks.GLASS)
            .mapColor(MapColor.COLOR_RED)
            .strength(3.0f, 10.0f)
            .sound(SoundType.GLASS)
            .noOcclusion();

    public IceGlass() {
        super(PROPERTIES);
    }
}