package com.renatiux.dinosexpansion.common.entities.controller;

import com.renatiux.dinosexpansion.common.entities.environment.Xenocranium;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

public class InEarthPathNavigator extends PathNavigator {

    private final Xenocranium xeno;
    public InEarthPathNavigator(Xenocranium entityIn, World worldIn) {
        super(entityIn, worldIn);
        this.xeno = entityIn;
    }

    @Override
    protected PathFinder getPathFinder(int searchDepth) {
        this.nodeProcessor = new WalkNodeProcessor();
        this.nodeProcessor.setCanSwim(true);
        return new PathFinder(this.nodeProcessor, searchDepth);
    }

    @Override
    protected Vector3d getEntityPosition() {
        return new Vector3d(this.xeno.getPosX(), this.xeno.getPosY() + 0.5d, this.xeno.getPosZ());
    }

    @Override
    protected boolean canNavigate() {
        return this.xeno.isInEarth();
    }

    @Override
    protected boolean isDirectPathBetweenPoints(Vector3d posVec31, Vector3d posVec32, int sizeX, int sizeY, int sizeZ) {
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(posVec31, posVec32, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, entity));
        if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
            return entity.world.getBlockState(new BlockPos(raytraceresult.getHitVec())).isIn(Tags.Blocks.DIRT);
        }
        return false;
    }


    @Override
    protected void pathFollow() {
        Vector3d position = this.getEntityPosition();
        float tolerance = 0.65F;

        if (position.squareDistanceTo(this.currentPath.getVectorFromIndex(this.entity, this.currentPath.getCurrentPathIndex())) < (double) tolerance) {
            this.currentPath.incrementPathIndex();
        }

        for (int j = Math.min(this.currentPath.getCurrentPathIndex() + 6, this.currentPath.getCurrentPathLength() - 1); j > this.currentPath.getCurrentPathIndex(); --j) {
            Vector3d pathVectorFromIndex = this.currentPath.getVectorFromIndex(this.entity, j);

            if (pathVectorFromIndex.squareDistanceTo(position) <= 36.0D && this.isDirectPathBetweenPoints(position, pathVectorFromIndex, 0, 0, 0)) {
                this.currentPath.setCurrentPathIndex(j);
                break;
            }
        }

        this.checkForStuck(position);
    }

    @Override
    public boolean canEntityStandOnPos(BlockPos pos) {
        return this.world.getBlockState(pos).isSolid();
    }
}
