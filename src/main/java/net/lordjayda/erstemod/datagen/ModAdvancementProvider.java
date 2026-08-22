package net.lordjayda.erstemod.datagen;

import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new ErsteModAdvancements()));
    }

    public static class  ErsteModAdvancements implements AdvancementSubProvider {

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);
            var blocks = registries.lookupOrThrow(Registries.BLOCK);

            AdvancementHolder root = Advancement.Builder.advancement().display(
                    ModBlocks.CUTTING_BOARD,
                    Component.translatable( "advancement.erstemod.root.title"),
                    Component.translatable( "advancement.erstemod.root.description"),
                    Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                    AdvancementType.TASK, false, false, false
            )
                    .addCriterion("has_cutting_board", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModBlocks.CUTTING_BOARD)))
                    .save(output, Erstemod.MOD_ID + ":erstemod/root");

            AdvancementHolder plantSeed = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.LETTUCE_SEED,
                            Component.translatable("advancements.tutorialmod.plant_custom.title"),
                            Component.translatable("advancements.tutorialmod.plant_custom.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("LETTUCE", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.lettuce_headcrop))
                    .addCriterion("tomato", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.tomatocrop))
                    .save(output, Erstemod.MOD_ID + ":erstemod/plant_custom");

            AdvancementHolder harvestcutsomplant = Advancement.Builder.advancement()
                    .parent(plantSeed)
                    .display(
                            ModItems.LETTUCEHEAD,
                            Component.translatable("advancements.tutorialmod.harvestcutsomplant.title"),
                            Component.translatable("advancements.tutorialmod.harvestcutsomplant.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("LETTUCE", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PATTY))
                    .save(output, Erstemod.MOD_ID + ":erstemod/harvestcutsomplant");



            AdvancementHolder cutmeat = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.RAW_PATTY,
                            Component.translatable("advancements.tutorialmod.cutmeat.title"),
                            Component.translatable("advancements.tutorialmod.cutmeat.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("LETTUCE", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_PATTY))
                    .save(output, Erstemod.MOD_ID + ":erstemod/cutmeat");

            AdvancementHolder cookmeat = Advancement.Builder.advancement()
                    .parent(cutmeat)
                    .display(
                            ModItems.PATTY,
                            Component.translatable("advancements.tutorialmod.cookmeat.title"),
                            Component.translatable("advancements.tutorialmod.cookmeat.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("LETTUCE", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PATTY))
                    .save(output, Erstemod.MOD_ID + ":erstemod/cookmeat");

        }
    }
}
