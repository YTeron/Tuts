package net.YTeron.init;

import net.YTeron.Tuts;
import net.YTeron.block.RawSapphireBlock;
import net.YTeron.block.SapphireBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> REGISTRY;

    // Item
    public static final RegistryObject<Item> SAPPHIRE;
    public static final RegistryObject<Item> RAW_SAPPHIRE;
    public static final RegistryObject<Item> FORNAR;

    // BlockItem
    public static final RegistryObject<Item> SAPPHIRE_BLOCK;
    public static final RegistryObject<Item> RAW_SAPPHIRE_BLOCK;
    public static final RegistryObject<Item> FIREBOKA_BLOCK;

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Tuts.MOD_ID);

        // Item
        SAPPHIRE = REGISTRY.register("sapphire", () -> new Item(new Item.Properties()));
        RAW_SAPPHIRE = REGISTRY.register("raw_sapphire", () -> new Item(new Item.Properties()));
        FORNAR = REGISTRY.register("fornar", () -> new Item(new Item.Properties()));

        // BlockItem
        SAPPHIRE_BLOCK = REGISTRY.register("sapphire_block",
                () -> new BlockItem(ModBlocks.SAPPHIRE_BLOCK.get(), new Item.Properties()));
        RAW_SAPPHIRE_BLOCK = REGISTRY.register("raw_sapphire_block",
                () -> new BlockItem(ModBlocks.RAW_SAPPHIRE_BLOCK.get(), new Item.Properties()));
        FIREBOKA_BLOCK = REGISTRY.register("fireboka",
                () -> new BlockItem(ModBlocks.FIREBOKA_BLOCK.get(), new Item.Properties()));
    }
}