package net.itisfarzin.backpacks.mixin;


import eu.pb4.sgui.virtual.inventory.VirtualScreenHandler;
import net.itisfarzin.backpacks.item.BackpackItem;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "onClickSlot", at = @At(value = "HEAD"), cancellable = true)
    public void onPlayerAction(ClickSlotC2SPacket packet, CallbackInfo ci) {
        if (packet.getActionType().equals(SlotActionType.SWAP) &&
                player.currentScreenHandler instanceof VirtualScreenHandler &&
                player.getOffHandStack().getItem() instanceof BackpackItem) {
            ci.cancel();
            player.currentScreenHandler.updateToClient();
        }
    }
}
