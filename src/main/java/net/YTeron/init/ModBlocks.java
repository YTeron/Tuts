package net.YTeron.init;

import net.YTeron.Tuts;
import net.YTeron.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> REGISTRY;
    //16 16
    public static final RegistryObject<Block> ICEGLASS;
    //another
    public static final RegistryObject<Block> OBOGREV2;
    public static final RegistryObject<Block> GENERATOR;

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Tuts.MOD_ID);
        ICEGLASS = REGISTRY.register("iceglass",()-> new IceGlass());

        OBOGREV2 = REGISTRY.register("obogrev",()-> new Obogrev2());
        GENERATOR = REGISTRY.register("generator",()-> new Generator());
    }
}