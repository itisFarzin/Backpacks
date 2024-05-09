package net.itisfarzin.backpacks.registry;

import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.itisfarzin.backpacks.gui.EnderBackpackGui;
import net.itisfarzin.backpacks.item.BackpackItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import net.itisfarzin.backpacks.Backpacks;
import net.minecraft.util.Rarity;

public class ItemRegistry {

    public static final RegistryKey<ItemGroup> BACKPACKS_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Backpacks.id("data/backpacks"));

    public static final BackpackItem LEATHER_BACKPACK = Registry.register(Registries.ITEM, Backpacks.id("leather_backpack"),
            new BackpackItem("leather", 18, new Item.Settings().rarity(Rarity.COMMON)));
    public static final BackpackItem IRON_BACKPACK = Registry.register(Registries.ITEM, Backpacks.id("iron_backpack"),
            new BackpackItem("iron", 27, new Item.Settings().rarity(Rarity.COMMON)));
    public static final BackpackItem GOLD_BACKPACK = Registry.register(Registries.ITEM, Backpacks.id("gold_backpack"),
            new BackpackItem("gold", 36, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final BackpackItem DIAMOND_BACKPACK = Registry.register(Registries.ITEM, Backpacks.id("diamond_backpack"),
            new BackpackItem("diamond", 45, new Item.Settings().rarity(Rarity.RARE)));
    public static final BackpackItem NETHERITE_BACKPACK = Registry.register(Registries.ITEM, Backpacks.id("netherite_backpack"),
            new BackpackItem("netherite", 54, new Item.Settings().rarity(Rarity.EPIC)));

    public static final BackpackItem ENDER_BACKPACK = Registry.register(Registries.ITEM, Backpacks.id("ender_backpack"), new BackpackItem("ender", 0, new Item.Settings().rarity(Rarity.RARE)) {
        @Override
        public void use(ServerPlayerEntity player, ItemStack stack, int slots) {
            new EnderBackpackGui(player, stack);
        }
    });

    public static void register() {
        // puts all items from this mod into a creative tab
        Registry.register(Registries.ITEM_GROUP, BACKPACKS_GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.backpacks"))
                .icon(() -> new ItemStack(NETHERITE_BACKPACK))
                .entries((group, entries) -> {
                    for (Item item: Registries.ITEM) {
                        if (item instanceof BackpackItem) {
                            entries.add(item);
                        }
                    }
                })
                .build()
        );
        PolymerItemGroupUtils.registerPolymerItemGroup(Backpacks.id(Backpacks.MOD_ID), Registries.ITEM_GROUP.get(BACKPACKS_GROUP));
    }
}
