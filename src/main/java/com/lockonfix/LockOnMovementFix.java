package com.lockonfix;

import com.lockonfix.compat.IntegrationRegistry;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.slf4j.Logger;

@Mod(LockOnMovementFix.MOD_ID)
public class LockOnMovementFix {
    public static final String MOD_ID = "lockonmovementfix";
    private static final Logger LOGGER = LogUtils.getLogger();

    // A registered category since 1.21.9; its label is translated from key.category.lockonmovementfix.main.
    public static final KeyMapping.Category KEY_CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(MOD_ID, "main"));
    public static KeyMapping TOGGLE_AUTO_LOCKON;

    public LockOnMovementFix(IEventBus modBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, FixConfig.CLIENT_CONFIG, "lockonmovementfix-client.toml");

        modBus.addListener(this::onCommonSetup);
        modBus.addListener(this::onRegisterKeyMappings);

        LOGGER.info("Epic Fight x Better Lock On: Movement Fixes (NeoForge 1.21.1) loaded.");
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        IntegrationRegistry.resolve();
    }

    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.registerCategory(KEY_CATEGORY);
        TOGGLE_AUTO_LOCKON = new KeyMapping(
            "key.lockonfix.toggle_auto_lockon",
            InputConstants.UNKNOWN.getValue(),
            KEY_CATEGORY
        );
        event.register(TOGGLE_AUTO_LOCKON);
    }
}
