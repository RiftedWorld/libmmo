package rifted.libmmo.item;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Map;
import java.util.function.Function;
import net.kyori.adventure.key.Key;
import net.minestom.server.item.Material;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;
import world.rifted.libmmo.dfu.DFU;
import world.rifted.libmmo.registry.MinestomCodecs;
import world.rifted.libmmo.registry.Registry;

public final class ItemRegistry {

    public record Entry(
            @NotNull NamespaceID id,
            @NotNull Material material,
            @NotNull Map<NamespaceID, ItemComponent> components)
    {
        public static final @NotNull Codec<Entry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                MinestomCodecs.NAMESPACE_ID.fieldOf("id").forGetter(Entry::id),
                MinestomCodecs.MATERIAL.fieldOf("material").forGetter(Entry::material),
                Codec.pair(MinestomCodecs.NAMESPACE_ID.fieldOf("type").codec(), ItemComponent.CODEC)
                        .listOf()
                        .xmap(DFU::pairListToMap, DFU::mapToPairList)
                        .optionalFieldOf("components", Map.of()).forGetter(Entry::components)
        ).apply(instance, Entry::new));
    }

    static final @NotNull Registry<Key, Item> REGISTRY = Registry.manual("items", json -> {
        Function<JsonElement, DataResult<Pair<Entry, JsonElement>>> decoder = JsonOps.INSTANCE.withDecoder(Entry.CODEC);
        Entry entry = decoder.apply(json).getOrThrow(false, System.err::println).getFirst();
        return new ItemImpl(entry);
    });

}
