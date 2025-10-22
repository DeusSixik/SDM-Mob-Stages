package dev.behindthescenery.mobstages.data.functions;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public interface FunctionEntity {

    void execute(Entity entity, Level level);
}
