package ultraclient;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class UltraClient implements ModInitializer {
    public static boolean killAura = false;
    public static boolean autoMiner = false;
    
    private static KeyBinding keyKA;
    private static KeyBinding keyMiner;
    
    public void onInitialize() {
        keyKA = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Toggle KillAura", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "UltraClient"));
        keyMiner = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Toggle Miner", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "UltraClient"));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyKA.wasPressed()) {
                killAura = !killAura;
                client.player.sendMessage(Text.of("KillAura: " + (killAura ? "ON" : "OFF")), false);
            }
            if (keyMiner.wasPressed()) {
                autoMiner = !autoMiner;
                client.player.sendMessage(Text.of("Miner: " + (autoMiner ? "ON" : "OFF")), false);
            }
            if (killAura) KillAuraModule.tick(client);
            if (autoMiner) MinerModule.tick(client);
        });
    }
}
