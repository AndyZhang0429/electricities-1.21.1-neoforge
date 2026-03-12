package com.fengming.electricities.block;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import com.fengming.electricities.Electricities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyGeneratorBlockEntitiy extends BlockEntity {
    protected int EnergyGerationLevel;
    EnergyGeneratorBlockEntitiy(BlockPos pos, BlockState state){
        super(ModBlockEntities.ENERGY_GENERATOR_ENTITY.get(),pos,state);
    }

    EnergyGeneratorBlockEntitiy(BlockEntityType<?> type, BlockPos pos, BlockState state){
        super(type,pos,state);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.EnergyGerationLevel = tag.getInt("energy_generation_level");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // The packet uses the CompoundTag returned by #getUpdateTag. An alternative overload of #create exists
        // that allows you to specify a custom update tag, including the ability to omit data the client might not need.
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
