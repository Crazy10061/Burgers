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

public class Moditems {
    public static final Item BURGER = registerItem( "burger", properties -> new Item(properties.food(ModFoods.BURGER)));
    public static final Item lettuce = registerItem( "lettuce", properties -> new Item(properties.food(ModFoods.salad)));
    public static final Item lettucehead = registerItem( "lettuce_head", properties -> new Item(properties.food(ModFoods.salad)));
    public static final Item lettuce_seed = registerItem( "lettuce_seed", properties -> new BlockItem(ModBlocks.lettuce_headcrop, properties.useItemDescriptionPrefix()));
    public static final Item Tomate = registerItem( "tomato", properties -> new Item(properties.food(ModFoods.tomato)));
    public static final Item tomaten_scheibe = registerItem( "tomato_slice", properties -> new Item(properties.food(ModFoods.tomato_slice)));
    public static final Item tomato_seed = registerItem( "tomato_seed", properties -> new BlockItem(ModBlocks.tomatocrop, properties.useItemDescriptionPrefix()));
    public static final Item patty = registerItem( "patty", properties -> new Item(properties.food(ModFoods.patty)));
    public static final Item raw_patty = registerItem( "raw_patty", properties -> new Item(properties.food(ModFoods.raw_patty)));
    public static final Item Bun = registerItem( "bun", Item::new );
    public static final Item top_Bun = registerItem( "top_bun", Item::new );
    public static final Item bottom_Bun = registerItem( "bottom_bun", Item::new );
//einfach kopieren und namen und id ändern




    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name)))));
    }

    public static void  registerModItems () {
        Erstemod.LOGGER.info("Registering Mod Items for" + Erstemod.MOD_ID);
    }
}
