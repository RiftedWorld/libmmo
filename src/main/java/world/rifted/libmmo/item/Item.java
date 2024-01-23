package rifted.libmmo.item;

import com.mojang.datafixers.util.Pair;
import java.util.Collection;
import java.util.Comparator;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.ItemHideFlag;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.tag.Tag;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;
import world.rifted.libmmo.registry.Resource;

public interface Item extends Resource {

    @NotNull Tag<NamespaceID> ID_TAG = Tag.String("id").map(NamespaceID::from, NamespaceID::asString);

    @NotNull Component displayName();

    @NotNull Material material();

    @NotNull Collection<ItemComponent> components();

    default @NotNull ItemStack asItemStack() {
        ItemStack.Builder builder = ItemStack.builder(this.material());

        this.components().stream()
                .map(component -> new Pair<>(component, ItemComponentHandler.from(component)))
                .filter(pair -> pair.getSecond().isPresent())
                .map(pair -> new Pair<>(pair.getFirst(), pair.getSecond().get()))
                .sorted(Comparator.comparingInt(pair -> pair.getSecond().priority()))
                .forEach(pair -> pair.getSecond().apply(pair.getFirst(), builder));

        return builder
                .displayName(this.displayName())
                .meta(meta -> {
                    meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES);
                    meta.setTag(ID_TAG, this.id());
                }).build();
    }

    static @NotNull Item fromId(@NotNull NamespaceID id) {
        return ItemRegistry.REGISTRY.get(id).orElseThrow(() -> new IllegalStateException("Missing item for " + id));
    }

    static @NotNull Item fromItemStack(@NotNull ItemStack item) {
        NamespaceID id = item.meta().getTag(ID_TAG);
        if (id == null) throw new IllegalStateException("ItemStack " + item + " does not have an ID tag");
        return Item.fromId(id);
    }

}
