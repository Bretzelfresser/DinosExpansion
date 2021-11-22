package com.renatiux.dinosexpansion.common.blocks.cables;

import java.util.Locale;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class AbstractCableBlock extends Block implements IWaterLoggable{

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public static final EnumProperty<ConnectionType> NORTH = EnumProperty.create("north", ConnectionType.class);
	public static final EnumProperty<ConnectionType> SOUTH = EnumProperty.create("south", ConnectionType.class);
	public static final EnumProperty<ConnectionType> EAST = EnumProperty.create("east", ConnectionType.class);
	public static final EnumProperty<ConnectionType> WEST = EnumProperty.create("west", ConnectionType.class);
	public static final EnumProperty<ConnectionType> UP = EnumProperty.create("up", ConnectionType.class);
	public static final EnumProperty<ConnectionType> DOWN = EnumProperty.create("down", ConnectionType.class);

	public static final Map<Direction, EnumProperty<ConnectionType>> FACING_TO_PROPERTY_MAP = Util
			.make(Maps.newEnumMap(Direction.class), (p) -> {
				p.put(Direction.NORTH, NORTH);
				p.put(Direction.EAST, EAST);
				p.put(Direction.SOUTH, SOUTH);
				p.put(Direction.WEST, WEST);
				p.put(Direction.UP, UP);
				p.put(Direction.DOWN, DOWN);
			});

	static boolean shapeConnects(BlockState state, EnumProperty<ConnectionType> dirctionProperty) {
		return state.get(dirctionProperty).isConnected();
	}

	public AbstractCableBlock(Properties properties) {
		super(properties);
		setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return getTileEntity(state, world);
	}
	
	public abstract TileEntity getTileEntity(BlockState state, IBlockReader world);

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		return stateIn;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(WATERLOGGED);
		builder.add(SOUTH, EAST, WEST, NORTH, UP, DOWN);
	}

	@Override
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).with(WATERLOGGED,
				context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
	}
	
	 public static boolean isItem(BlockState stateIn, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		    return hasCapabilityDir(facing, world, facingPos, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
		  }

		  public static boolean isFluid(BlockState stateIn, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		    return hasCapabilityDir(facing, world, facingPos, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
		  }

		  public static boolean isEnergy(BlockState stateIn, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		    return hasCapabilityDir(facing, world, facingPos, CapabilityEnergy.ENERGY);
		  }

		  private static boolean hasCapabilityDir(Direction facing, IWorld world, BlockPos facingPos, Capability<?> cap) {
		    if (facing == null) {
		      return false;
		    }
		    TileEntity neighbor = world.getTileEntity(facingPos);
		    if (neighbor != null
		        && neighbor.getCapability(cap, facing.getOpposite()).orElse(null) != null) {
		      return true;
		    }
		    return false;
		  }

	public enum ConnectionType implements IStringSerializable {

		NONE, CABLE, INVENTORY, BLOCKED;

		public boolean isHollow() {
			return this == NONE || this == BLOCKED;
		}

		public boolean isConnected() {
			return this == CABLE || this == INVENTORY;
		}

		public boolean isBlocked() {
			return this == BLOCKED;
		}
		
		public boolean isExtraction() {
			return this == BLOCKED;
		}

		@Override
		public String getString() {
			return this.name().toLowerCase(Locale.ENGLISH);
		}
	}

}
