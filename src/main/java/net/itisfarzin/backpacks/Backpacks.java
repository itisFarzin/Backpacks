package net.itisfarzin.backpacks;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.itisfarzin.backpacks.registry.ItemRegistry;
import net.itisfarzin.backpacks.registry.SoundRegistry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Backpacks implements ModInitializer {

	// Mod ID
	public static final String MOD_ID = "backpacks";
	// Logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ItemRegistry.register();
		SoundRegistry.register();
		LOGGER.info("Initialized Backpacks for Fabric");
		PolymerResourcePackUtils.addModAssets(Backpacks.MOD_ID);
		PolymerResourcePackUtils.getInstance().setPackDescription("Resource pack for Backpacks Mod");
    }
}