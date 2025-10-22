package dev.behindthescenery.mobstages.data.restriction;

import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public record BiomeRestrictionEntity(ResourceLocation biomeId) implements RestrictionEntity {

    @Override
    public boolean success(Entity entity, Level level, Stage stage) {
        return level.getBiome(entity.getOnPos()).is(biomeId);
    }
}
