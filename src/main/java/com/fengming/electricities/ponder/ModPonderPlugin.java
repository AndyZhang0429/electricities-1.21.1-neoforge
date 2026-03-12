package com.fengming.electricities.ponder;

import com.fengming.electricities.Electricities;
import com.fengming.electricities.item.ModItems;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class ModPonderPlugin implements PonderPlugin {
    final ResourceLocation LOCATION_BATTERY = ResourceLocation.fromNamespaceAndPath(Electricities.MODID,"battery");

    @Override
    public String getModId(){
        return Electricities.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(LOCATION_BATTERY)
                .addStoryBoard("battery/battery_simple", ModBatteryPonders::PonderBatterySimple);
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        ResourceLocation TagBasic = ResourceLocation.fromNamespaceAndPath(Electricities.MODID,"basic");
        helper.registerTag(TagBasic)
                .addToIndex()
                .item(ModItems.BATTERY_ITEM.get())
                .register();
        helper.addTagToComponent(LOCATION_BATTERY, TagBasic);
    }
}
