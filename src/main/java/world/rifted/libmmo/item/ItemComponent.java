package rifted.libmmo.item;

import com.mojang.serialization.Codec;
import org.jetbrains.annotations.NotNull;

public interface ItemComponent {

    @NotNull Codec<ItemComponent> CODEC = ItemComponentHandler.CODEC.dispatch(
            "type",
            component -> ItemComponentHandler.from(component).orElseThrow(() -> new IllegalStateException("Missing item component handler for " + component.getClass())),
            ItemComponentHandler::codec
    );

}
