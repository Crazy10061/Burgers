package net.lordjayda.erstemod.menu.custom;

import net.lordjayda.erstemod.menu.ModMenuTypes;
import net.lordjayda.erstemod.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AssemblerMenu extends AbstractContainerMenu {
    private final Container inventory;

    public AssemblerMenu(int containerId, Inventory inv, BlockPos blockPos) {
        this(containerId, inv, inv.player.level().getBlockEntity(blockPos));
    }

    public AssemblerMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.ASSEMBLER_MENU, containerId);
        this.inventory = ((Container) blockEntity);

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        //bottom bun
        addSlot(new Slot(inventory, 0, 80, 64) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.BOTTOM_BUN);
            }
        });

        //Ingredient Slot 1
        addSlot(new Slot(inventory, 1, 8, 34) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
            return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
        });

        //Ingredient Slot 2
        addSlot(new Slot(inventory, 2, 44, 34) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }

        });

        //Ingredient Slot 3
        addSlot(new Slot(inventory, 3, 80, 34) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
        });

        //Ingredient Slot 4
        addSlot(new Slot(inventory, 4, 116, 34) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
        });

        //Ingredient Slot 5
        addSlot(new Slot(inventory, 5, 152, 34) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ASSEMBLER_INGREDIENTS);
            }
        });

        //sauce 1
        addSlot(new Slot(inventory, 6, 116, 5) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.SAUCES);
            }
        });

        //sauce 2
        addSlot(new Slot(inventory, 7, 152, 5) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.SAUCES);
            }
        });
        //top bun
        addSlot(new Slot(inventory, 8, 80, 5) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.TOP_BUN);
            }
        });
        // Result Slot
        addSlot(new Slot(inventory, 9, 80, 115) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 10;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveSingleItemToAssembler(sourceStack)) {
                return ItemStack.EMPTY;
            }
         return ItemStack.EMPTY;  // EMPTY_ITEM
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.inventory.stillValid(pPlayer);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private boolean moveSingleItemToAssembler(ItemStack stack) {
        System.out.println("Move: " + stack);

        for(int i = TE_INVENTORY_FIRST_SLOT_INDEX;
            i < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT;
            i++) {

            System.out.println("Check Slot: " + i);

            Slot slot = slots.get(i);

            if(slot.mayPlace(stack) && !slot.hasItem()) {
                System.out.println("Found slot");

                ItemStack copy = stack.copy();
                copy.setCount(1);

                slot.set(copy);
                stack.shrink(1);

                return true;
            }
        }

        return false;
    }

}