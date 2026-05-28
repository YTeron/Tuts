package net.YTeron.armor;

import net.YTeron.Tuts;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ArmorClronRenderer extends GeoArmorRenderer<GeckoArmorItem> {

    public ArmorClronRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(Tuts.MOD_ID, "armor/armor_clron")) {
            @Override
            public ResourceLocation getTextureResource(GeckoArmorItem animatable) {
                return new ResourceLocation(Tuts.MOD_ID, "textures/models/armor/armor_clron.png");
            }
        });
    }
}