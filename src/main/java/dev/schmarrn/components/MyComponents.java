package dev.schmarrn.components;

import dev.schmarrn.BeegSmol;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class MyComponents {
    public static final DataComponentType<MobEffectInstancesComponent> MOB_EFFECTS = MyComponents.register(
            "mobeffects", builder -> builder.persistent(MobEffectInstancesComponent.CODEC).networkSynchronized(MobEffectInstancesComponent.STREAM_CODEC).cacheEncoding());

    private static <T> DataComponentType<T> register(String string, UnaryOperator<DataComponentType.Builder<T>> unaryOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, BeegSmol.rl(string), ((DataComponentType.Builder)unaryOperator.apply(DataComponentType.builder())).build());
    }

    public static void init() {
        // no-op
    }
}
