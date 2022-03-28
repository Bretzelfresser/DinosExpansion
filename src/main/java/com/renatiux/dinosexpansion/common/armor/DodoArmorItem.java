package com.renatiux.dinosexpansion.common.armor;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.armor.DodoArmorModel;
import com.renatiux.dinosexpansion.core.init.ItemInit;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

public class DodoArmorItem extends ArmorItem {

    private static final Map<EquipmentSlotType, BipedModel<?>> dodoArmorModel = new EnumMap<>(EquipmentSlotType.class);

    public DodoArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) dodoArmorModel.get(armorSlot);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return Dinosexpansion.ARMOR_DIR + "dodo_layer_1.png";
    }

    @OnlyIn(Dist.CLIENT)
    public static void initArmorModel() {
        dodoArmorModel.put(EquipmentSlotType.HEAD, new DodoArmorModel(EquipmentSlotType.HEAD,1.0F));
        dodoArmorModel.put(EquipmentSlotType.CHEST, new DodoArmorModel(EquipmentSlotType.CHEST,0.5F));
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        boolean isHelmetOn = player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ItemInit.DODO_HELMET.get();
        boolean isChestplateOn = player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ItemInit.DODO_CHESTPLATE.get();

        if(isHelmetOn&isChestplateOn){
                player.addPotionEffect(new EffectInstance(Effects.LUCK, 300, 0, false, false));

        }

    }
}
