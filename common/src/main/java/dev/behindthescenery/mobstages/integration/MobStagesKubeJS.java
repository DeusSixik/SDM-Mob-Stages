package dev.behindthescenery.mobstages.integration;

import dev.behindthescenery.mobstages.api.MobStagesApi;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

public class MobStagesKubeJS implements KubeJSPlugin {

    @Override
    public void registerBindings(BindingRegistry bindings) {
        if(bindings.type().isServer()) {
            bindings.add("MobStages", Methods.class);
        }
    }

    public interface Methods {

        static MobStagesApi.EntityStageBuilder builderByType(ResourceLocation entity_id, String stage) {
            return MobStagesApi.builder(entity_id, stage);
        }

        static MobStagesApi.EntityStageBuilder builderById(EntityType<?> entityType, String stage) {
            return MobStagesApi.builder(entityType, stage);
        }
    }
}

