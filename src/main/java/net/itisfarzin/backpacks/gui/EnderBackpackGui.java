package net.itisfarzin.backpacks.gui;

import eu.pb4.sgui.api.gui.SimpleGui;
import net.itisfarzin.backpacks.registry.SoundRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;

public class EnderBackpackGui extends SimpleGui {
    public EnderBackpackGui(ServerPlayerEntity player, ItemStack stack) {
        super(ScreenHandlerType.GENERIC_9X3, player, false);

        setTitle(stack.getName());
        open();

        fillBackpack();
    }

    private void fillBackpack() {
        for (int i = 0; i < 27; i++)
            setSlotRedirect(i, new Slot(player.getEnderChestInventory(), i, i, 0));
    }

    public void onOpen() {
        player.getServerWorld().playSound(null, player.getBlockPos(), SoundRegistry.OPEN_ENDER_BACKPACK, player.getSoundCategory(), 1F, 1F);
    }

    public void onClose() {
        player.getServerWorld().playSound(null, player.getBlockPos(), SoundRegistry.CLOSE_ENDER_BACKPACK, player.getSoundCategory(), 1F, 1F);
    }
}
