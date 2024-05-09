package net.itisfarzin.backpacks.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BackpacksDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(BackpacksModelProvider::new);
        pack.addProvider(BackpacksRecipeProvider::new);
        pack.addProvider(BackpacksLanguageProvider::new);
        pack.addProvider(BackpacksAdvancementProvider::new);
    }
}
