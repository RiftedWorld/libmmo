package world.rifted.libmmo.registry;

import com.mojang.serialization.Codec;
import net.minestom.server.item.Material;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

public final class MinestomCodecs {

    public static final @NotNull Codec<NamespaceID> NAMESPACE_ID = Codec.STRING.xmap(NamespaceID::from, NamespaceID::asString);

    public static final @NotNull Codec<Material> MATERIAL = Codec.STRING.xmap(Material::fromNamespaceId, Material::name);

}
