package dev.schmarrn;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MyMobEffects {
    public static final Holder<MobEffect> BEEG = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, BeegSmol.rl("beeg"), new MyMobEffect(MobEffectCategory.NEUTRAL, 0xd092ef).addAttributeModifier(Attributes.SCALE, BeegSmol.rl("effect.beeg"), 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> SMOL = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, BeegSmol.rl("smol"), new MyMobEffect(MobEffectCategory.NEUTRAL, 0x92efb6).addAttributeModifier(Attributes.SCALE, BeegSmol.rl("effect.smol"), -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final MobEffectInstance BIG_INSTANCE = getMobEffectInstance(BEEG, 0);
    public static final MobEffectInstance SMALL_INSTANCE = getMobEffectInstance(SMOL, 0);

    private static MobEffectInstance getMobEffectInstance(Holder<MobEffect> effect, int amplifier) {
        int duration = -1;
        boolean ambient = false;
        boolean visible = false;
        boolean showIcon = false;

        return new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon);
    }

    public static void init() {
        // no-op
    }
}
