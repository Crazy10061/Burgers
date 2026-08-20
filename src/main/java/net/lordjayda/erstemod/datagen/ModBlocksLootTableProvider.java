package net.lordjayda.erstemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.block.custom.lettuceCropBlock;
import net.lordjayda.erstemod.item.Moditems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;

import java.util.concurrent.CompletableFuture;

import static net.lordjayda.erstemod.block.ModBlocks.lettuce_headcrop;
import static net.lordjayda.erstemod.block.ModBlocks.tomatocrop;

public class ModBlocksLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlocksLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.CUTTING_BOARD);
        this.add(lettuce_headcrop, this.createCropDrops(lettuce_headcrop, Moditems.lettucehead, Moditems.lettuce_seed,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(lettuce_headcrop)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(lettuceCropBlock.AGE, lettuceCropBlock.MAX_AGE))));
        this.add(tomatocrop, this.createCropDrops(tomatocrop, Moditems.Tomate, Moditems.tomato_seed,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(tomatocrop)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(lettuceCropBlock.AGE, lettuceCropBlock.MAX_AGE))
        ));
    }
}
