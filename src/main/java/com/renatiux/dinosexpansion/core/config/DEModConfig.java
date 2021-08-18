package com.renatiux.dinosexpansion.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class DEModConfig {

    public static BoomerangConfig BOOMERANGCONFIG;

    public static ForgeConfigSpec init(ForgeConfigSpec.Builder builder) {

        BOOMERANGCONFIG = new BoomerangConfig(builder);

        return builder.build();
    }

public static class BoomerangConfig {

        public final ForgeConfigSpec.BooleanValue turnAroundItem;
        public final ForgeConfigSpec.BooleanValue turnAroundMob;
        public final ForgeConfigSpec.BooleanValue turnAroundButton;
        public final ForgeConfigSpec.IntValue WoodBoomerangRange;
        public final ForgeConfigSpec.IntValue WoodBoomerangDamage;
        public final ForgeConfigSpec.BooleanValue WoodBoomerangFollows;
        public final ForgeConfigSpec.IntValue IronBoomerangRange;
        public final ForgeConfigSpec.IntValue IronBoomerangDamage;
        public final ForgeConfigSpec.BooleanValue IronBoomerangFollows;
        public final ForgeConfigSpec.IntValue DiamondBoomerangRange;
        public final ForgeConfigSpec.IntValue DiamondBoomerangDamage;
        public final ForgeConfigSpec.BooleanValue DiamondBoomerangFollows;
        public final ForgeConfigSpec.BooleanValue breaksTorches;
        public final ForgeConfigSpec.BooleanValue breaksFlowers;
        public final ForgeConfigSpec.BooleanValue breaksGrass;
        public final ForgeConfigSpec.BooleanValue breaksTallGrass;
        public final ForgeConfigSpec.BooleanValue activatesLevers;
        public final ForgeConfigSpec.BooleanValue activatesButtons;
        public final ForgeConfigSpec.BooleanValue activatesPressurePlates;
        public final ForgeConfigSpec.BooleanValue activatesTripWire;

        public BoomerangConfig(ForgeConfigSpec.Builder builder) {

            builder.comment("Boomerang Config Options").push("Boomerang");
            turnAroundItem = builder.comment("Comes back to the player after picking up items.").define("turnAroundItem", true);
            turnAroundMob = builder.comment("Comes back to the player after hitting a mob.").define("turnAroundMob", true);
            turnAroundButton = builder.comment("Comes back to player after hitting a button.").define("turnAroundButton", true);
            WoodBoomerangRange = builder.comment("The maximum range of travel before returning to player.").defineInRange("WoodBoomerangRange", 30, 1, 200);
            WoodBoomerangDamage = builder.comment("The amount of damage that is done when hitting any living entity.").defineInRange("WoodBoomerangDamage", 5, 1, 500);
            WoodBoomerangFollows = builder.comment("The Wood Boomerang will follow your mouse till it hits it's range limit.").define("WoodBoomerangFollows", true);
            IronBoomerangRange = builder.comment("The maximum range of travel before returning to player.").defineInRange("IronBoomerangRange", 30, 1, 200);
            IronBoomerangDamage = builder.comment("The amount of damage that is done when hitting any living entity.").defineInRange("IronBoomerangDamage", 5, 1, 500);
            IronBoomerangFollows = builder.comment("The Iron Boomerang will follow your mouse till it hits it's range limit.").define("IronBoomerangFollows", true);
            DiamondBoomerangRange = builder.comment("The maximum range of travel before returning to player.").defineInRange("DiamondBoomerangRange", 30, 1, 200);
            DiamondBoomerangDamage = builder.comment("The amount of damage that is done when hitting any living entity.").defineInRange("DiamondBoomerangDamage", 5, 1, 500);
            DiamondBoomerangFollows = builder.comment("The Diamond Boomerang will follow your mouse till it hits it's range limit.").define("DiamondBoomerangFollows", true);
            breaksTorches = builder.comment("Can boomerang break torches.").define("breaksTorches", true);
            breaksFlowers = builder.comment("Can boomerang break Flowers.").define("breaksFlowers", true);
            breaksGrass = builder.comment("Can boomerang break Glass.").define("breaksGrass", true);
            breaksTallGrass = builder.comment("Can boomerang break Tall Grass.").define("breaksTallGrass", true);
            activatesLevers = builder.comment("Can boomerang switch levers on and off.").define("activatesLevers", true);
            activatesButtons = builder.comment("Can boomerang activate/push buttons.").define("activatesButtons", true);
            activatesPressurePlates = builder.comment("Can boomerang activate regular and lightweight pressure plates.").define("activatesPressurePlates", true);
            activatesTripWire = builder.comment("Can boomerang activate/trigger tripwire(s).").define("activatesTripWire", true);
            builder.pop();
        }

    }
}
