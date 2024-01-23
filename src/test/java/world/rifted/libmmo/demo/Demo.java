package world.rifted.libmmo.demo;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.lan.OpenToLAN;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;
import rifted.libmmo.item.Item;

public final class Demo {

    public static void main(@NotNull String[] args) {
        MinecraftServer server = MinecraftServer.init();

        InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer();
        instance.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        GlobalEventHandler eventNode = MinecraftServer.getGlobalEventHandler();
        eventNode.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            event.setSpawningInstance(instance);
            event.getPlayer().setRespawnPoint(new Pos(0, 42, 0));
        });
        eventNode.addListener(PlayerSpawnEvent.class, event -> event.getPlayer().getInventory().addItemStack(Item.fromId(NamespaceID.from("libmmo:demo_item")).asItemStack()));

        MojangAuth.init();
        OpenToLAN.open();

        server.start("0.0.0.0", 25565);
    }

}
