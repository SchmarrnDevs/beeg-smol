package dev.schmarrn.mixin;

import dev.schmarrn.brewing.IBrewingRecipe;
import dev.schmarrn.brewing.CustomBrewingRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

@Mixin(PotionBrewing.class)
public class PotionBrewingMixin {
	@ModifyReturnValue(
		method = "isPotionIngredient",
		at = @At("RETURN")
	)
	private boolean bs$isPotionIngredient(boolean original, ItemStack ingredient) {
		return original || CustomBrewingRecipes.isIngredient(ingredient);
	}

	@ModifyReturnValue(
			method = "isContainer",
			at = @At("RETURN")
	)
	private boolean bs$isContainer(boolean original, ItemStack ingredient) {
		return original || CustomBrewingRecipes.isBasis(ingredient);
	}

	@ModifyReturnValue(
		method = "hasPotionMix",
		at = @At("RETURN")
	)
	private boolean bs$hasPotionMix(boolean original, ItemStack base, ItemStack ingredient) {
		return original || CustomBrewingRecipes.isBasis(base) && CustomBrewingRecipes.isIngredient(ingredient);
	}

	@ModifyReturnValue(
		method = "mix",
		at = @At("RETURN")
	)
	private ItemStack bs$mix(ItemStack original, ItemStack ingredient, ItemStack base) {
		// TODO: Better Mixin Location
		for (IBrewingRecipe recipe : CustomBrewingRecipes.RECIPES) {
			if (recipe.isBase(base) && recipe.isIngredient(ingredient)) {
				return recipe.getOutput(base, ingredient);
			}
		}

		return original;
	}
}