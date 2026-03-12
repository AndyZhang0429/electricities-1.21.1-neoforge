package com.fengming.electricities.block;

import java.util.function.Supplier;

import com.fengming.electricities.Electricities;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities { //needs to register see https://docs.neoforged.net/docs/1.21.1/blockentities/
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Electricities.MODID);
    public static final Supplier<BlockEntityType<EnergyStorageBlockEntitiy>> ENERGY_STORAGE_ENTITY = BLOCK_ENTITIES.register(
        "energy_storage",
        // The block entity type, created using a builder.
        () -> BlockEntityType.Builder.of(
                // The supplier to use for constructing the block entity instances.
                EnergyStorageBlockEntitiy::new,
                // A vararg of blocks that can have this block entity.
                // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                ModBlocks.BATTERY.get()
        )
        // Build using null; vanilla does some datafixer shenanigans with the parameter that we don't need.
        .build(null)
    );
    public static final Supplier<BlockEntityType<EnergyGeneratorBlockEntitiy>> ENERGY_GENERATOR_ENTITY = BLOCK_ENTITIES.register(
        "energy_generator",
        () -> BlockEntityType.Builder.of(
                EnergyGeneratorBlockEntitiy::new
        )
        .build(null)
    );
    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_ENTITY = BLOCK_ENTITIES.register(
        "solar_panel",
        () -> BlockEntityType.Builder.of(
                SolarPanelBlockEntity::new,
                ModBlocks.SOLAR_PANEL.get()
        )
        .build(null)
    );
}
