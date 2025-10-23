# Mob Stages
Mob Stages is a mod that allows you to limit spawn to different creatures until the player has the desired stage.

## How Use ?
The mod supports both KubeJS and CraftTweaker

### KubeJs
```js
//Server_Scripts

MobStages.builderById("minecraft:pig", "stage_one").biome("minecraft:plains").biome("minecraft:forest").replace("minecraft:zombie").build()
```

| Method        | Type                                           | Return             | Description                                                                                     |
|---------------|------------------------------------------------|--------------------|-------------------------------------------------------------------------------------------------|
| builderByType | entity_id as EntityType<?>, stage as String    | EntityStageBuilder | Creates a constructor                                                                           |
| builderById   | entity_id as ResourceLocation, stage as String | EntityStageBuilder | Creates a constructor                                                                           |
| biome         | biome_id as ResourceLocation                   | EntityStageBuilder | Indicates which biome the creatures should spawn in.                                            |
| dimension     | dimension_id as ResourceLocation               | EntityStageBuilder | Specifies which dimension the creatures should spawn in.                                        |
| replace       | entity_id as ResourceLocation                  | EntityStageBuilder | Indicates which entity the original entity will be replaced with if the conditions are not met. |
| build         |                                                | void               | The last stage. Completes the constructor chain                                                 |

### CraftTweaker
`import mods.modstages.MobStages;`

```ts
import mods.modstages.MobStages;

MobStages.builder(<entitytype:minecraft:pig>, "stage_one").biome(<resource:minecraft:plains>).biome(<resource:minecraft:forest>).replace(<entitytype:minecraft:zombie>).build();
```

| Method    | Type                                           | Return             | Description                                                                                     |
|-----------|------------------------------------------------|--------------------|-------------------------------------------------------------------------------------------------|
| builder   | entity_id as EntityType<?>, stage as String    | EntityStageBuilder | Creates a constructor                                                                           |
| builder   | entity_id as ResourceLocation, stage as String | EntityStageBuilder | Creates a constructor                                                                           |
| biome     | biome_id as ResourceLocation                   | EntityStageBuilder | Indicates which biome the creatures should spawn in.                                            |
| dimension | dimension_id as ResourceLocation               | EntityStageBuilder | Specifies which dimension the creatures should spawn in.                                        |
| replace   | entity_id as ResourceLocation                  | EntityStageBuilder | Indicates which entity the original entity will be replaced with if the conditions are not met. |
| replace   | entityType as EntityType<?>                    | EntityStageBuilder | Indicates which entity the original entity will be replaced with if the conditions are not met. |
| build     |                                                | void               | The last stage. Completes the constructor chain                                                 |



