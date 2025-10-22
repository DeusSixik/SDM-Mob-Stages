package dev.behindthescenery.mobstages;

import dev.architectury.event.EventResult;
import dev.behindthescenery.mobstages.data.EntityStage;
import dev.behindthescenery.mobstages.data.EntityStageContainer;
import dev.behindthescenery.mobstages.utils.ChunkHelper;
import dev.behindthescenery.sdmstages.StageApi;
import dev.behindthescenery.sdmstages.data.StageContainer;
import dev.behindthescenery.sdmstages.data.StageContainerType;
import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MobStagesEvent {

    private static StageContainer ServerStageContainer;
    private static boolean isGlobal = false;

    public static EventResult onEntityAdd(final Entity entity, final @NotNull Level level) {
        if(level.isClientSide) return EventResult.interruptDefault();
        return isGlobal ? onEntityAddGlobal(entity, level) : onEntityAddLocal(entity, level);
    }

    protected static EventResult onEntityAddGlobal(final Entity entity, final Level level) {
        return checkCanSpawn(entity, level, ServerStageContainer.getStage(null));
    }

    protected static EventResult onEntityAddLocal(final Entity entity, final Level level) {
        final Player player = ChunkHelper.getNearestPlayer(level, entity.blockPosition());
        if(player == null) return EventResult.interruptFalse();
        return checkCanSpawn(entity, level, ServerStageContainer.getStage(player));
    }

    protected static EventResult checkCanSpawn(final Entity entity, final Level level, final Stage stage) {
        for (EntityStage entityStage : EntityStageContainer.Instance.getStages(entity)) {
            if(entityStage.canSpawn(entity, level, stage)) continue;

            return EventResult.interruptFalse();
        }

        return EventResult.interruptTrue();
    }

    public static void onServerStarted(final MinecraftServer server) {
        ServerStageContainer = StageApi.getServerStage();
        isGlobal = ServerStageContainer.getContainerType() == StageContainerType.GLOBAL;
    }
}
