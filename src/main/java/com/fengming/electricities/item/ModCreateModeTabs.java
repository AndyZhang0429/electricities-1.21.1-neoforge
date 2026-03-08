package com.fengming.electricities.item;

import com.fengming.electricities.Electricities;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreateModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Electricities.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(
            "electricities_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.electricities")) //The language key for the title of your CreativeModeTab
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModItems.BATTERY_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BATTERY_ITEM.get());
                        output.accept(ModItems.SOLAR_PANEL_ITEM.get());
                        output.accept(ModItems.RAW_SILICON.get());
                        output.accept(ModItems.SILICON_INGOT.get());
                    }).build());
}
