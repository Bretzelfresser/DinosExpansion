package com.renatiux.dinosexpansion.core.config;

import com.renatiux.dinosexpansion.common.tribes.Tribe;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Random;

public class DEModConfig {

    public static final Random random = new Random();
    public static BoomerangConfig BOOMERANGCONFIG;
    public static ToolsConfig TOOL_CONFIG;
    public static IncubatorConfig INCUBATOR_CONFIG;
    public static ShieldsConfig SHIELD_CONFIG;
    public static ItemsConfig ITEMS_CONFIG;
    public static EffectConfig EFFECT_CONFIG;
    public static TribeConfig TRIBE_CONFIG;

    public static ForgeConfigSpec init(ForgeConfigSpec.Builder builder) {

        BOOMERANGCONFIG = new BoomerangConfig(builder);
        TOOL_CONFIG = new ToolsConfig(builder);
        INCUBATOR_CONFIG = new IncubatorConfig(builder);
        SHIELD_CONFIG = new ShieldsConfig(builder);
        ITEMS_CONFIG = new ItemsConfig(builder);
        EFFECT_CONFIG = new EffectConfig(builder);
        TRIBE_CONFIG = new TribeConfig(builder);
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
            breaksGrass = builder.comment("Can boomerang break Grass.").define("breaksGrass", true);
            breaksTallGrass = builder.comment("Can boomerang break Tall Grass.").define("breaksTallGrass", true);
            activatesLevers = builder.comment("Can boomerang switch levers on and off.").define("activatesLevers", true);
            activatesButtons = builder.comment("Can boomerang activate/push buttons.").define("activatesButtons", true);
            activatesPressurePlates = builder.comment("Can boomerang activate regular and lightweight pressure plates.").define("activatesPressurePlates", true);
            activatesTripWire = builder.comment("Can boomerang activate/trigger tripwire(s).").define("activatesTripWire", true);
            builder.pop();
        }

    }

    public static class ToolsConfig {

        public final ForgeConfigSpec.IntValue hammerDuraLossMulti;
        public final ForgeConfigSpec.IntValue greataxeDuraLossMulti;
        public final ForgeConfigSpec.IntValue excavatorDuraLossMulti;

        public final ForgeConfigSpec.BooleanValue weaponfireability;

        public ToolsConfig(ForgeConfigSpec.Builder builder) {

            builder.comment(" Dinos Expansion Tool config \n These multipliers affect the amount of damage tools take per use.").push("Tool");
            hammerDuraLossMulti = builder.defineInRange("HammerDuraLossMulti", 2, 1, Integer.MAX_VALUE);
            greataxeDuraLossMulti = builder.defineInRange("GreataxeDuraLossMulti", 3, 1, Integer.MAX_VALUE);
            excavatorDuraLossMulti = builder.defineInRange("ExcavatorDuraLossMulti", 2, 1, Integer.MAX_VALUE);
            weaponfireability = builder.comment("Fire Ability").define("Turn on the enemy",true);
            builder.pop();
        }
    }

    public static class IncubatorConfig{
        public final ForgeConfigSpec.IntValue maxEnergyConsumnerPerEgg;
        public final ForgeConfigSpec.IntValue incubatorSpeedMultiplier;

        public IncubatorConfig(ForgeConfigSpec.Builder builder){
            builder.comment("configs of the incubator").push("Incubator");
            maxEnergyConsumnerPerEgg = builder.defineInRange("MaxEnergyPerEgg", 120, 1, Integer.MAX_VALUE);
            incubatorSpeedMultiplier = builder.defineInRange("SpeedMultiplayer", 1, 1, 10);
            builder.pop();
        }
    }

    public static final class EffectConfig{
        //deathblow
        public final ForgeConfigSpec.DoubleValue attackDamageLoss;
        public final ForgeConfigSpec.DoubleValue slownessLoss;
        //bleeding
        public final ForgeConfigSpec.IntValue ticksBeforeDamage;


        public EffectConfig(ForgeConfigSpec.Builder builder){
            builder.push("Death Blow");
            builder.comment("how much attack damage u loose per level of effect");
            attackDamageLoss = builder.defineInRange("attack damage Loss", 2d, 0d, 10d);
            builder.comment("slowness loss per level of effect");
            slownessLoss = builder.defineInRange("slowness", -0.2, -1, 1);
            builder.pop().push("Bleeding");
            builder.comment("how mch ticks there r between the damage of the bleeding");
            ticksBeforeDamage = builder.defineInRange("ticksBetweenBleeding", 20, 1, Integer.MAX_VALUE);


        }
    }

    public static class ShieldsConfig{
        public final ForgeConfigSpec.IntValue hullBreakerCooldown;
        public final ForgeConfigSpec.DoubleValue hullbreakerKnockbackMultiplier;
        public final ForgeConfigSpec.IntValue spikesShieldDurabilityLoss;

        public final ForgeConfigSpec.DoubleValue spikesShieldVelocity;
        public final ForgeConfigSpec.IntValue hullbreakerDurabilityLoss;

        public final ForgeConfigSpec.IntValue heavyShieldBaseCooldownOnGround;
        public final ForgeConfigSpec.IntValue heavyShieldCooldownPushAway;
        public final ForgeConfigSpec.BooleanValue canBeInfinte;

        public ShieldsConfig(ForgeConfigSpec.Builder builder){
            builder.comment("configs of the Shields").push("Hullbreaker");
            builder.comment("defines how much durability one knockback of the shield costs");
            hullbreakerDurabilityLoss = builder.defineInRange("Durability Loss", 3, 1, 100);
            hullBreakerCooldown = builder.defineInRange("Hullbreaker knockback Cooldown in ticks", 200, 20, Integer.MAX_VALUE);
            hullbreakerKnockbackMultiplier = builder.defineInRange("Hullbreaker Knockback multiplier", 1d, 0d, 10d);
            builder.pop();
            builder.push("Spikes Shield");
            builder.comment("this determines how fast teh spikes Shield fly when thrown \n the default value is the same as the trident, when set to zero u actually cant throw it");
            spikesShieldVelocity = builder.defineInRange("spikes shield velocity", 2d, 0d, 10d);
            builder.comment("defines how much durability one throw of the shield costs");
            spikesShieldDurabilityLoss = builder.defineInRange("Durability Loss", 3, 1, 100);
            builder.pop();
            builder.comment("Configs of the Heavy Shield").push("HeavyShield");
            builder.comment("defines how long u can put ur heavy shield in the ground \n may be higher with enchantments \n in ticks");
            heavyShieldBaseCooldownOnGround = builder.defineInRange("base time", 200, 20, Integer.MAX_VALUE);
            builder.comment("this values defines how long it takes until u can place the shield again and entities get pushed away");
            heavyShieldCooldownPushAway =  builder.defineInRange("pushAwayCooldown", 600, 20, Integer.MAX_VALUE);
            builder.comment("defines whether when u have ShieldStrength 5 on this shied u can put ur heavyShield for ever in ground or not");
            canBeInfinte = builder.define("infinite", true);
            builder.pop();
        }
    }

    public static final class TribeConfig{
        public final ForgeConfigSpec.IntValue playerPerTribe;
        public final ForgeConfigSpec.IntValue maxTribes;

        public TribeConfig(ForgeConfigSpec.Builder builder){
            builder.push("Tribes");
            builder.comment("defines how many player at a maximum can join a tribe", "0 means that there is no max Value");
            playerPerTribe = builder.defineInRange("maxPlayerPerTribe", 0, 5, 100);
            maxTribes = builder.defineInRange("maxTribes", 10, 0, 30);
            builder.pop();
        }
    }

    public static final class ItemsConfig{
        public final ForgeConfigSpec.IntValue medikitCooldown;

        public ItemsConfig(ForgeConfigSpec.Builder builder){
            builder.comment("all configuration for Items").push("Items");
            this.medikitCooldown = builder.defineInRange("the cooldown of the medikits after they got used in seconds", 20, 1, Integer.MAX_VALUE);
            builder.pop();
        }
    }
}
