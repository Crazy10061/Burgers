package net.lordjayda.erstemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.block.custom.LettuceCropBlock;
import net.lordjayda.erstemod.item.ModItems;
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
        dropSelf(ModBlocks.ASSEMBLER);
        this.add(lettuce_headcrop, this.createCropDrops(lettuce_headcrop, ModItems.LETTUCEHEAD, ModItems.LETTUCE_SEED,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(lettuce_headcrop)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, LettuceCropBlock.MAX_AGE))));
        this.add(tomatocrop, this.createCropDrops(tomatocrop, ModItems.TOMATO, ModItems.TOMATO_SEED,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(tomatocrop)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, LettuceCropBlock.MAX_AGE))
        ));
    }
}
