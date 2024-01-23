package rifted.libmmo.item;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;
import world.rifted.libmmo.registry.Registry;

final class ItemComponentRegistry {

    static final @NotNull Registry<Key, ItemComponentHandler<?>> REGISTRY = Registry.manual(() -> {
        List<ItemComponentHandler<?>> handlers = new ArrayList<>();
        for (ItemComponentHandler<?> handler : ServiceLoader.load(ItemComponentHandler.class)) handlers.add(handler);
        return handlers;
    });

    static final @NotNull Registry<Class<?>, ItemComponentHandler<?>> COMPONENT_CLASS_INDEX = REGISTRY.index(ItemComponentHandler::type);

}
