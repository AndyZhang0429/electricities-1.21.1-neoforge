package com.fengming.electricities.block;

import com.fengming.electricities.Electricities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BatteryBlock extends Block implements EntityBlock{
    // BlockStates Definitions
    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class, Direction.Plane.HORIZONTAL);
    // Electrical energy (saved) level, full 14400 FE (= 64*1024 SU*s by Alternator in Create Crafts & Additions)

    public BatteryBlock(Properties prop){
        super(prop);
        this.registerDefaultState(
                stateDefinition.any().setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new EnergyStorageBlockEntitiy(pos, state);
    }

    @Nullable
    private static <E extends BlockEntity, X extends BlockEntity> 
    BlockEntityTicker<X> createTickerHelper(
        BlockEntityType<X> type, BlockEntityType<E> checkedType, 
        BlockEntityTicker<? super E> ticker
    ) {
        return checkedType == type ? (BlockEntityTicker<X>) ticker : null;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.ENERGY_STORAGE_ENTITY.get(), EnergyStorageBlockEntitiy::tick);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context){
        VoxelShape base;
        Direction facing = state.getValue(FACING);
        if(facing==Direction.EAST||facing==Direction.WEST){
            base = Shapes.box(0.0625, 0, 0, 0.9375, 0.625, 1);
        }else{
            base = Shapes.box(0, 0, 0.0625, 1, 0.625, 0.9375);
        }
        return base;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder){
        pBuilder.add(FACING);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext){
        Direction playerDirection = pContext.getPlayer().getDirection();
        Direction placementDirection = playerDirection.getAxis() == Direction.Axis.Y ?
                Direction.NORTH : playerDirection;
        return defaultBlockState()
                .setValue(FACING, placementDirection);
    }
}
