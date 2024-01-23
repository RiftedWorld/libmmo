package rifted.libmmo.item;

import java.util.Collection;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.Material;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

public record ItemImpl(@NotNull ItemRegistry.Entry entry) implements Item {

    @Override
    public @NotNull Component displayName() {
        return Component.text(this.name()); // todo: implement
    }

    @Override
    public @NotNull Material material() {
        return this.entry.material();
    }

    @Override
    public @NotNull Collection<ItemComponent> components() {
        return this.entry.components().values();
    }

    @Override
    public @NotNull NamespaceID id() {
        return this.entry.id();
    }

}
