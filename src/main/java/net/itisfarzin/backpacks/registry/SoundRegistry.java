package net.itisfarzin.backpacks.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class SoundRegistry {
    public static final SoundEvent OPEN_BACKPACK = SoundEvents.ENTITY_HORSE_ARMOR;
    public static final SoundEvent CLOSE_BACKPACK = OPEN_BACKPACK;
    public static final SoundEvent OPEN_ENDER_BACKPACK = SoundEvents.ENTITY_ENDERMAN_TELEPORT;
    public static final SoundEvent CLOSE_ENDER_BACKPACK = OPEN_ENDER_BACKPACK;

    public static void register() {
        // No custom sounds for now
    }
}
