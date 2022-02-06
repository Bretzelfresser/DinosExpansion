package com.renatiux.dinosexpansion.common.armor;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.armor.AllosaurusArmorModel;
import com.renatiux.dinosexpansion.client.model.armor.AstorgosuchusArmorModel;
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

public class AstorgosuchusChestplateItem extends ArmorItem {

    private static final Map<EquipmentSlotType, BipedModel<?>> astorgosuchusArmorModel = new EnumMap<>(EquipmentSlotType.class);

    public AstorgosuchusChestplateItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) astorgosuchusArmorModel.get(armorSlot);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
            return Dinosexpansion.ARMOR_DIR + "astorgosuchus_layer_1.png";
    }

    @OnlyIn(Dist.CLIENT)
    public static void initArmorModel() {
        astorgosuchusArmorModel.put(EquipmentSlotType.CHEST, new AstorgosuchusArmorModel(EquipmentSlotType.CHEST,0.5F));
    }
}
