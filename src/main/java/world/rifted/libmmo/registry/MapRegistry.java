package world.rifted.libmmo.registry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

final class MapRegistry<K, R extends Resource> implements Registry<K, R> {

    private final @NotNull Map<K, R> map;

    public MapRegistry(@NotNull Map<K, R> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public @NotNull Optional<R> get(@NotNull K key) {
        return Optional.ofNullable(map.get(key));
    }

    @Override
    public @NotNull Collection<K> keys() {
        return this.map.keySet();
    }

    @Override
    public @NotNull Collection<R> values() {
        return this.map.values();
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public @NotNull <T> Registry<T, R> index(@NotNull Function<R, T> mapper) {
        Map<T, R> index = this.values().stream().collect(Collectors.toMap(mapper, i -> i));
        return new MapRegistry<>(index);
    }

}
