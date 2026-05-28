package net.YTeron.armor;

import net.YTeron.Tuts;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModArmorItems {

    // Используем ОДИН DeferredRegister для всех предметов
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tuts.MOD_ID);

    // ===== Броня CUSTOM =====
    public static final RegistryObject<GeckoArmorItem> CUSTOM_HELMET =
            ITEMS.register("custom_helmet",
                    () -> new GeckoArmorItem(ArmorMaterials.DIAMOND, ArmorItem.Type.HELMET, new Item.Properties(), "custom"));

    public static final RegistryObject<GeckoArmorItem> CUSTOM_CHESTPLATE =
            ITEMS.register("custom_chestplate",
                    () -> new GeckoArmorItem(ArmorMaterials.DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "custom"));

    public static final RegistryObject<GeckoArmorItem> CUSTOM_LEGGINGS =
            ITEMS.register("custom_leggings",
                    () -> new GeckoArmorItem(ArmorMaterials.DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties(), "custom"));

    public static final RegistryObject<GeckoArmorItem> CUSTOM_BOOTS =
            ITEMS.register("custom_boots",
                    () -> new GeckoArmorItem(ModArmorMaterials.CARCLE, ArmorItem.Type.BOOTS, new Item.Properties(), "custom"));

    // ===== Броня CLRON =====
    public static final RegistryObject<GeckoArmorItem> CLRON_HELMET =
            ITEMS.register("clron_helmet",
                    () -> new GeckoArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new Item.Properties(), "clron"));

    public static final RegistryObject<GeckoArmorItem> CLRON_CHESTPLATE =
            ITEMS.register("clron_chestplate",
                    () -> new GeckoArmorItem(ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties(), "clron"));

    public static final RegistryObject<GeckoArmorItem> CLRON_LEGGINGS =
            ITEMS.register("clron_leggings",
                    () -> new GeckoArmorItem(ArmorMaterials.IRON, ArmorItem.Type.LEGGINGS, new Item.Properties(), "clron"));

    public static final RegistryObject<GeckoArmorItem> CLRON_BOOTS =
            ITEMS.register("clron_boots",
                    () -> new GeckoArmorItem(ModArmorMaterials.CARCLE, ArmorItem.Type.BOOTS, new Item.Properties(), "clron"));
}