package dev.behindthescenery.mobstages.data.functions;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public record ReplaceEntity(EntityType<?> entityType) implements FunctionEntity {
    @Override
    public void execute(Entity entity, Level level) {
        final Entity newEntity = entityType.create(level);
        if(newEntity == null) return;

        final BlockPos position = entity.blockPosition();
        newEntity.setPos(position.getX(), position.getY() + 0.1, position.getZ());
        level.addFreshEntity(newEntity);
    }
}
