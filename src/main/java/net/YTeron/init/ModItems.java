package net.YTeron.init;

import net.YTeron.Tuts;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> REGISTRY;

    // Item
    public static final RegistryObject<Item> FORNAR;

    // BlockItem
    public static final RegistryObject<Item> OBOGREV2;
    public static final RegistryObject<Item> GENERATOR;
    public static final RegistryObject<Item> ICEGLASS;

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Tuts.MOD_ID);

        // Item
        FORNAR = REGISTRY.register("fornar", () -> new Item(new Item.Properties()));

        // BlockItem
        OBOGREV2 = REGISTRY.register("obogrev",
                () -> new BlockItem(ModBlocks.OBOGREV2.get(), new Item.Properties()));
        GENERATOR = REGISTRY.register("generator",
                () -> new BlockItem(ModBlocks.GENERATOR.get(), new Item.Properties()));
        ICEGLASS = REGISTRY.register("iceglass",
                () -> new BlockItem(ModBlocks.ICEGLASS.get(), new Item.Properties()));
    }
}