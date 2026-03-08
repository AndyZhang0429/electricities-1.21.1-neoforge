package com.fengming.electricities.item;

import com.fengming.electricities.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.fengming.electricities.Electricities;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Electricities.MODID);
    public static final DeferredItem<BlockItem> BATTERY_ITEM =
            ITEMS.register(
                    "battery",
                    () -> new BlockItem(
                            ModBlocks.BATTERY.get(),
                            new Item.Properties()
                    )
            );
    public static final DeferredItem<BlockItem> SOLAR_PANEL_ITEM =
            ITEMS.register(
                    "solar_panel",
                    () -> new BlockItem(
                            ModBlocks.SOLAR_PANEL.get(),
                            new Item.Properties()
                    )
            );
    public static final DeferredItem<Item> RAW_SILICON =
            ITEMS.register(
                    "raw_silicon",
                    () -> new Item(
                            new Item.Properties()
                    )
            );
    public static final DeferredItem<Item> SILICON_INGOT =
            ITEMS.register(
                    "silicon_ingot",
                    () -> new Item(
                            new Item.Properties()
                    )
            );
}
