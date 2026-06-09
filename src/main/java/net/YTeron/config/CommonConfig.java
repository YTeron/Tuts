package net.YTeron.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER =new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static{
        BUILDER.push("Common TUTS");


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
