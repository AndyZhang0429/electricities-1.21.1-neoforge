package com.fengming.electricities.ponder;

import com.fengming.electricities.Electricities;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class ModPonderPlugin implements PonderPlugin {
    @Override
    public String getModId(){
        return Electricities.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(ResourceLocation.fromNamespaceAndPath(Electricities.MODID,"battery"))
                .addStoryBoard("battery/battery_simple", ModBatteryPonders::PonderBatterySimple);
    }

}
