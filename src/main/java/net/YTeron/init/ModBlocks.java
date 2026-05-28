package net.YTeron.init;

import net.YTeron.Tuts;
import net.YTeron.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> REGISTRY;
    public static final RegistryObject<Block> SAPPHIRE_BLOCK;
    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK;
    public static final RegistryObject<Block> FIREBOKA_BLOCK;
    public static final RegistryObject<Block> OBOGREV2;
    public static final RegistryObject<Block> GENERATOR;

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Tuts.MOD_ID);

        SAPPHIRE_BLOCK = REGISTRY.register("sapphire_block", () -> new SapphireBlock());
        RAW_SAPPHIRE_BLOCK = REGISTRY.register("raw_sapphire_block", () -> new RawSapphireBlock());
        FIREBOKA_BLOCK = REGISTRY.register("fireboka",()->new Fireboka());
        OBOGREV2 = REGISTRY.register("obogrev",()-> new Obogrev2());
        GENERATOR = REGISTRY.register("generator",()-> new Generator());
    }
}