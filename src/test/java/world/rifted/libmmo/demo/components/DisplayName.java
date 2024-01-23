package world.rifted.libmmo.demo.components;

import com.mojang.serialization.Codec;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import rifted.libmmo.item.ItemComponent;
import world.rifted.libmmo.dfu.minimessage.MiniMessageCodecs;

public record DisplayName(
        @NotNull Component displayName
) implements ItemComponent {

    public static final @NotNull Codec<DisplayName> CODEC = MiniMessageCodecs.COMPONENT_CODEC.xmap(DisplayName::new, DisplayName::displayName);

}
