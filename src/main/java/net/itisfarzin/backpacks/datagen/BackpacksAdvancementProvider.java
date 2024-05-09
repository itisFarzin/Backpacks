package net.itisfarzin.backpacks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.itisfarzin.backpacks.Backpacks;
import net.itisfarzin.backpacks.item.BackpackItem;
import net.itisfarzin.backpacks.registry.ItemRegistry;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BackpacksAdvancementProvider extends FabricAdvancementProvider {
    public static AdvancementEntry rootAdvancement;
    public static AdvancementEntry leatherBackpackAdvancement;
    public static AdvancementEntry ironBackpackAdvancement;
    public static AdvancementEntry goldBackpackAdvancement;
    public static AdvancementEntry diamondBackpackAdvancement;
    public static AdvancementEntry netheriteBackpackAdvancement;
    public static AdvancementEntry enderBackpackAdvancement;

    protected BackpacksAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }
    private AdvancementEntry createBackpackAdvancement(Consumer<AdvancementEntry> consumer, BackpackItem backpack, AdvancementEntry parent) {
        String backpackType = backpack.getBackpackType();

        Advancement.Builder advancement = new Advancement.Builder().parent(parent)
                .display(
                        backpack,
                        Text.translatable("advancements.backpack." + backpackType + "_backpack.title"),
                        Text.translatable("advancements.backpack." + backpackType + "_backpack.description"),
                        null,
                        backpackType.equals("netherite") ? AdvancementFrame.CHALLENGE : AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_the_backpack", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().items(backpack)
                ));
        AdvancementEntry advancementEntry = advancement.build(Backpacks.id("backpacks/" + backpackType + "_backpack"));
        consumer.accept(advancementEntry);
        return advancementEntry;
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        rootAdvancement = new Advancement.Builder()
                .display(
                        ItemRegistry.NETHERITE_BACKPACK,
                        Text.translatable("advancements.backpack.root.title"),
                        Text.translatable("advancements.backpack.root.description"),
                        new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.TASK,
                        true,
                        false,
                        false
                )
                .criterion("requirement", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().count(NumberRange.IntRange.atLeast(32))))
                .build(Backpacks.id("backpacks/root"));
        consumer.accept(rootAdvancement);

        leatherBackpackAdvancement = createBackpackAdvancement(consumer, ItemRegistry.LEATHER_BACKPACK, rootAdvancement);
        ironBackpackAdvancement = createBackpackAdvancement(consumer, ItemRegistry.IRON_BACKPACK, leatherBackpackAdvancement);
        goldBackpackAdvancement = createBackpackAdvancement(consumer, ItemRegistry.GOLD_BACKPACK, ironBackpackAdvancement);
        diamondBackpackAdvancement = createBackpackAdvancement(consumer, ItemRegistry.DIAMOND_BACKPACK, goldBackpackAdvancement);
        netheriteBackpackAdvancement = createBackpackAdvancement(consumer, ItemRegistry.NETHERITE_BACKPACK, diamondBackpackAdvancement);
        enderBackpackAdvancement = createBackpackAdvancement(consumer, ItemRegistry.ENDER_BACKPACK, rootAdvancement);
    }
}
