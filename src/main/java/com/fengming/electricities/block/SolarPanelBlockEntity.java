package com.fengming.electricities.block;

import com.fengming.electricities.Electricities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SolarPanelBlockEntity extends EnergyGeneratorBlockEntitiy{
    SolarPanelBlockEntity(BlockPos pos, BlockState state){
        super(ModBlockEntities.SOLAR_PANEL_ENTITY.get(),pos,state);
    }    

    public int getGenerateSpeed(Level level, BlockPos pos) {
        return getnConnectedPanels(level,pos)*1;
    }

    public int getnConnectedPanels(Level level, BlockPos pos){
        Boolean[][] vis = {};
        return dfsConnections(vis,level,pos);
    }
    
    int dfsConnections(Boolean[][] vis, Level level, BlockPos pos){
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
    
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        Level level = getLevel();
        BlockPos pos = getBlockPos();
        EnergyGerationLevel = getGenerateSpeed(level, pos);
        saveAdditional(tag, registries);

        Electricities.LOGGER.info("EnergyGen:{}",tag.getInt("energy_generation_level"));
        return tag;
    }

}
