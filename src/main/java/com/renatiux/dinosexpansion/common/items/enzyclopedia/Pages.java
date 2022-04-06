package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.renatiux.dinosexpansion.Dinosexpansion;
import com.renatiux.dinosexpansion.core.init.EntityTypeInit;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pages {

    public static final Page BLANK_FILLER = new Page(0,0,2,2);

    public static final List<DoublePage> PAGES = new ArrayList<>();

    public static final EntityPage ALLOSAURUS = register(new EntityPage(EntityPage.Builder.create(EntityTypeInit.ALLOSAURUS.get().create(Minecraft.getInstance().world),
            Dinosexpansion.modLoc("textures/gui/matrix.png"), 100, 100).addProperty("health", 20)));

    public static final Page TEST = register(new Page(Page.Builder.create(new TranslationTextComponent("title")).addProperty("speed", "speed_value").addProperty("Health", 20)));


    /**
     * adds pages to the {@link com.renatiux.dinosexpansion.common.items.enzyclopedia.EnzyklopediaBook} and fills them up in double pages
     * the pages will be in the same order as in the list in here
     * @param page - the page that should be registered
     * @param <T> - the more specific type of the Page
     * @return - the page that was thrown in
     */
    public static final <T extends Page> T register(T page){
        if (PAGES.size() == 0){
            PAGES.add(new DoublePage(page, BLANK_FILLER));
        }else{
            DoublePage lastPage = PAGES.get(PAGES.size() - 1);
            if (lastPage.getRightPage() == BLANK_FILLER){
                lastPage.setRight(page);
            }else{
                PAGES.add(new DoublePage(page, BLANK_FILLER));
            }
        }

        return page;
    }
}
