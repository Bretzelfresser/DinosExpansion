package com.renatiux.dinosexpansion.client.renderer.entities;

import com.renatiux.dinosexpansion.client.model.entities.creatures.AstorgosuchusModel;
import com.renatiux.dinosexpansion.client.renderer.DinosuarRenderer;
import com.renatiux.dinosexpansion.common.entities.dinosaurs.Astorgosuchus;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class AstorgosuchusRender extends DinosuarRenderer<Astorgosuchus> {
    public AstorgosuchusRender(EntityRendererManager renderManager) {
        super(renderManager, new AstorgosuchusModel());
    }

}
