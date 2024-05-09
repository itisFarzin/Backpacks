package net.itisfarzin.backpacks.gui;

import eu.pb4.sgui.api.gui.SimpleGui;
import net.itisfarzin.backpacks.registry.SoundRegistry;
import net.itisfarzin.backpacks.slot.BackpackSlot;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;

public class BackpackGui extends SimpleGui {
    final ItemStack stack;
    final int backpackSlots;
    final DefaultedList<ItemStack> list;
    final NbtComponent component;
    final NbtCompound compound;

    @SuppressWarnings("deprecation")
    public BackpackGui(ServerPlayerEntity player, ItemStack stack, int backpackSlots) {
        super(getHandler(backpackSlots), player, false);
        this.stack = stack;
        this.backpackSlots = backpackSlots;

        setTitle(stack.getName());
        open();

        this.list = DefaultedList.ofSize(this.backpackSlots + 1, ItemStack.EMPTY);
        this.list.set(this.backpackSlots, stack);
        this.component = this.stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        this.compound = this.component.getNbt();
        Inventories.readNbt(this.compound, list, player.getRegistryManager());

        fillBackpack();
    }

    private static ScreenHandlerType<?> getHandler(int backpackSlots) {
        return switch (backpackSlots / 9) {
            case 1 -> ScreenHandlerType.GENERIC_9X1;
            case 2 -> ScreenHandlerType.GENERIC_9X2;
            case 3 -> ScreenHandlerType.GENERIC_9X3;
            case 4 -> ScreenHandlerType.GENERIC_9X4;
            case 5 -> ScreenHandlerType.GENERIC_9X5;
            default -> ScreenHandlerType.GENERIC_9X6;
        };
    }

    private void fillBackpack() {
        Inventory inventory = new SimpleInventory(list.toArray(ItemStack[]::new));

        for (int i = 0; i < this.backpackSlots; i++)
            setSlotRedirect(i, new BackpackSlot(inventory, i, i, 0));

        int invSlot = player.getInventory().getSlotWithStack(stack);
        if (invSlot != -1) {
            screenHandler.setSlot(this.backpackSlots + 27 + invSlot, new Slot(inventory, this.backpackSlots, this.backpackSlots, 0) {

                @Override
                public boolean canTakeItems(PlayerEntity playerEntity) {
                    return false;
                }

                @Override
                public boolean canInsert(ItemStack stack) {
                    return false;
                }
            });
        }
    }

    @Override
    public void onOpen() {
        player.getServerWorld().playSound(null, player.getBlockPos(), SoundRegistry.OPEN_BACKPACK, player.getSoundCategory(), 1F, 1F);
    }

    @Override
    public void onClose() {
        player.getServerWorld().playSound(null, player.getBlockPos(), SoundRegistry.CLOSE_BACKPACK, player.getSoundCategory(), 1F, 1F);

        DefaultedList<ItemStack> inv = DefaultedList.ofSize(this.backpackSlots, ItemStack.EMPTY);

        for (int i = 0; i < this.backpackSlots; i++)
            inv.set(i, slotRedirects[i].getStack());

        Inventories.writeNbt(this.compound, inv, player.getRegistryManager());
        this.stack.set(DataComponentTypes.CUSTOM_DATA, this.component);
    }
}
