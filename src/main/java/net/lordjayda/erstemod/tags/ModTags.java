package net.lordjayda.erstemod.tags;

import net.lordjayda.erstemod.Erstemod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

public class ModTags {
    public static class Trades {
        public static final TagKey<VillagerTrade> FAST_FOOD_WORKER_1 = createTag("fast_food_worker/level_1");

        private static TagKey<VillagerTrade> createTag(String id) {
            return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, id));
        }

    }
}
