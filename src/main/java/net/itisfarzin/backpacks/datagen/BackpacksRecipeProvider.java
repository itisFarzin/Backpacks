package net.itisfarzin.backpacks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.itisfarzin.backpacks.item.BackpackItem;
import net.itisfarzin.backpacks.registry.ItemRegistry;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class BackpacksRecipeProvider extends FabricRecipeProvider {
    public BackpacksRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private void createBackpackRecipe(RecipeExporter exporter, BackpackItem backpack, Item oldBackpack) {
        String backpackType = backpack.getBackpackType();
        Identifier needed_item_Identifier;
        int needed_item_count = 6;

        switch (backpack.getBackpackType()) {
            case "leather" -> {
                needed_item_Identifier = new Identifier("leather");
                needed_item_count = 8;
            }
            case "ender" -> needed_item_Identifier = new Identifier("obsidian");
            case "diamond" -> needed_item_Identifier = new Identifier("diamond");
            case "netherite" -> needed_item_Identifier = new Identifier("netherite_scrap");
            default -> needed_item_Identifier = new Identifier(backpackType + "_ingot");
        }
        Item needed_item = Registries.ITEM.get(needed_item_Identifier);
        Item chest = backpack == ItemRegistry.ENDER_BACKPACK ? Items.ENDER_CHEST : Items.CHEST;

        ShapedRecipeJsonBuilder shapedRecipeJsonBuilder = ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, backpack)
                .pattern("###")
                .pattern(backpack == ItemRegistry.LEATHER_BACKPACK ? "#C#" : "CBC")
                .pattern("###")
                .input('#', needed_item)
                .input('C', chest)
                .criterion("has_the_" + Registries.ITEM.getId(needed_item).getPath(), InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().items(needed_item).count(NumberRange.IntRange.atLeast(needed_item_count))
                ));

        if (oldBackpack != null)
            shapedRecipeJsonBuilder.input('B', oldBackpack);

        shapedRecipeJsonBuilder.offerTo(exporter);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        createBackpackRecipe(exporter, ItemRegistry.LEATHER_BACKPACK, null);
        createBackpackRecipe(exporter, ItemRegistry.ENDER_BACKPACK, ItemRegistry.LEATHER_BACKPACK);
        createBackpackRecipe(exporter, ItemRegistry.IRON_BACKPACK, ItemRegistry.LEATHER_BACKPACK);
        createBackpackRecipe(exporter, ItemRegistry.GOLD_BACKPACK, ItemRegistry.IRON_BACKPACK);
        createBackpackRecipe(exporter, ItemRegistry.DIAMOND_BACKPACK, ItemRegistry.GOLD_BACKPACK);
        createBackpackRecipe(exporter, ItemRegistry.NETHERITE_BACKPACK, ItemRegistry.DIAMOND_BACKPACK);
    }
}
