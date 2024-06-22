package dev.schmarrn.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.schmarrn.brewing.CustomBrewingRecipes;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.inventory.BrewingStandMenu$PotionSlot")
public class BrewingStandMenuMixin {
    @ModifyReturnValue(
            method = "mayPlaceItem",
            at = @At("RETURN")
    )
    private static boolean bs$mayPlaceItem(boolean original, ItemStack stack) {
        return original || CustomBrewingRecipes.isBasis(stack);
    }
}
