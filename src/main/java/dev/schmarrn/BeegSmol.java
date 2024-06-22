package dev.schmarrn;

import dev.schmarrn.brewing.CustomBrewingRecipes;
import dev.schmarrn.components.MyComponents;
import dev.schmarrn.items.MyItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeegSmol implements ModInitializer {
	public static final String MOD_ID = "beeg-smol";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ResourceLocation rl(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		MyMobEffects.init();
		MyComponents.init();

		CustomBrewingRecipes.init();

		MyItems.init();
	}
}