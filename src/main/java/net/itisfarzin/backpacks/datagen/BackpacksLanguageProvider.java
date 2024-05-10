package net.itisfarzin.backpacks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.itisfarzin.backpacks.registry.ItemRegistry;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BackpacksLanguageProvider extends FabricLanguageProvider {

    protected BackpacksLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ItemRegistry.BACKPACKS_GROUP, "Backpacks");

        translationBuilder.add("item.backpacks.backpack.size", "Size: %sx%s (%s) slots");
        translationBuilder.add("item.backpacks.backpack.how_to_rename", "Sneak + Right click to rename");

        translationBuilder.add(ItemRegistry.LEATHER_BACKPACK, "Leather Backpack");
        translationBuilder.add(ItemRegistry.IRON_BACKPACK, "Iron Backpack");
        translationBuilder.add(ItemRegistry.GOLD_BACKPACK, "Gold Backpack");
        translationBuilder.add(ItemRegistry.DIAMOND_BACKPACK, "Diamond Backpack");
        translationBuilder.add(ItemRegistry.NETHERITE_BACKPACK, "Netherite Backpack");
        translationBuilder.add(ItemRegistry.ENDER_BACKPACK, "Ender Backpack");

        translationBuilder.add("container.backpacks.rename_backpack", "Rename your backpack");
        translationBuilder.add("container.backpacks.rename_backpack.accept", "Accept the change");
        translationBuilder.add("container.backpacks.rename_backpack.reset", "Reset to default name");

        translationBuilder.add("advancements.backpack.root.title", "Backpacks");
        translationBuilder.add("advancements.backpack.root.description", "Seems like you need a backpack");
        translationBuilder.add("advancements.backpack.leather_backpack.title", "Leather Backpack");
        translationBuilder.add("advancements.backpack.leather_backpack.description", "Get your first backpack");
        translationBuilder.add("advancements.backpack.iron_backpack.title", "Iron Backpack");
        translationBuilder.add("advancements.backpack.iron_backpack.description", "");
        translationBuilder.add("advancements.backpack.gold_backpack.title", "Gold Backpack");
        translationBuilder.add("advancements.backpack.gold_backpack.description", "");
        translationBuilder.add("advancements.backpack.diamond_backpack.title", "Diamond Backpack");
        translationBuilder.add("advancements.backpack.diamond_backpack.description", "");
        translationBuilder.add("advancements.backpack.netherite_backpack.title", "Netherite Backpack");
        translationBuilder.add("advancements.backpack.netherite_backpack.description", "Maxed out backpack, Wow");
        translationBuilder.add("advancements.backpack.ender_backpack.title", "Ender Backpack");
        translationBuilder.add("advancements.backpack.ender_backpack.description", "Portable ender chest, Huh?");
    }
}
