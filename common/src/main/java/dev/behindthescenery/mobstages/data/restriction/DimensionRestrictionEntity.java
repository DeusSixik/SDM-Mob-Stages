package dev.behindthescenery.mobstages.data.restriction;

import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public record DimensionRestrictionEntity(ResourceLocation dimension_id) implements RestrictionEntity {

    @Override
    public boolean success(Entity entity, LevelAccessor level, Stage stage) {
        if(level instanceof Level level1)
            return level1.dimension().location().equals(dimension_id);
        return true;
    }
}
