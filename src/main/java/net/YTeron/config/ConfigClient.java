package net.YTeron.config;

import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

public class ConfigClient
{
    public static final ForgeConfigSpec.Builder BUILDER =new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> TEMPUTEREIZMER;

    static{
        BUILDER.push("Config for TUTS");

        TEMPUTEREIZMER =BUILDER.comment("what type of temperature measurement")
                .define("C",true);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
