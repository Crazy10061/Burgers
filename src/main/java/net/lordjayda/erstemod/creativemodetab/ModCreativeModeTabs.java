package net.lordjayda.erstemod.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.item.Moditems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.naming.directory.ModificationItem;

public class ModCreativeModeTabs {
    public static final CreativeModeTab burger_tab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, "burgers"),
            FabricCreativeModeTab.builder().icon(()-> new ItemStack(Moditems.BURGER))
                            .title(Component.translatable("creativemodetab.erstemod.burgers"))
                            .displayItems((parameters, output) ->{
                                output.accept(Moditems.BURGER);
                                output.accept(Moditems.Tomate);
                                output.accept(Moditems.tomaten_scheibe);
                                output.accept(Moditems.tomato_seed);
                                output.accept(Moditems.lettucehead);
                                output.accept(Moditems.lettuce);
                                output.accept(Moditems.lettuce_seed);
                                output.accept(Moditems.Bun);
                                output.accept(Moditems.top_Bun);
                                output.accept(Moditems.bottom_Bun);
                                output.accept(Moditems.patty);
                                output.accept(Moditems.raw_patty);
                                output.accept(ModBlocks.CUTTING_BOARD);
                            }).build());
//hier neuen output für etwas im creative tab
    public static void registerModCreativeModeTabs(){
        Erstemod.LOGGER.info("Tabs registriert für" + Erstemod.MOD_ID);
    }
}