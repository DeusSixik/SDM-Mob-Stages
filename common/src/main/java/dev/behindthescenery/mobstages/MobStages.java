package dev.behindthescenery.mobstages;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.LifecycleEvent;

public final class MobStages {
    public static final String MOD_ID = "mobstages";

    public static void init() {


        LifecycleEvent.SERVER_STARTED.register(MobStagesEvent::onServerStarted);
        EntityEvent.LIVING_CHECK_SPAWN.register(MobStagesEvent::onEntityAdd);
    }
}
