package net.YTeron.buff;

import net.YTeron.Tuts;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffect extends MobEffect
{
    public ModEffect(MobEffectCategory category, int color) {
    super(category, color);
    }
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Tuts.MOD_ID);

    public static final RegistryObject<MobEffect> CAMPFIRE_EFFECTS = MOB_EFFECTS.register("campfire_effects",
            () -> new CampfireEffect(MobEffectCategory.NEUTRAL, 0x36ebab));
    public static final RegistryObject<MobEffect> FROZENEFFECT = MOB_EFFECTS.register("frozen_effects",
            () -> new FrozenEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}