package com.renatiux.dinosexpansion.common.armor;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.armor.ChimerarachneArmorModel;
import com.renatiux.dinosexpansion.core.init.PotionInit;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

public class ChimerarachneArmorItem extends ArmorItem {

    private static final Map<EquipmentSlotType, BipedModel<?>> chimerarachneArmorModel = new EnumMap<>(EquipmentSlotType.class);

    public ChimerarachneArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("unchecked")
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) chimerarachneArmorModel.get(armorSlot);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (slot == EquipmentSlotType.LEGS) {
            return Dinosexpansion.ARMOR_DIR + "chimerarachne_layer_2.png";
        } else {
            return Dinosexpansion.ARMOR_DIR + "chimerarachne_layer_1.png";
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void initArmorModel() {
        chimerarachneArmorModel.put(EquipmentSlotType.HEAD, new ChimerarachneArmorModel(EquipmentSlotType.HEAD,0.5F));
        chimerarachneArmorModel.put(EquipmentSlotType.CHEST, new ChimerarachneArmorModel(EquipmentSlotType.CHEST,1.0F));
        chimerarachneArmorModel.put(EquipmentSlotType.LEGS, new ChimerarachneArmorModel(EquipmentSlotType.LEGS,0.5F));
        chimerarachneArmorModel.put(EquipmentSlotType.FEET, new ChimerarachneArmorModel(EquipmentSlotType.FEET,1.0F));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (hasFullArmor(player)) {
            player.addPotionEffect(new EffectInstance(PotionInit.CLIMB_EFFECT.get(), 250, 0));
        }
    }

    private boolean hasFullArmor(PlayerEntity player) {
        boolean hasHelmet = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof ChimerarachneArmorItem;
        boolean hasChest = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof ChimerarachneArmorItem;
        boolean hasLegs = player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() instanceof ChimerarachneArmorItem;
        boolean hasBoots = player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() instanceof ChimerarachneArmorItem;
        return hasHelmet && hasChest && hasLegs && hasBoots;
    }

}
