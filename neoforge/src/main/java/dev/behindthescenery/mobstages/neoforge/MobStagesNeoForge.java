package dev.behindthescenery.mobstages.neoforge;

import dev.behindthescenery.mobstages.MobStages;
import net.neoforged.fml.common.Mod;

@Mod(MobStages.MOD_ID)
public final class MobStagesNeoForge {
    public MobStagesNeoForge() {
        // Run our common setup.
        MobStages.init();
    }
}
