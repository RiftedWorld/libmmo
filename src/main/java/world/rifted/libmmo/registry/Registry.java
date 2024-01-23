package world.rifted.libmmo.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public interface Registry<K, R extends Resource> {

    static <R extends Resource> @NotNull Registry<Key, R> manual(@NotNull Supplier<Collection<R>> supplier) {
        return new MapRegistry<>(supplier.get().stream().collect(Collectors.toMap(Resource::key, r -> r, (a, b) -> b, HashMap::new)));
    }

    static <R extends Resource> @NotNull Registry<Key, R> manual(@NotNull String name, @NotNull Function<JsonObject, R> mapper) {
        return new MapRegistry<>(
                StreamSupport.stream(Resource.loadJsonArray(name + ".json").spliterator(), false)
                    .filter(JsonElement::isJsonObject)
                    .map(JsonElement::getAsJsonObject)
                    .map(mapper)
                    .collect(Collectors.toMap(Resource::key, r -> r, (a, b) -> b, HashMap::new))
        );
    }

    @NotNull Optional<R> get(@NotNull K key);

    @NotNull Collection<K> keys();

    @NotNull Collection<R> values();

    int size();

    <T> @NotNull Registry<T, R> index(@NotNull Function<R, T> mapper);

}
