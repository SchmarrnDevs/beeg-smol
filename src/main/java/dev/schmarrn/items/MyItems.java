package dev.schmarrn.items;

import dev.schmarrn.BeegSmol;
import dev.schmarrn.MyMobEffects;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class MyItems {
    public static final Item VIAL = Registry.register(BuiltInRegistries.ITEM, BeegSmol.rl("vial"), new Vial(new Item.Properties().stacksTo(1)));

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(Vial.getWithEffect(MyMobEffects.BIG_INSTANCE));
            entries.accept(Vial.getWithEffect(MyMobEffects.SMALL_INSTANCE));
        });
    }
}
