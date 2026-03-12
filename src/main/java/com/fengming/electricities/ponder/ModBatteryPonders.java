package com.fengming.electricities.ponder;

import com.fengming.electricities.block.ModBlocks;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class ModBatteryPonders{
    public static void PonderBatterySimple(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("battery_simple","How to use the battery to save energy");
        scene.configureBasePlate(0,0,9);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        scene.idle(10);

        BlockPos PosBattery0 = new BlockPos(4,1,4);
        scene.world().setBlock(
                PosBattery0,
                ModBlocks.BATTERY.get().defaultBlockState(),
                true);
        Selection battery0 = util.select().position(PosBattery0);
        scene.world().showSection(battery0, Direction.UP);

        scene.overlay().showText(15)
                .text("The battery have an input and an output.")
                .pointAt(util.vector().of(4.5,1.5,4.5));
        scene.idle(20);

        scene.overlay().showText(15)
                .placeNearTarget()
                .text("Input")
                .pointAt(util.vector().of(5,1.5,4.5))
                .colored(PonderPalette.INPUT);
        scene.idle(20);
        scene.overlay().showText(15)
                .text("Output")
                .pointAt(util.vector().of(4,1.5,4.5))
                .colored(PonderPalette.OUTPUT);
        scene.idle(20);

        BlockPos PosBattery1 = new BlockPos(3,1,4);
        scene.world().setBlock(
                PosBattery1,
                ModBlocks.BATTERY.get().defaultBlockState(),
                true);
        Selection battery1 = util.select().position(PosBattery1);
        scene.world().showSection(battery1, Direction.UP);
        scene.idle(30);
    }
}
