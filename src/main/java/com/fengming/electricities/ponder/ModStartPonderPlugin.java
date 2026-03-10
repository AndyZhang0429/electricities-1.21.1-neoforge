package com.fengming.electricities.ponder;

import net.createmod.ponder.foundation.PonderIndex;

public class ModStartPonderPlugin {
    public static void Start(){
        PonderIndex.addPlugin(new ModPonderPlugin());
    }
}
