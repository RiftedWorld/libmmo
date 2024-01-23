package world.rifted.libmmo.demo.components;

import com.google.auto.service.AutoService;
import com.mojang.serialization.Codec;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.NamespaceID;
import org.davidmoten.text.utils.WordWrap;
import org.jetbrains.annotations.NotNull;
import rifted.libmmo.item.ItemComponentHandler;

@SuppressWarnings("rawtypes")
@AutoService(ItemComponentHandler.class)
public final class DescriptionHandler implements ItemComponentHandler<Description> {

    @Override
    public @NotNull Class<Description> type() {
        return Description.class;
    }

    @Override
    public @NotNull Codec<Description> codec() {
        return Description.CODEC;
    }

    @Override
    public @NotNull NamespaceID id() {
        return NamespaceID.from("libmmo:description");
    }

    @Override
    public int priority() {
        return 1000;
    }

    @Override
    public void apply(@NotNull Description component, ItemStack.@NotNull Builder builder) {
        List<TextComponent> components = WordWrap.from(component.description()).maxWidth(50).wrapToList().stream()
                .map(line -> Component.text(line, NamedTextColor.DARK_GRAY))
                .toList();
        builder.lore(components);
    }

}
