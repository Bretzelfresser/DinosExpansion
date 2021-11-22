package com.renatiux.dinosexpansion.common.tileEntities;

import com.renatiux.dinosexpansion.common.blocks.machine.PrehistoricBed;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import net.minecraft.item.DyeColor;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PrehistoricBedTileEntity extends TileEntity {
    private DyeColor color;

    public PrehistoricBedTileEntity() {
        super(TileEntityTypesInit.PREHISTORIC_BED.get());
    }

    public PrehistoricBedTileEntity(DyeColor colorIn) {
        this();
        this.setColor(colorIn);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 11, this.getUpdateTag());
    }

    @OnlyIn(Dist.CLIENT)
    public DyeColor getColor() {
        if (this.color == null) {
            this.color = ((PrehistoricBed) this.getBlockState().getBlock()).getColor();
        }
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
