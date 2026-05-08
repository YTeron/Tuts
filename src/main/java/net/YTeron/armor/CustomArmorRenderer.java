package net.YTeron.armor;
import net.YTeron.Tuts;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CustomArmorRenderer extends GeoArmorRenderer<GeckoArmorItem> {

    public CustomArmorRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(Tuts.MOD_ID, "armor/custom_armor")) {
            @Override
            public ResourceLocation getTextureResource(GeckoArmorItem animatable) {
                return new ResourceLocation(Tuts.MOD_ID, "textures/models/armor/custom_armor.png");
            }
        });
    }
}