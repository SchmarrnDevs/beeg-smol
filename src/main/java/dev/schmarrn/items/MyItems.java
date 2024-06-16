package dev.schmarrn.items;

import dev.schmarrn.BeegSmol;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class MyItems {
    public static final Item POTION = Registry.register(BuiltInRegistries.ITEM, BeegSmol.rl("vial"), new Vial(new Item.Properties()));

    public static void init() {
        // no-op
    }
}
