package net.itisfarzin.backpacks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.itisfarzin.backpacks.registry.ItemRegistry;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class BackpacksModelProvider extends FabricModelProvider {
    public BackpacksModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistry.LEATHER_BACKPACK, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.IRON_BACKPACK, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.GOLD_BACKPACK, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.DIAMOND_BACKPACK, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.NETHERITE_BACKPACK, Models.GENERATED);
        itemModelGenerator.register(ItemRegistry.ENDER_BACKPACK, Models.GENERATED);
    }
}
