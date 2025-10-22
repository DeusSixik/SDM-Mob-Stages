package dev.behindthescenery.mobstages.api;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import dev.behindthescenery.mobstages.data.BaseEntityStage;
import dev.behindthescenery.mobstages.data.EntityStageContainer;
import dev.behindthescenery.mobstages.data.functions.FunctionEntity;
import dev.behindthescenery.mobstages.data.functions.ReplaceEntity;
import dev.behindthescenery.mobstages.data.restriction.BiomeRestrictionEntity;
import dev.behindthescenery.mobstages.data.restriction.DimensionRestrictionEntity;
import dev.behindthescenery.mobstages.data.restriction.RestrictionEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.modstages.MobStages")
public class MobStagesApi {

    @ZenCodeType.Method
    public static EntityStageBuilder builder(ResourceLocation entity_id, String stage) {
        return new EntityStageBuilder(entity_id, stage);
    }

    @ZenCodeType.Method
    public static EntityStageBuilder builder(EntityType<?> entityType, String stage) {
        return new EntityStageBuilder(entityType, stage);
    }

    @ZenRegister
    @ZenCodeType.Name("mods.modstages.EntityStageBuilder")
    public static class EntityStageBuilder {

        protected EntityType<?> entityType;
        protected String stage;
        protected List<RestrictionEntity> restrictionEntities = new ArrayList<>();
        protected List<FunctionEntity> functionEntities = new ArrayList<>();

        protected EntityStageBuilder(ResourceLocation entity_id, String stage) {
            this(getEntity(entity_id), stage);
        }

        protected EntityStageBuilder(EntityType<?> entityType, String stage) {
            this.entityType = entityType;
            this.stage = stage;
        }

        protected static EntityType<?> getEntity(ResourceLocation entity_id) {
            EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(entity_id);
            if(type == null)
                throw new NullPointerException("Can't find entity by id: " + entity_id.toString());
            return type;
        }

        @ZenCodeType.Method
        public EntityStageBuilder biome(ResourceLocation biome_id) {
            restrictionEntities.add(new BiomeRestrictionEntity(biome_id));
            return this;
        }

        @ZenCodeType.Method
        public EntityStageBuilder dimension(ResourceLocation dimension_id) {
            restrictionEntities.add(new DimensionRestrictionEntity(dimension_id));
            return this;
        }

        @ZenCodeType.Method
        public EntityStageBuilder replace(ResourceLocation entity) {
            return replace(getEntity(entity));
        }

        @ZenCodeType.Method
        public EntityStageBuilder replace(EntityType<?> entity) {
            functionEntities.add(new ReplaceEntity(entity));
            return this;
        }

        @ZenCodeType.Method
        public void build() {
            final BaseEntityStage entityStage = new BaseEntityStage(entityType, stage, restrictionEntities.toArray(new RestrictionEntity[0]), functionEntities.toArray(new FunctionEntity[0]));
            EntityStageContainer.Instance.register(entityType, entityStage);
        }
    }
}
