package dev.schmarrn.mixin;

import dev.schmarrn.BeegSmol;
import dev.schmarrn.brewing.CustomBrewingRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {
    @Inject(
            method = "canPlaceItem",
            at = @At(value="HEAD"),
            cancellable = true
    )
    private void bs$canPlaceItem(int i, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        BeegSmol.LOGGER.info("Is basis? {}, {}", stack, CustomBrewingRecipes.isBasis(stack));

        if (i != 3 && i != 4 && CustomBrewingRecipes.isBasis(stack)) {
            cir.setReturnValue(true);
        }
    }
}
