package com.fengming.electricities.block;

import com.fengming.electricities.Electricities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

public class SolarPanel extends Block implements EntityBlock {
    // BlockStates Definitions
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    // LevelAccessor LEVEL;
    // BlockPos CURRENT_POS;

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
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new SolarPanelBlockEntity(pos, state);
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
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        BlockState south_block = level.getBlockState(pos.south());
        BlockState north_block = level.getBlockState(pos.north());
        BlockState east_block = level.getBlockState(pos.east());
        BlockState west_block = level.getBlockState(pos.west());
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
    
    public int getGenerateSpeed(LevelAccessor level, BlockPos pos) {
        return getnConnectedPanels(level,pos)*1;
    }

    public int getnConnectedPanels(LevelAccessor level, BlockPos pos){
        Boolean[][] vis = {};
        return dfsConnections(vis,level,pos);
    }
    
    int dfsConnections(Boolean[][] vis, LevelAccessor level, BlockPos pos){
        int sum = 1;
        vis[pos.getX()][pos.getZ()] = true;
        Block south_block = level.getBlockState(pos.south()).getBlock();
        Block north_block = level.getBlockState(pos.north()).getBlock();
        Block east_block = level.getBlockState(pos.east()).getBlock();
        Block west_block = level.getBlockState(pos.west()).getBlock();
        Block current = level.getBlockState(pos).getBlock();
        if(south_block==current){
            sum += dfsConnections(vis,level,pos.south());
        }
        if(north_block==current){
            sum += dfsConnections(vis,level,pos.north());
        }
        if(east_block==current){
            sum += dfsConnections(vis,level,pos.east());
        }
        if(west_block==current){
            sum += dfsConnections(vis,level,pos.west());
        }
        return sum;
    }
}
