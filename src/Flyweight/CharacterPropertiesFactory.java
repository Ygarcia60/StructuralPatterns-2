package Flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


//Flyweight factory: guarantes that only one CharacterProperties object
// exists for each unique (font, size, color) combination

public class CharacterPropertiesFactory {
    private static final Map<String, CharacterProperties> pool = new HashMap<>();

    private CharacterPropertiesFactory() {}

    public static CharacterProperties getProperties(String font, int size, String color) {
        String key = font + "|" + size + "|" + color;
        CharacterProperties props = pool.get(key);
        if (props == null) {
            props = new CharacterProperties(font, size, color);
            pool.put(key, props);
        }
        return props;
    }

    public static int getPoolSize() {return pool.size();}
}
