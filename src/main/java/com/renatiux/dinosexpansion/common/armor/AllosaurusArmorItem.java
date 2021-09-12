package com.renatiux.dinosexpansion.common.armor;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.armor.AllosaurusArmorModel;
import com.renatiux.dinosexpansion.client.model.armor.ChimerarachneArmorModel;
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

public class AllosaurusArmorItem extends ArmorItem {

    private static final Map<EquipmentSlotType, BipedModel<?>> allosaurusArmorModel = new EnumMap<>(EquipmentSlotType.class);

    public AllosaurusArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) allosaurusArmorModel.get(armorSlot);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (slot == EquipmentSlotType.LEGS) {
            return Dinosexpansion.ARMOR_DIR + "allosaurus_layer_2.png";
        } else {
            return Dinosexpansion.ARMOR_DIR + "allosaurus_layer_1.png";
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void initArmorModel() {
        allosaurusArmorModel.put(EquipmentSlotType.HEAD, new AllosaurusArmorModel(EquipmentSlotType.HEAD,0.5F));
        allosaurusArmorModel.put(EquipmentSlotType.CHEST, new AllosaurusArmorModel(EquipmentSlotType.CHEST,0.5F));
        allosaurusArmorModel.put(EquipmentSlotType.LEGS, new AllosaurusArmorModel(EquipmentSlotType.LEGS,0.4F));
        allosaurusArmorModel.put(EquipmentSlotType.FEET, new AllosaurusArmorModel(EquipmentSlotType.FEET,0.5F));
    }
}
