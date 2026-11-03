package Flyweight;

// Flyweight: holds the Intrinsic (shared) state of a character;
// font, size, and color

import java.util.Objects;

public final class CharacterProperties {
    private final String font;
    private final int size;
    private final String color;

    //constructor
    CharacterProperties(String font, int size, String color) {
        this.font = font;
        this.size = size;
        this.color = color;
    }

    public String getFont() { return font; }
    public int getSize() { return size; }
    public String getColor() { return color; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterProperties)) return false;
        CharacterProperties p = (CharacterProperties) o;
        return size == p.size && font.equals(p.font) && color.equals(p.color);
    }

    @Override
    public int hashCode() { return Objects.hash(font, size, color); }

    @Override
    public String toString() { return font + "/" + size + "/" + color; }
}
