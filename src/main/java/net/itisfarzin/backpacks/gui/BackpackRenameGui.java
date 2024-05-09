package net.itisfarzin.backpacks.gui;

import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.sgui.api.gui.AnvilInputGui;
import net.itisfarzin.backpacks.Backpacks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class BackpackRenameGui extends AnvilInputGui {
    final ItemStack stack;
    final ItemStack newItemStack;

    final PolymerModelData accept = PolymerResourcePackUtils.requestModel(Items.LIME_DYE, Backpacks.id("item/accept_button"));
    final PolymerModelData reset = PolymerResourcePackUtils.requestModel(Items.RED_DYE, Backpacks.id("item/reset_button"));

    final ItemStack acceptItem = accept.item().getDefaultStack();
    final ItemStack resetItem = reset.item().getDefaultStack();

    public BackpackRenameGui(ServerPlayerEntity player, ItemStack stack) {
        super(player, true);
        setTitle(Text.translatable("container.backpacks.rename_backpack"));
        setDefaultInputValue(stack.getName().getString());
        open();

        this.stack = stack;
        this.newItemStack = this.stack.copy();
        this.newItemStack.set(DataComponentTypes.CUSTOM_NAME, this.stack.getName());

        this.acceptItem.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("container.backpacks.rename_backpack.accept"));
        this.resetItem.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("container.backpacks.rename_backpack.reset"));

        this.setSlot(0, this.newItemStack);
        this.setSlot(1, this.resetItem, ((index, type1, action, gui) -> {
            if (action.equals(SlotActionType.PICKUP)) {
                this.stack.remove(DataComponentTypes.CUSTOM_NAME);
                close();
            }
        }));
    }

    @Override
    public void onInput(String input) {
        this.newItemStack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(getInput()));

        this.setSlot(2, this.acceptItem, ((index, type1, action, gui) -> {
            if (action.equals(SlotActionType.PICKUP)) {
                this.stack.set(DataComponentTypes.CUSTOM_NAME, this.newItemStack.getName());
                close();
            }
        }));
    }
}
