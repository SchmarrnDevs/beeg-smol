package dev.schmarrn.components;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public record MobEffectInstancesComponent(List<MobEffectInstance> effectInstances) {
    public static final MobEffectInstancesComponent EMPTY = new MobEffectInstancesComponent(List.of());
    public static final Codec<MobEffectInstancesComponent> CODEC = MobEffectInstance.CODEC.listOf().xmap(MobEffectInstancesComponent::new, MobEffectInstancesComponent::effectInstances);
    public static final StreamCodec<RegistryFriendlyByteBuf, MobEffectInstancesComponent> STREAM_CODEC = MobEffectInstance.STREAM_CODEC.apply(ByteBufCodecs.list()).map(MobEffectInstancesComponent::new, MobEffectInstancesComponent::effectInstances);
}
