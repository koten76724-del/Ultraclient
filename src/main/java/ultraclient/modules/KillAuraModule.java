package ultraclient.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class KillAuraModule {
    public static void tick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        for (PlayerEntity target : client.world.getPlayers()) {
            if (target == client.player || !target.isAlive()) continue;
            if (client.player.distanceTo(target) < 4.0) {
                client.interactionManager.attackEntity(client.player, target);
                client.player.swingHand(Hand.MAIN_HAND);
                break;
            }
        }
    }
}
