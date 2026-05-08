package net.YTeron.armor;


import net.YTeron.Tuts;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModArmorItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tuts.MOD_ID);

    // Шлем
    public static final RegistryObject<GeckoArmorItem> CUSTOM_HELMET =
            ITEMS.register("custom_helmet",
                    () -> new GeckoArmorItem(ArmorMaterials.DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));

    // Нагрудник
    public static final RegistryObject<GeckoArmorItem> CUSTOM_CHESTPLATE =
            ITEMS.register("custom_chestplate",
                    () -> new GeckoArmorItem(ArmorMaterials.DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    // Поножи
    public static final RegistryObject<GeckoArmorItem> CUSTOM_LEGGINGS =
            ITEMS.register("custom_leggings",
                    () -> new GeckoArmorItem(ArmorMaterials.DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    // Ботинки
    public static final RegistryObject<GeckoArmorItem> CUSTOM_BOOTS =
            ITEMS.register("custom_boots",
                    () -> new GeckoArmorItem(ModArmorMaterials.CARCLE, ArmorItem.Type.BOOTS, new Item.Properties()));
}