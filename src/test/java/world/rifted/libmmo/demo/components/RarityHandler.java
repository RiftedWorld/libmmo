package world.rifted.libmmo.demo.components;

import com.google.auto.service.AutoService;
import com.mojang.serialization.Codec;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;
import rifted.libmmo.item.ItemComponentHandler;

@SuppressWarnings("rawtypes")
@AutoService(ItemComponentHandler.class)
public final class RarityHandler implements ItemComponentHandler<Rarity> {

    @Override
    public @NotNull NamespaceID id() {
        return NamespaceID.from("libmmo:rarity");
    }

    @Override
    public @NotNull Class<Rarity> type() {
        return Rarity.class;
    }

    @Override
    public @NotNull Codec<@NotNull Rarity> codec() {
        return Rarity.CODEC;
    }

    @Override
    public int priority() {
        return -1000;
    }

    @Override
    public void apply(@NotNull Rarity component, @NotNull ItemStack.Builder builder) {
        builder.lore(Component.text(component.value()));
    }
}
