package com.renatiux.dinosexpansion.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.renatiux.dinosexpansion.Dinosexpansion;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class WorldUtils {
	
	/**
	 * checks whether the Position is loaded or not
	 */
	 public static boolean isBlockLoaded(@Nullable IBlockReader world, @Nonnull BlockPos pos) {
	        if (world == null || !World.isValid(pos)) {
	            return false;
	        } else if (world instanceof IWorldReader) {
	            //Note: We don't bother checking if it is a world and then isBlockPresent because
	            // all that does is also validate the y value is in bounds, and we already check to make
	            // sure the position is valid both in the y and xz directions
	            return ((IWorldReader) world).isAreaLoaded(pos, 0);
	        }
	        return true;
	    }

	
	/**
	 * gets the TileEntity when the chunk is loaded
	 */
	 
	@Nullable
	public static TileEntity getTileEntity(IBlockReader world, BlockPos pos) {
		if(!isBlockLoaded(world, pos)) {
			return null;
		}
		return world.getTileEntity(pos);
	}
	/**
	 * gets the TileEntity when the chunk is loaded
	 * @param <T> - the Type of the TileEntity
	 * @param clazz - the class the TileEntity at this position should have
	 * @param world - the world we are in
	 * @param pos - the position we are searching for the Tile Entity
	 * @return the TileEntity or null if there isnt one or it cant be cast
	 */
	@Nullable
	 public static <T extends TileEntity> T getTileEntity(@Nonnull Class<T> clazz, @Nullable IBlockReader world, @Nonnull BlockPos pos) {
		 TileEntity te = getTileEntity(world, pos);
		 if(te == null)
			 return null;
		 else if(clazz.isInstance(te)) {
			 return clazz.cast(te);
		 }
		 return null;
	 }

}
