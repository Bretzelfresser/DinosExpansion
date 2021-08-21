package com.renatiux.dinosexpansion.util;

import com.renatiux.dinosexpansion.core.init.BlockInit;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class CutoutRendersDE {

    public static void renderCutOuts(){

        //Machine
        RenderTypeLookup.setRenderLayer(BlockInit.MORTAR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.MORTAR.get(), RenderType.getTranslucent());

        //Flower
        RenderTypeLookup.setRenderLayer(BlockInit.LAVENDER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.LEMON_VERBENA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ARCHAEOSIGILLARIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.CEPHALOTAXUS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.DILLHOFFIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.EPHEDRA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.OSMUNDA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.SARRACENIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.VACCINIUM.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ZAMITES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.WELWITSCHIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.PACHYPODA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.HORSETAIL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.FOOZIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.DUISBERGIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.BENNETTITALES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.CRATAEGUS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.FLORISSANTIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.AMORPHOPHALLUS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.TEMPSKYA.get(), RenderType.getCutout());


        //Sapling
        RenderTypeLookup.setRenderLayer(BlockInit.REDWOOD_SAPLING.get(), RenderType.getCutout());


        //Leaves
        RenderTypeLookup.setRenderLayer(BlockInit.REDWOOD_LEAVES.get(), RenderType.getCutout());
    }


}
