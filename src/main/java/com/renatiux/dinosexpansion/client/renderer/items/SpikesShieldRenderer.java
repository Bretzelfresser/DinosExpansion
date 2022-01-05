package com.renatiux.dinosexpansion.client.renderer.items;

import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.client.model.items.SpikesShieldModel;
import com.renatiux.dinosexpansion.common.entities.projectiles.SpikesShieldEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SpikesShieldRenderer extends EntityRenderer<SpikesShieldEntity> {

    public static final ResourceLocation SPIKE_SHIELD = Dinosexpansion.modLoc("textures/entity/shield/spike_shield.png");
    private final SpikesShieldModel spikesShield = new SpikesShieldModel();

    protected SpikesShieldRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }

    @Override
    public ResourceLocation getEntityTexture(SpikesShieldEntity entity) {
        return null;
    }
}
