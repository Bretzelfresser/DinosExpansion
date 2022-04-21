package com.renatiux.dinosexpansion.common.tileEntities;

import com.renatiux.dinosexpansion.core.init.PotionInit;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public class RadiationTileEntity extends TileEntity implements ITickableTileEntity {
    public RadiationTileEntity() {
        super(TileEntityTypesInit.RADIATION_TE.get());
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            if (this.getBlockState().getBlock() instanceof IRadiationBlock) {
                IRadiationBlock radiation = (IRadiationBlock) this.getBlockState().getBlock();
                List<LivingEntity> entities = this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.getPos()).grow(radiation.getDistance()));
                for (LivingEntity entity : entities) {
                    double distance = this.getPos().distanceSq(entity.getPositionVec(), true);
                    int radiationAmp = (int) map(distance, 0, radiation.getDistance(), radiation.getMinRadiation(), radiation.getMaxRadiation());
                    entity.addPotionEffect(new EffectInstance(PotionInit.RADIATION.get(), 10, radiationAmp));
                    System.out.println(radiationAmp);
                }
            }
        }
    }

    /**
     * @param valueToMap the value that has to be mapped from [a, b] to [c, d]
     * @param oldMin the lower border of the first interval
     * @param oldMax the higher border of the first interval
     * @param newMin the lower border of the second interval
     * @param newMax the higher border of the second interval
     * @return the new mapped value
     */
    public static final double map(double valueToMap, double oldMin, double oldMax, double newMin, double newMax) {
        double oldRange = oldMax - oldMin;
        double newRange = newMax - newMin;
        return (((valueToMap - oldMin) * newRange) / oldRange) + newMin;
    }

    public static interface IRadiationBlock {
        int getMaxRadiation();

        int getMinRadiation();

        double getDistance();
    }
}
