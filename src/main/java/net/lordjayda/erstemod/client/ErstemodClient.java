package net.lordjayda.erstemod.client;

import net.fabricmc.api.ClientModInitializer;
import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.entity.ModBlockEntities;
import net.lordjayda.erstemod.client.renderer.CuttingBoardBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import org.apache.commons.compress.archivers.sevenz.CLI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErstemodClient implements ClientModInitializer {

    public static final Logger CLIENT_LOGGER = LoggerFactory.getLogger(Erstemod.MOD_ID);
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntities.cuttingboard_be, CuttingBoardBlockEntityRenderer::new);
        CLIENT_LOGGER.info("Successfully initialized client!");
    }
}    