package dev.schmarrn.brewing;

import net.minecraft.world.item.ItemStack;

/**
 * Inspired by neoforge: https://github.com/neoforged/NeoForge/blob/f923568cc0595b90034739ecc96af367ecacbdd5/src/main/java/net/neoforged/neoforge/common/brewing/IBrewingRecipe.java
 */
public interface IBrewingRecipe {
    boolean isBase(ItemStack itemStack);
    boolean isIngredient(ItemStack itemStack);
    ItemStack getOutput(ItemStack base, ItemStack ingredient);
}
