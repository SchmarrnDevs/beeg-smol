package dev.schmarrn;

import dev.schmarrn.components.MobEffectInstancesComponent;
import dev.schmarrn.components.MyComponents;
import dev.schmarrn.items.MyItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.util.FastColor;

public class BeegSmolClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			if (tintIndex > 0) {
				return -1;
			}
			MobEffectInstancesComponent mei = stack.getOrDefault(MyComponents.MOB_EFFECTS, MobEffectInstancesComponent.EMPTY);
			if (mei.effectInstances().isEmpty()) {
				return -1;
			}
			return FastColor.ARGB32.opaque(mei.effectInstances().getFirst().getEffect().value().getColor());
		}, MyItems.VIAL);
	}
}
