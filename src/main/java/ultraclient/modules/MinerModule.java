package ultraclient.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class MinerModule {
    public static void tick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        for (int dy = -2; dy <= 2; dy++) {
            for (int dx = -5; dx <= 5; dx++) {
                for (int dz = -5; dz <= 5; dz++) {
                    BlockPos pos = client.player.getBlockPos().add(dx, dy, dz);
                    Block block = client.world.getBlockState(pos).getBlock();
                    if (isOre(block)) {
                        client.interactionManager.attackBlock(pos, client.player.getHorizontalFacing());
                        client.player.swingHand(Hand.MAIN_HAND);
                        return;
                    }
                }
            }
        }
    }
    private static boolean isOre(Block b) {
        return b == Blocks.DIAMOND_ORE || b == Blocks.IRON_ORE || b == Blocks.GOLD_ORE || b == Blocks.COAL_ORE;
    }
}
