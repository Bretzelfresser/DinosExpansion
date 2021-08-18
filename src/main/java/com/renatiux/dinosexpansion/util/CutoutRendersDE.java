package com.renatiux.dinosexpansion.util;

import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class CutoutRendersDE {

    public static void renderCutOuts(){

        //Machine
        RenderTypeLookup.setRenderLayer(BlockInit.MORTAR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.MORTAR.get(), RenderType.getTranslucent());
    }


}
