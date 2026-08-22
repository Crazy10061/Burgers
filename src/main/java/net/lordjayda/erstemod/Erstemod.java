package net.lordjayda.erstemod;

import net.fabricmc.api.ModInitializer;

import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.block.entity.ModBlockEntities;
import net.lordjayda.erstemod.item.creative.ModCreativeModeTabs;
import net.lordjayda.erstemod.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Erstemod implements ModInitializer {
	public static final String MOD_ID = "erstemod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		LOGGER.info("Successfully initialized!");
    }
}
