package world.rifted.libmmo.demo.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import rifted.libmmo.item.ItemComponent;

public record Rarity(
        String value
) implements ItemComponent {

    public static final Codec<Rarity> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("value").forGetter(Rarity::value)
    ).apply(i, Rarity::new));

}