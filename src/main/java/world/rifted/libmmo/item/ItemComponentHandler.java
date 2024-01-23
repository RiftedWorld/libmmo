package rifted.libmmo.item;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minestom.server.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import world.rifted.libmmo.registry.MinestomCodecs;
import world.rifted.libmmo.registry.Resource;

public interface ItemComponentHandler<C extends ItemComponent> extends Resource {

    @NotNull Codec<ItemComponentHandler<?>> CODEC = MinestomCodecs.NAMESPACE_ID.xmap(
            id -> ItemComponentRegistry.REGISTRY.get(id).orElseThrow(() -> new IllegalStateException("Missing item component handler for " + id)),
            Resource::id
    );

    static <C extends ItemComponent> @NotNull Optional<ItemComponentHandler<C>> from(@NotNull Class<C> type) {
        //noinspection unchecked
        return ItemComponentRegistry.COMPONENT_CLASS_INDEX.get(type).map(handler -> (ItemComponentHandler<C>) handler);
    }

    static <C extends ItemComponent> @NotNull Optional<ItemComponentHandler<C>> from(@NotNull C component) {
        //noinspection unchecked
        return from((Class<C>) component.getClass());
    }

    @NotNull Class<C> type();

    @NotNull Codec<C> codec();

    default int priority() {
        return 0;
    }

    default void apply(@NotNull C component, @NotNull ItemStack.Builder builder) {

    }

}
