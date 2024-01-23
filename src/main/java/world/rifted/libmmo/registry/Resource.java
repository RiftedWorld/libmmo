package world.rifted.libmmo.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.Keyed;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Resource extends Keyed {

    @NotNull Path DATA_PATH = Path.of(System.getProperty("libmmo.data.dir", "data"));

    @NotNull NamespaceID id();

    @Override
    default @NotNull Key key() {
        return this.id();
    }

    default @NotNull String name() {
        return this.id().asString();
    }

    static @Nullable String load(@NotNull String file) {
        InputStream packaged = Resource.class.getClassLoader().getResourceAsStream("data/" + file);
        if (packaged != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(packaged, Charset.defaultCharset()))) {
                return reader.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                Logger logger = LoggerFactory.getLogger(Resource.class);
                logger.warn("Found resource {} in classpath, but failed to load it", file, e);
            }
        }

        Path external = DATA_PATH.resolve(file);
        if (Files.exists(external) && Files.isRegularFile(external)) {
            try (BufferedReader reader = Files.newBufferedReader(external)) {
                return reader.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                Logger logger = LoggerFactory.getLogger(Resource.class);
                logger.warn("Found resource {} in external data, but failed to load it", file, e);
            }
        }

        return null;
    }

    static @NotNull JsonArray loadJsonArray(@NotNull String resource) {
        String content = load(resource);
        if (content == null) return new JsonArray();
        return JsonParser.parseString(content).getAsJsonArray();
    }

}
