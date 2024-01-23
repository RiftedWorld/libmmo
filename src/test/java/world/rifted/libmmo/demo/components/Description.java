package world.rifted.libmmo.demo.components;

import com.mojang.serialization.Codec;
import org.jetbrains.annotations.NotNull;
import rifted.libmmo.item.ItemComponent;

public record Description(
        @NotNull String description
) implements ItemComponent {

    public static final @NotNull Codec<Description> CODEC = Codec.STRING.xmap(Description::new, Description::description);

}
