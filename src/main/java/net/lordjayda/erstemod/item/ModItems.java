package net.lordjayda.erstemod.item;

import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.food.ModFoods;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import java.util.function.Function;

public class ModItems {
    public static final Item BURGER = registerItem( "burger", properties -> new Item(properties.food(ModFoods.BURGER)));
    public static final Item LETTUCE = registerItem( "LETTUCE", properties -> new Item(properties.food(ModFoods.salad)));
    public static final Item LETTUCEHEAD = registerItem( "lettuce_head", properties -> new Item(properties.food(ModFoods.salad)));
    public static final Item LETTUCE_SEED = registerItem( "LETTUCE_SEED", properties -> new BlockItem(ModBlocks.lettuce_headcrop, properties.useItemDescriptionPrefix()));
    public static final Item TOMATO = registerItem( "tomato", properties -> new Item(properties.food(ModFoods.tomato)));
    public static final Item TOMATO_SLICE = registerItem( "tomato_slice", properties -> new Item(properties.food(ModFoods.tomato_slice)));
    public static final Item TOMATO_SEED = registerItem( "TOMATO_SEED", properties -> new BlockItem(ModBlocks.tomatocrop, properties.useItemDescriptionPrefix()));
    public static final Item PATTY = registerItem( "PATTY", properties -> new Item(properties.food(ModFoods.patty)));
    public static final Item RAW_PATTY = registerItem( "RAW_PATTY", properties -> new Item(properties.food(ModFoods.raw_patty)));
    public static final Item BUN = registerItem( "bun", Item::new );
    public static final Item TOP_BUN = registerItem( "top_bun", Item::new );
    public static final Item BOTTOM_BUN = registerItem( "bottom_bun", Item::new );


    private static Item registerItem(String id, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, id),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, id)))));
    }

    public static void registerModItems () {
        Erstemod.LOGGER.info("Registering Mod Items for" + Erstemod.MOD_ID);
    }
}
