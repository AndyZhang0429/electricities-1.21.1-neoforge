package com.fengming.electricities.block;

import com.fengming.electricities.Electricities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.rmi.registry.Registry;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Electricities.MODID);
    public static final DeferredBlock<Block> BATTERY = BLOCKS.register(
            "battery",
            () -> new BatteryBlock(
                    BlockBehaviour.Properties.of()
            )
    );
    public static final DeferredBlock<Block> SOLAR_PANEL = BLOCKS.register(
            "solar_panel",
            () -> new SolarPanel(
                    BlockBehaviour.Properties.of()
            )
    );
}
