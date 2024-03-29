package world.rifted.libmmo.registry.exception;

import org.jetbrains.annotations.NotNull;
import world.rifted.libmmo.registry.Registry;

public final class MissingEntryException extends RuntimeException {

    private final @NotNull Registry<?, ?> registry;
    private final @NotNull String key;

    public MissingEntryException(@NotNull Registry<?, ?> registry, @NotNull String key) {
        super("Missing registry entry: " + key + " in " + registry);
        this.registry = registry;
        this.key = key;
    }

    public @NotNull Registry<?, ?> registry() {
        return registry;
    }

    public @NotNull String key() {
        return key;
    }

}
