package dev.behindthescenery.mobstages.data;

import dev.behindthescenery.mobstages.data.functions.FunctionEntity;
import dev.behindthescenery.mobstages.data.restriction.RestrictionEntity;
import dev.behindthescenery.sdmstages.data.containers.Stage;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class BaseEntityStage implements EntityStage {

    protected final EntityType<?> entityType;
    protected final String stage_name;

    protected RestrictionEntity[] restrictions;
    protected FunctionEntity[] functions;

    public BaseEntityStage(EntityType<?> entityType, String stage) {
        this(entityType, stage, new RestrictionEntity[0], new FunctionEntity[0]);
    }

    public BaseEntityStage(EntityType<?> entityType, String stage, RestrictionEntity[] restrictions, FunctionEntity[] functions) {
        this.entityType = entityType;
        this.stage_name = stage;
        this.restrictions = restrictions;
        this.functions = functions;
    }

    @Override
    public boolean canSpawn(Entity entity, Level level, Stage stage) {
        if (!entity.getType().equals(entityType))
            return true;

        if (stage.contains(stage_name))
            return true;

        for (RestrictionEntity restriction : restrictions) {
            if (!restriction.success(entity, level, stage)) {
                cantSpawn(entity, level);
                return false;
            }
        }

        return true;
    }

    protected void cantSpawn(Entity entity, Level level) {
        for (FunctionEntity function : functions) {
            function.execute(entity, level);
        }
    }
}
