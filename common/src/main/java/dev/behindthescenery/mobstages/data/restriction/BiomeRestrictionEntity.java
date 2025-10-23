package dev.behindthescenery.mobstages.data.restriction;

import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public record BiomeRestrictionEntity(ResourceLocation biomeId) implements RestrictionEntity {

    @Override
    public boolean success(Entity entity, LevelAccessor level, Stage stage) {
        return level.getBiome(entity.getOnPos()).is(biomeId);
    }
}
