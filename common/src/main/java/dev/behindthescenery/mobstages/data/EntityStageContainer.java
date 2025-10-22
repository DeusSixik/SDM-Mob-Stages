package dev.behindthescenery.mobstages.data;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class EntityStageContainer extends SimplePreparableReloadListener<Void> {

    public static final EntityStageContainer Instance = new EntityStageContainer();

    protected Object2ObjectMap<EntityType<?>, List<EntityStage>> StageData = new Object2ObjectArrayMap<>();

    public void register(EntityType<?> entityType, EntityStage stage) {
        StageData.computeIfAbsent(entityType, s -> new ArrayList<>()).add(stage);
    }

    public List<EntityStage> getStages(Entity entity) {
        return getStages(entity.getType());
    }

    public List<EntityStage> getStages(EntityType<?> entityType) {
        return StageData.getOrDefault(entityType, new ArrayList<>());
    }

    @Override
    protected Void prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        StageData.clear();
        return null;
    }

    @Override
    protected void apply(Void object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
    }
}
