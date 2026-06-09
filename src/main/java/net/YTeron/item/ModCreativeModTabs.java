package net.YTeron.item;

import net.YTeron.Tuts;
import net.YTeron.armor.ModArmorItems;
import net.YTeron.init.ModBlocks;
import net.YTeron.init.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.example.registry.ItemRegistry;


public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tuts.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.DIAMOND))
                    .title(Component.translatable("creativetab.tutorial_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.FORNAR.get());

                        pOutput.accept(ModBlocks.ICEGLASS.get());
                        pOutput.accept(ModBlocks.OBOGREV2.get());
                        pOutput.accept(ModBlocks.GENERATOR.get());

                        pOutput.accept(ModArmorItems.CUSTOM_HELMET.get());
                        pOutput.accept(ModArmorItems.CUSTOM_CHESTPLATE.get());
                        pOutput.accept(ModArmorItems.CUSTOM_LEGGINGS.get());
                        pOutput.accept(ModArmorItems.CUSTOM_BOOTS.get());
                        pOutput.accept(ModArmorItems.CLRON_HELMET.get());
                        pOutput.accept(ModArmorItems.CLRON_CHESTPLATE.get());
                        pOutput.accept(ModArmorItems.CLRON_LEGGINGS.get());
                        pOutput.accept(ModArmorItems.CLRON_BOOTS.get());

                        pOutput.accept(Items.DIAMOND);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}