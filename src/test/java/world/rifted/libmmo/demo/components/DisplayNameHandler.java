package world.rifted.libmmo.demo.components;

import com.google.auto.service.AutoService;
import com.mojang.serialization.Codec;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;
import rifted.libmmo.item.ItemComponentHandler;

@SuppressWarnings("rawtypes")
@AutoService(ItemComponentHandler.class)
public final class DisplayNameHandler implements ItemComponentHandler<DisplayName> {

    @Override
    public @NotNull Class<DisplayName> type() {
        return DisplayName.class;
    }

    @Override
    public @NotNull Codec<DisplayName> codec() {
        return DisplayName.CODEC;
    }

    @Override
    public @NotNull NamespaceID id() {
        return NamespaceID.from("libmmo:display_name");
    }

    @Override
    public void apply(@NotNull DisplayName component, ItemStack.@NotNull Builder builder) {
        builder.displayName(component.displayName().decoration(TextDecoration.ITALIC, false));
    }

}
