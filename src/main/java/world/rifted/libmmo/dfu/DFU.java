package world.rifted.libmmo.dfu;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class DFU {

    private DFU() {
    }

    public static <T, S> @NotNull Map<T, S> pairListToMap(@NotNull List<Pair<T, S>> pairList) {
        return pairList.stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    public static <T, S> @NotNull List<Pair<T, S>> mapToPairList(@NotNull Map<T, S> map) {
        return map.entrySet().stream().map(entry -> new Pair<>(entry.getKey(), entry.getValue())).toList();
    }

}
