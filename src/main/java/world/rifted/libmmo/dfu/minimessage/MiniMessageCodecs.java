package world.rifted.libmmo.dfu.minimessage;

import com.mojang.serialization.Codec;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public final class MiniMessageCodecs {

    private static final @NotNull MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static final @NotNull Codec<Component> COMPONENT_CODEC = Codec.STRING.xmap(MINI_MESSAGE::deserialize, MINI_MESSAGE::serialize);

    private MiniMessageCodecs() {

    }

}
