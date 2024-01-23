package world.rifted.libmmo.demo.components;

import com.mojang.serialization.Codec;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import rifted.libmmo.item.ItemComponent;

public enum Rarity implements ItemComponent {

    COMMON(Component.text("Common", NamedTextColor.GRAY)),
    UNCOMMON(Component.text("Uncommon", NamedTextColor.GREEN)),
    RARE(Component.text("Rare", NamedTextColor.BLUE)),
    EPIC(Component.text("Epic", NamedTextColor.DARK_PURPLE)),
    LEGENDARY(Component.text("Legendary", NamedTextColor.GOLD)),
    MYTHIC(Component.text("Mythic", NamedTextColor.LIGHT_PURPLE));

    public static final @NotNull Codec<Rarity> CODEC = Codec.STRING.xmap(Rarity::fromString, Rarity::name);

    public static @NotNull Rarity fromString(@NotNull String string) {
        return valueOf(string.toUpperCase());
    }

    private final @NotNull Component component;

    Rarity(@NotNull Component component) {
        this.component = component.decorate(TextDecoration.BOLD);
    }

    public @NotNull Component component() {
        return component;
    }

}
