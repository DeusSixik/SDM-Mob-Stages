package dev.behindthescenery.mobstages.data;

import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public interface EntityStage {

    boolean canSpawn(Entity entity, LevelAccessor level, Stage stage);
}
