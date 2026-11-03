package Flyweight;

public class DocumentCharacter {
    private final char symbol;
    private final CharacterProperties properties;

    public DocumentCharacter(char symbol, CharacterProperties properties) {
        this.symbol = symbol;
        this.properties = properties;
    }

    public char getSymbol() {return symbol;}
    public CharacterProperties getProperties() { return properties; }

    @Override
    public String toString() { return symbol + " [" + properties + "]"; }
}
