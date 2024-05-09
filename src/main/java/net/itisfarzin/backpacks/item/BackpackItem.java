package net.itisfarzin.backpacks.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.itisfarzin.backpacks.Backpacks;
import net.itisfarzin.backpacks.gui.BackpackRenameGui;
import net.itisfarzin.backpacks.gui.BackpackGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class BackpackItem extends Item implements PolymerItem {
    final int slots;
    final String backpackType;
    final PolymerModelData polymerModelData;

    public BackpackItem(String backpackType, int slots, Settings settings) {
        super(settings.maxCount(1));
        this.slots = slots;
        this.backpackType = backpackType;
        this.polymerModelData = PolymerResourcePackUtils.requestModel(Items.BUNDLE, Backpacks.id("item/" + backpackType + "_backpack"));
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable("item.backpacks." + backpackType + "_backpack");
    }

    public String getBackpackType() {
        return this.backpackType;
    }

    public void use(ServerPlayerEntity player, ItemStack stack, int slots) {
        new BackpackGui(player, stack, slots);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        var cast = user.raycast(5, 0, false);
        if (cast.getType() == HitResult.Type.BLOCK) return TypedActionResult.pass(stack);
        if (!(user instanceof ServerPlayerEntity player)) return TypedActionResult.pass(stack);
        if (player.isSneaking()) new BackpackRenameGui(player, stack);
        else use(player, stack, slots);
        return TypedActionResult.success(stack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!(context.getPlayer() instanceof ServerPlayerEntity player)) return ActionResult.PASS;
        if (player.isSneaking()) new BackpackRenameGui(player, context.getStack());
        else use(player, context.getStack(), slots);
        return ActionResult.SUCCESS;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModelData.item();
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerModelData.value();
    }
}