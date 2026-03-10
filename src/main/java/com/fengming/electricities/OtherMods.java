package com.fengming.electricities;

import net.neoforged.fml.ModList;

public class OtherMods {
    public static Boolean Exsist(String modId){
        return ModList.get().getMods().stream().anyMatch(modContainer -> modContainer.getModId().equals(modId));
    }
}
