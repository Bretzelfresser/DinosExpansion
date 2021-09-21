package com.renatiux.dinosexpansion.common.armor;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.armor.SteelArmorModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

public class SteelArmorItem extends ArmorItem {

    private static final Map<EquipmentSlotType, BipedModel<?>> steelArmorModel = new EnumMap<>(EquipmentSlotType.class);

    public SteelArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) steelArmorModel.get(armorSlot);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (slot == EquipmentSlotType.LEGS) {
            return Dinosexpansion.ARMOR_DIR + "steel_layer_2.png";
        } else {
            return Dinosexpansion.ARMOR_DIR + "steel_layer_1.png";
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void initArmorModel() {
        steelArmorModel.put(EquipmentSlotType.HEAD, new SteelArmorModel(EquipmentSlotType.HEAD,0.75F));
        steelArmorModel.put(EquipmentSlotType.CHEST, new SteelArmorModel(EquipmentSlotType.CHEST,0.5F));
        steelArmorModel.put(EquipmentSlotType.LEGS, new SteelArmorModel(EquipmentSlotType.LEGS,0.4F));
        steelArmorModel.put(EquipmentSlotType.FEET, new SteelArmorModel(EquipmentSlotType.FEET,0.5F));
    }
}
