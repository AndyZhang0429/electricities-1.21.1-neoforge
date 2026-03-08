package com.fengming.electricities.block;

import com.fengming.electricities.Electricities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.energy.EnergyStorage;

import javax.annotation.Nullable;

public class SolarPanel extends EnergyGenerator{
    // BlockStates Definitions
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    public SolarPanel(Properties prop){
        super(prop);
        this.registerDefaultState(
                stateDefinition.any()
                        .setValue(NORTH, true)
                        .setValue(EAST, true)
                        .setValue(SOUTH, true)
                        .setValue(WEST, true)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context){
        VoxelShape base = Shapes.box(0,0,0,1,0.0625,1);
        return base;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder){
        pBuilder.add(NORTH);
        pBuilder.add(EAST);
        pBuilder.add(SOUTH);
        pBuilder.add(WEST);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext){
        BlockPos pos = pContext.getClickedPos();
        BlockState south_block = pContext.getLevel().getBlockState(pos.south());
        BlockState north_block = pContext.getLevel().getBlockState(pos.north());
        BlockState east_block = pContext.getLevel().getBlockState(pos.east());
        BlockState west_block = pContext.getLevel().getBlockState(pos.west());
        Block current = defaultBlockState().getBlock();
        boolean south = true,
                north = true,
                east = true,
                west = true;
        if(south_block.getBlock()==current){
//            pContext.getLevel().setBlock(pos.south(),south_block.setValue(NORTH,false),Block.UPDATE_ALL);
            south = false;
        }
        if(north_block.getBlock()==current){
//            pContext.getLevel().setBlock(pos.north(),north_block.setValue(SOUTH,false),Block.UPDATE_ALL);
            north = false;
        }
        if(east_block.getBlock()==current){
//            pContext.getLevel().setBlock(pos.east(),east_block.setValue(WEST,false),Block.UPDATE_ALL);
            east = false;
        }
        if(west_block.getBlock()==current){
//            pContext.getLevel().setBlock(pos.west(),west_block.setValue(EAST,false),Block.UPDATE_ALL);
            west = false;
        }
        return defaultBlockState()
                .setValue(SOUTH,south)
                .setValue(NORTH,north)
                .setValue(EAST,east)
                .setValue(WEST,west);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState south_block = level.getBlockState(pos.south());
        BlockState north_block = level.getBlockState(pos.north());
        BlockState east_block = level.getBlockState(pos.east());
        BlockState west_block = level.getBlockState(pos.west());
        Block current = defaultBlockState().getBlock();
        boolean south = south_block.getBlock()!=current,
                north = north_block.getBlock()!=current,
                east = east_block.getBlock()!=current,
                west = west_block.getBlock()!=current;
        return defaultBlockState()
                .setValue(SOUTH,south)
                .setValue(NORTH,north)
                .setValue(EAST,east)
                .setValue(WEST,west);
    }
}
