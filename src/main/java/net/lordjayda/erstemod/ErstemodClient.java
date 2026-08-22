package net.lordjayda.erstemod;

import net.fabricmc.api.ClientModInitializer;
import net.lordjayda.erstemod.block.entity.ModBlockEntities;
import net.lordjayda.erstemod.block.entity.renderer.CuttingBoardBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class ErstemodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockEntityRenderers.register(ModBlockEntities.cuttingboard_be, CuttingBoardBlockEntityRenderer::new);
    }
}    