package dev.behindthescenery.mobstages.data.functions;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public interface FunctionEntity {

    void execute(Entity entity, LevelAccessor level);
}
