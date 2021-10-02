package com.renatiux.dinosexpansion.common.blocks.cables;

import java.util.ArrayList;
import java.util.List;

import com.renatiux.dinosexpansion.common.tileEntities.cable.AbstractCableTileEntity;
import com.renatiux.dinosexpansion.core.init.TileEntityTypesInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

public class AbstractCableBlock extends Block {

	public static final EnumProperty<ConnectionState> CONNECTION_STATE = EnumProperty.create("connection_state",
			ConnectionState.class);
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public AbstractCableBlock(Properties properties) {
		super(properties);
		setDefaultState(stateContainer.getBaseState().with(CONNECTION_STATE, ConnectionState.DEFAULT).with(FACING,
				Direction.NORTH));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.BASIC_ENERGY_CABLE.get().create();
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		stateIn = updateConnection(worldIn, stateIn, currentPos);
		return stateIn;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		state = updateConnection(worldIn, state, pos);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	protected BlockState updateConnection(IWorld world, BlockState state, BlockPos pos) {
		List<Direction> directions = new ArrayList<>(6);
		for (Direction dir : Direction.values()) {
			TileEntity te = world.getTileEntity(pos.offset(dir));
			if (te != null && te.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite()).isPresent()) {
				directions.add(dir);
			}
		}
		state = state.with(CONNECTION_STATE, getWithAmountOfConnections(directions));
		state = state.with(FACING, getDirectionsOfConnections(directions, state));
		return state;
	}

	private ConnectionState getWithAmountOfConnections(List<Direction> directions) {
		if (directions.size() > 6) {
			throw new IllegalArgumentException(
					"cant be connected to more then 6 sides, check what u put in as a parameter : "
							+ directions.size());
		}
		if (!directions.contains(Direction.UP) && !directions.contains(Direction.DOWN))
			switch (directions.size()) {
			case 0:
				return ConnectionState.DEFAULT;
			case 1:
				return ConnectionState.ONE_SIDED;
			case 2:
				if (checkForStraight(directions))
					return ConnectionState.STRAIGHT;
				return ConnectionState.CORNER;
			case 3:
				return ConnectionState.T_CROSS;
			case 4:
				return ConnectionState.CROSS;
			default:
				return ConnectionState.CROSS;
			}
		return ConnectionState.CROSS;
	}

	private boolean checkForStraight(List<Direction> directions) {
		if (directions.contains(Direction.NORTH) && directions.contains(Direction.SOUTH))
			return true;
		if (directions.contains(Direction.EAST) && directions.contains(Direction.WEST))
			return true;
		return false;
	}

	private Direction getDirectionsOfConnections(List<Direction> directions, BlockState currentState) {
		switch (directions.size()) {
		case 1:
			return directions.get(0);
		case 2:
			if (currentState.get(CONNECTION_STATE) == ConnectionState.STRAIGHT) {
				if (directions.contains(Direction.EAST))
					return Direction.EAST;
			} else {
				return findCornerDirection(directions);
			}
			break;
		case 3:
			if (!directions.contains(Direction.WEST))
				return Direction.EAST;
			if (!directions.contains(Direction.NORTH))
				return Direction.SOUTH;
			if (!directions.contains(Direction.EAST))
				return Direction.WEST;
			break;
		}

		return Direction.NORTH;
	}

	private Direction findCornerDirection(List<Direction> directions) {
		if (directions.contains(Direction.NORTH) && directions.contains(Direction.WEST))
			return Direction.NORTH;
		if (directions.contains(Direction.NORTH) && directions.contains(Direction.EAST))
			return Direction.EAST;
		if (directions.contains(Direction.SOUTH) && directions.contains(Direction.EAST))
			return Direction.SOUTH;
		if (directions.contains(Direction.SOUTH) && directions.contains(Direction.WEST))
			return Direction.WEST;
		return Direction.NORTH;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(CONNECTION_STATE, FACING);
	}

	public static enum ConnectionState implements IStringSerializable {
		DEFAULT("default"), STRAIGHT("straight"), CORNER("corner"), ONE_SIDED("one_sided"), T_CROSS("t_cross"),
		CROSS("cross");

		private final String name;

		private ConnectionState(String name) {
			this.name = name;
		}

		@Override
		public String getString() {
			return name;
		}
	}

}
