package net.lordjayda.erstemod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.block.custom.lettuceCropBlock;
import net.lordjayda.erstemod.block.custom.tomatocropblock;
import net.lordjayda.erstemod.item.Moditems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.CUTTING_BOARD);
        blockModelGenerators.createCropBlock(ModBlocks.lettuce_headcrop, lettuceCropBlock.AGE,0, 1);
        blockModelGenerators.createCropBlock(ModBlocks.tomatocrop, tomatocropblock.AGE, 0,1 );

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(Moditems.BURGER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.lettuce, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.lettucehead, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.Tomate, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.patty, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.raw_patty, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.Bun, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.top_Bun, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.bottom_Bun, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Moditems.tomaten_scheibe, ModelTemplates.FLAT_ITEM);

    }
}
