package net.lordjayda.erstemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.lordjayda.erstemod.block.entity.custom.CuttingBoardBlockEntity;
import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.Map;


public class CuttingBoard extends BaseEntityBlock {

    private static final MapCodec<CuttingBoard> CODEC= simpleCodec(CuttingBoard::new);

    public static final Map<Direction, VoxelShape> SHAPES = Map.of(
            Direction.NORTH, Shapes.or(
                    Block.box(1, 0, 1, 16, 2, 10),

                    Block.box(11, 5, 6, 15, 6, 7),
                    Block.box(11, 3, 5, 15, 5, 6),
                    Block.box(11, 2, 6, 15, 3, 7),
                    Block.box(11, 3, 6, 15, 5, 7),
                    Block.box(11, 3, 7, 15, 5, 8),

                    Block.box(5, 4, 6, 11, 4.25, 7),
                    Block.box(4, 4, 7, 11, 4.25, 8),
                    Block.box(6, 4, 5, 11, 4.25, 6)
            ),

            Direction.SOUTH, Shapes.or(
                    Block.box(0, 0, 6, 15, 2, 15),

                    Block.box(1, 5, 9, 5, 6, 10),
                    Block.box(1, 3, 10, 5, 5, 11),
                    Block.box(1, 2, 9, 5, 3, 10),
                    Block.box(1, 3, 9, 5, 5, 10),
                    Block.box(1, 3, 8, 5, 5, 9),

                    Block.box(5, 4, 9, 11, 4.25, 10),
                    Block.box(5, 4, 8, 12, 4.25, 9),
                    Block.box(5, 4, 10, 10, 4.25, 11)
            ),


            Direction.EAST, Shapes.or(
                    Block.box(6, 0, 1, 15, 2, 16),

                    Block.box(9, 5, 11, 10, 6, 15),
                    Block.box(10, 3, 11, 11, 5, 15),
                    Block.box(9, 2, 11, 10, 3, 15),
                    Block.box(9, 3, 11, 10, 5, 15),
                    Block.box(8, 3, 11, 9, 5, 15),

                    Block.box(9, 4, 5, 10, 4.25, 11),
                    Block.box(8, 4, 4, 9, 4.25, 11),
                    Block.box(10, 4, 6, 11, 4.25, 11)
            ),

            Direction.WEST, Shapes.or(
                    Block.box(1, 0, 0, 10, 2, 15),

                    Block.box(6, 5, 1, 7, 6, 5),
                    Block.box(5, 3, 1, 6, 5, 5),
                    Block.box(6, 2, 1, 7, 3, 5),
                    Block.box(6, 3, 1, 7, 5, 5),
                    Block.box(7, 3, 1, 8, 5, 5),

                    Block.box(6, 4, 5, 7, 4.25, 11),
                    Block.box(7, 4, 5, 8, 4.25, 12),
                    Block.box(5, 4, 5, 6, 4.25, 10)
            )
    );
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;



    public CuttingBoard(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.addParticle(ParticleTypes.ANGRY_VILLAGER, pos.getX() + 0.5, pos.getY() -0.6, pos.getZ() +0.5, 0 ,1 ,0);

        level.playLocalSound(player, SoundEvents.ANVIL_HIT , SoundSource.BLOCKS, 2f,  1f );

        return InteractionResult.SUCCESS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    /*@Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ModItems.TOMATO)){ itemStack.shrink(1);
            player.addItem(new ItemStack(ModItems.TOMATO_SLICE, 4));
        }
        if (stack.is(ModItems.LETTUCEHEAD)){ itemStack.shrink(1);
            player.addItem(new ItemStack(ModItems.LETTUCE, 4));
        }
        if (stack.is(ModItems.BUN)){ itemStack.shrink(1);
            player.addItem( new ItemStack(ModItems.TOP_BUN, 1));
            player.addItem(new ItemStack(ModItems.BOTTOM_BUN, 1));
        }
        if (stack.is(Items.BEEF)) {
            itemStack.shrink(1);
            player.addItem(new ItemStack(ModItems.RAW_PATTY,1));
        }
        return InteractionResult.SUCCESS;
    }
     */


    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof CuttingBoardBlockEntity cuttingBoardBlockEntity) {
            boolean isCuttingBoardempty = cuttingBoardBlockEntity.isEmpty();

            //Insert
            if (isCuttingBoardempty && !itemStack.isEmpty()) {
                cuttingBoardBlockEntity.setTheItem(itemStack);
                itemStack.shrink(1);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            }
            //Extract
            else if (!isCuttingBoardempty) {
                ItemStack stackOnCuttingBoard = cuttingBoardBlockEntity.getTheItem();

                if (player.isShiftKeyDown()) {
                    ItemStack resultStack = ItemStack.EMPTY;

                    if (stackOnCuttingBoard.is(ModItems.TOMATO)) {
                        resultStack = new ItemStack(ModItems.TOMATO_SLICE, 4);
                    } else if (stackOnCuttingBoard.is(ModItems.LETTUCEHEAD)) {
                        resultStack = new ItemStack(ModItems.LETTUCE, 4);
                    } else if (stackOnCuttingBoard.is(Items.BEEF)) {
                        resultStack = new ItemStack(ModItems.RAW_PATTY);
                    } else
                    if (stackOnCuttingBoard.is(ModItems.BUN)) {
                        cuttingBoardBlockEntity.clearContent();

                        ItemStack bottomBun = new ItemStack(ModItems.BOTTOM_BUN);
                        ItemStack topBun = new ItemStack(ModItems.TOP_BUN);

                        if (!player.getInventory().add(bottomBun)) {
                            player.drop(bottomBun, false);
                        }

                        if (!player.getInventory().add(topBun)) {
                            player.drop(topBun, false);
                        }
                    }

                    if (!resultStack.isEmpty()) {
                        cuttingBoardBlockEntity.clearContent();

                        if (!player.getInventory().add(resultStack)) {
                            player.drop(resultStack, false);
                        }
                    }
                } else {
                    cuttingBoardBlockEntity.clearContent();

                    if (!player.getInventory().add(stackOnCuttingBoard)) {
                        player.drop(stackOnCuttingBoard, false);
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (level instanceof ServerLevel serverLevel
                && entity instanceof Player player) {

            player.hurtServer(
                    serverLevel,
                    level.damageSources().cactus(),
                    4.0F
            );
        }
        super.stepOn(level, pos, state, entity);




    }

    @Override
    public void playerDestroy(Level level, Player player,
                              BlockPos pos, BlockState state,
                              @Nullable BlockEntity blockEntity,
                              ItemStack destroyedWith) {

        if(level.getBlockEntity(pos) instanceof CuttingBoardBlockEntity cuttingBoardBlockEntity) {
            cuttingBoardBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }

        super.playerDestroy(
                level,
                player,
                pos,
                state,
                blockEntity,
                destroyedWith
        );
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new CuttingBoardBlockEntity(worldPosition, blockState);
    }
}
