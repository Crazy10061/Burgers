package net.lordjayda.erstemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.lordjayda.erstemod.item.Moditems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> Smeltables = List.of(Moditems.raw_patty);

                oreSmelting(Smeltables, RecipeCategory.FOOD, CookingBookCategory.FOOD, Moditems.patty, 0.25f,100, "Burger Ingredients");
                shaped(RecipeCategory.FOOD, Moditems.BURGER)
                        .pattern(" A ")
                        .pattern("BCD")
                        .pattern(" E ")
                        .define('A',Moditems.top_Bun)
                        .define('B',Moditems.tomaten_scheibe)
                        .define('C',Moditems.patty)
                        .define('D',Moditems.lettuce)
                        .define('E',Moditems.bottom_Bun)
                        .unlockedBy(getHasName(Moditems.patty), has(Moditems.patty))
                        .group("Burgers")
                        .save(output);
                shaped(RecipeCategory.FOOD, Moditems.Bun)
                        .pattern("RR")
                        .pattern("RR")
                        .define('R',Items.WHEAT)
                        .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                        .group("Burgers")
                        .save(output);

            }
        };
    }

    @Override
    public String getName() {
        return "Erstemod Recipes";
    }
}
