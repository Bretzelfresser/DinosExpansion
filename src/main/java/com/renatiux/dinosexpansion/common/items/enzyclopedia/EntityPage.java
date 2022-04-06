package com.renatiux.dinosexpansion.common.items.enzyclopedia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EntityPage extends Page{

    protected final Entity entity;
    protected final ResourceLocation entityTexture;
    private final int textWidth, textHeight;

    /**
     * this constructor is for registering pages, the x y and width and height r set in the screen
     */
    public EntityPage(Builder builder){
        super(builder.subBuilder);
        this.entity = builder.entity;
        this.entityTexture = builder.entityTexture;
        this.textHeight = builder.textWidth;
        this.textWidth = builder.textHeight;

    }

    public EntityPage(int x, int y, int width, int height, Entity entity, ResourceLocation entityTexture, int textWidth, int textHeight) {
        super(x, y, width, height, entity.getDisplayName());
        this.entity = entity;
        this.entityTexture = entityTexture;
        this.textHeight = textHeight;
        this.textWidth = textWidth;
    }

    @Override
    protected void renderPage(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        mc.textureManager.bindTexture(entityTexture);
        AbstractGui.blit(stack, x, textStartY, 0,0,textWidth, textHeight, textWidth, textHeight);
        this.textStartY += this.textHeight + 4;
        super.renderPage(stack, mouseX, mouseY, partialTicks);

    }

    public Entity getEntity() {
        return entity;
    }

    public static class Builder{

        public static Builder create(Entity entity, ResourceLocation entityTexture, int textWidth, int textHeight){
            return new Builder(entity, entityTexture, textWidth, textHeight);
        }

        private final Entity entity;
        private final ResourceLocation entityTexture;
        private final int textWidth, textHeight;
        private final Page.Builder subBuilder;


        private Builder(Entity entity, ResourceLocation entityTexture, int textWidth, int textHeight) {
            this.entity = entity;
            this.entityTexture = entityTexture;
            this.textWidth = textWidth;
            this.textHeight = textHeight;
            subBuilder = Page.Builder.create(entity.getDisplayName());
        }

        public Builder addProperty(String translationKeyProperty, String translationKeyValue){
            return addProperty(new TranslationTextComponent(translationKeyProperty), new TranslationTextComponent(translationKeyValue));
        }

        public Builder addProperty(String translationKeyProperty, int value){
            return addProperty(new TranslationTextComponent(translationKeyProperty), new StringTextComponent("" + value));
        }

        public Builder addProperty(ITextComponent property, ITextComponent value){
            this.subBuilder.addProperty(property, value);
            return this;
        }
    }
}
