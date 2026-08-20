package net.lordjayda.erstemod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.lordjayda.erstemod.datagen.ModBlockTagsProvider;
import net.lordjayda.erstemod.datagen.ModBlocksLootTableProvider;
import net.lordjayda.erstemod.datagen.ModModelProvider;
import net.lordjayda.erstemod.datagen.ModRecipeProvider;
import net.minecraft.client.data.models.ModelProvider;

public class ErstemodDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModBlocksLootTableProvider::new);
	}
}