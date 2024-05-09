package net.itisfarzin.backpacks.slot;

import net.itisfarzin.backpacks.item.BackpackItem;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class BackpackSlot extends Slot {
    public BackpackSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if (stack.getItem() instanceof BackpackItem)
            return false;

        if (stack.getItem() instanceof final BlockItem _item) {
            return !(_item.getBlock() instanceof ShulkerBoxBlock);
        }
        return true;
    }
}
