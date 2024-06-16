package dev.schmarrn.items;

import dev.schmarrn.components.MobEffectInstancesComponent;
import dev.schmarrn.components.MyComponents;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Vial extends Item {

    public Vial(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = super.getDefaultInstance();
        itemStack.set(MyComponents.MOB_EFFECTS, MobEffectInstancesComponent.EMPTY);
        return itemStack;
    }

    public static @NotNull ItemStack getWithEffect(MobEffectInstance me) {
        return getWithEffects(List.of(me));
    }

    public static @NotNull ItemStack getWithEffects(List<MobEffectInstance> mes) {
        ItemStack itemStack = MyItems.POTION.getDefaultInstance();
        itemStack.set(MyComponents.MOB_EFFECTS, new MobEffectInstancesComponent(mes));
        return itemStack;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide) {
            MobEffectInstancesComponent mobEffectInstancesComponent = itemStack.getOrDefault(MyComponents.MOB_EFFECTS, MobEffectInstancesComponent.EMPTY);
            for (MobEffectInstance entry : mobEffectInstancesComponent.effectInstances()) {
                livingEntity.addEffect(entry);
            }
        }

        Player player = livingEntity instanceof Player ? (Player)livingEntity : null;

        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, itemStack);
        }
        if (player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            itemStack.consume(1, player);
        }
        if (player == null || !player.hasInfiniteMaterials()) {
            if (itemStack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }
            if (player != null) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
        livingEntity.gameEvent(GameEvent.DRINK);
        return itemStack;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 32;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }
}
