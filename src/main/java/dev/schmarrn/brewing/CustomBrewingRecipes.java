package dev.schmarrn.brewing;

import dev.schmarrn.MyMobEffects;
import dev.schmarrn.components.MobEffectInstancesComponent;
import dev.schmarrn.components.MyComponents;
import dev.schmarrn.items.Vial;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomBrewingRecipes {
    public static final List<IBrewingRecipe> RECIPES = new ArrayList<>();
    public static final IBrewingRecipe BEEG = new IBrewingRecipe() {
        @Override
        public boolean isBase(ItemStack base) {
            Optional<Holder<Potion>> basePotion = base.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion();
            return basePotion.filter(Potions.THICK::is).isPresent();
        }

        @Override
        public boolean isIngredient(ItemStack itemStack) {
            return itemStack.is(Items.RED_MUSHROOM);
        }

        @Override
        public ItemStack getOutput(ItemStack base, ItemStack ingredient) {
            return Vial.getWithEffect(MyMobEffects.BIG_INSTANCE);
        }
    };

    public static final IBrewingRecipe SMOL = new IBrewingRecipe() {
        @Override
        public boolean isBase(ItemStack base) {
            var mobEffects = base.getOrDefault(MyComponents.MOB_EFFECTS, MobEffectInstancesComponent.EMPTY).effectInstances();
            return mobEffects.size() == 1 && mobEffects.getFirst().equals(MyMobEffects.BIG_INSTANCE);
        }

        @Override
        public boolean isIngredient(ItemStack itemStack) {
            return itemStack.is(Items.FERMENTED_SPIDER_EYE);
        }

        @Override
        public ItemStack getOutput(ItemStack base, ItemStack ingredient) {
            return Vial.getWithEffect(MyMobEffects.SMALL_INSTANCE);
        }
    };


    static {
        register(BEEG);
        register(SMOL);
    }

    public static boolean isRecipe(ItemStack base, ItemStack ingredient) {
        for (IBrewingRecipe recipe : RECIPES) {
            if (recipe.isBase(base) && recipe.isIngredient(ingredient)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIngredient(ItemStack stack) {
        for (IBrewingRecipe recipe : RECIPES) {
            if (recipe.isIngredient(stack)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBasis(ItemStack stack) {
        for (IBrewingRecipe recipe : RECIPES) {
            if (recipe.isBase(stack)) {
                return true;
            }
        }

        return false;
    }

    private static void register(IBrewingRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static void init() {
        // no-op
    }
}
