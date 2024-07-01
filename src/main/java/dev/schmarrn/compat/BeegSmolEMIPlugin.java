package dev.schmarrn.compat;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.recipe.EmiBrewingRecipe;
import dev.schmarrn.BeegSmol;
import dev.schmarrn.MyMobEffects;
import dev.schmarrn.items.Vial;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Optional;

public class BeegSmolEMIPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        ItemStack thickPotionStack = Items.POTION.getDefaultInstance();
        thickPotionStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Optional.of(Potions.THICK), Optional.empty(), List.of()));
        registry.addRecipe(new EmiBrewingRecipe(EmiStack.of(thickPotionStack), EmiIngredient.of(Ingredient.of(Items.RED_MUSHROOM)), EmiStack.of(Vial.getWithEffect(MyMobEffects.BIG_INSTANCE)), BeegSmol.rl("beeg_vial_brewing_recipe")));
        registry.addRecipe(new EmiBrewingRecipe(EmiStack.of(Vial.getWithEffect(MyMobEffects.BIG_INSTANCE)), EmiIngredient.of(Ingredient.of(Items.FERMENTED_SPIDER_EYE)), EmiStack.of(Vial.getWithEffect(MyMobEffects.SMALL_INSTANCE)), BeegSmol.rl("smol_vial_brewing_recipe")));
    }
}
