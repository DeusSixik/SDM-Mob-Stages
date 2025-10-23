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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

public class MobStagesEvent {

    private static StageContainer ServerStageContainer;
    private static boolean isGlobal = false;

    public static EventResult onEntityAdd(LivingEntity livingEntity, LevelAccessor levelAccessor, double v, double v1, double v2, MobSpawnType mobSpawnType, @Nullable BaseSpawner baseSpawner) {
        if(livingEntity instanceof ServerPlayer) return EventResult.interruptDefault();

        if(levelAccessor.isClientSide()) return EventResult.interruptDefault();
        return isGlobal ? onEntityAddGlobal(livingEntity, levelAccessor) : onEntityAddLocal(livingEntity, levelAccessor);
    }

    protected static EventResult onEntityAddGlobal(final Entity entity, final LevelAccessor level) {
        return checkCanSpawn(entity, level, ServerStageContainer.getStage(null));
    }

    protected static EventResult onEntityAddLocal(final Entity entity, final LevelAccessor level) {
        final Player player = ChunkHelper.getNearestPlayer(level, entity.blockPosition());
        if(player == null) return EventResult.interruptFalse();
        return checkCanSpawn(entity, level, ServerStageContainer.getStage(player));
    }

    protected static EventResult checkCanSpawn(final Entity entity, final LevelAccessor level, final Stage stage) {
        for (EntityStage entityStage : EntityStageContainer.Instance.getStages(entity)) {
            if(entityStage.canSpawn(entity, level, stage)) continue;

            return EventResult.interruptFalse();
        }

        return EventResult.interruptDefault();
    }

    public static void onServerStarted(final MinecraftServer server) {
        ServerStageContainer = StageApi.getServerStage();
        isGlobal = ServerStageContainer.getContainerType() == StageContainerType.GLOBAL;
    }
}
