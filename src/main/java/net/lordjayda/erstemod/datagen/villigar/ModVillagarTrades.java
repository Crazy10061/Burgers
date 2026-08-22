package net.lordjayda.erstemod.datagen.villigar;


import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.List;
import java.util.Optional;

public class ModVillagarTrades {


    public static final ResourceKey<VillagerTrade> FARMER_1_EMERALD_LETTUCE_SEED = createKey("farmer/1/emerald_lettuce_seed");
    public static final ResourceKey<VillagerTrade> FARMER_1_EMERALD_TOMATO_SEED = createKey("farmer/1/emerald_tomato_seed");


    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        context.register(FARMER_1_EMERALD_LETTUCE_SEED, new VillagerTrade(new TradeCost(Items.EMERALD, 4), new ItemStackTemplate(ModItems.LETTUCE_SEED), 12,4,0.05f, Optional.empty(), List.of()));
        context.register(FARMER_1_EMERALD_TOMATO_SEED, new VillagerTrade(new TradeCost(Items.EMERALD, 4), new ItemStackTemplate(ModItems.TOMATO_SEED), 12,4,0.05f, Optional.empty(), List.of()));
        }

    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name));
    }
}
