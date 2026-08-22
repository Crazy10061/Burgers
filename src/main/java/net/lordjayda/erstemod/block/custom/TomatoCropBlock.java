package net.lordjayda.erstemod.block.custom;

import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class TomatoCropBlock extends CropBlock {

    public static final int MAX_AGE = 1;
    public static final IntegerProperty AGE = IntegerProperty.create( "age", 0, MAX_AGE);
    private static final VoxelShape[] SHAPES = {
            Block.box(7, 0, 5, 10, 15, 9),
            Block.box(6, 0, 5, 10, 15, 9)
    };

    public TomatoCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull ItemLike getBaseSeedId() {
        return ModItems.TOMATO_SEED;
    }

    @Override
    protected @NonNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected @NonNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(AGE)];
    }

}
