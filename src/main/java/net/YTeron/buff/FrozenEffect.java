package net.YTeron.buff;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class FrozenEffect extends MobEffect {
    private static final String SPEED_MODIFIER_ID = "c4e5f6a7-8b9c-0d1e-2f3a-4b5c6d7e8f9a";

    public FrozenEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);

        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                SPEED_MODIFIER_ID,
                -0.2,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            double newX = entity.getDeltaMovement().x * 0.8;
            double newZ = entity.getDeltaMovement().z * 0.8;

            if (newX > 0.2) newX = 0.2;
            if (newZ < -0.2) newZ = -0.2;

            entity.setDeltaMovement(newX, entity.getDeltaMovement().y, newZ);
        }
        super.applyEffectTick(entity, amplifier);
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}