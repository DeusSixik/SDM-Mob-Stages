package dev.behindthescenery.mobstages.data.restriction;

import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public record DimensionRestrictionEntity(ResourceLocation dimension_id) implements RestrictionEntity {

    @Override
    public boolean success(Entity entity, Level level, Stage stage) {
        return level.dimension().location().equals(dimension_id);
    }
}
