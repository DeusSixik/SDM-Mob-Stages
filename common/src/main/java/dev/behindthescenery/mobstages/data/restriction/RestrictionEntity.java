package dev.behindthescenery.mobstages.data.restriction;

import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public interface RestrictionEntity {

    boolean success(Entity entity, Level level, Stage stage);
}
