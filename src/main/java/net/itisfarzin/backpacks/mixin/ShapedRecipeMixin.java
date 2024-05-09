package net.itisfarzin.backpacks.mixin;

import net.itisfarzin.backpacks.item.BackpackItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.RegistryWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin {

    @Inject(method = "craft*", at = @At("TAIL"), cancellable = true)
    public void craft(RecipeInputInventory recipeInputInventory, RegistryWrapper.WrapperLookup wrapperLookup, CallbackInfoReturnable<ItemStack> ci) {
        ItemStack item = recipeInputInventory.getStack(4);
        if (!item.isEmpty() & item.getItem() instanceof BackpackItem) {
            ItemStack out = ci.getReturnValue();
            out.set(DataComponentTypes.CUSTOM_DATA, item.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT));
            ci.setReturnValue(out);
        }
    }
}
