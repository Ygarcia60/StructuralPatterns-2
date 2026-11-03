package Flyweight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//The text document: a list of characters that share flyweight properties
public class Document {
    private final List<DocumentCharacter> characters = new ArrayList<>();

    /** Append one character with the given properties. */
    public void addCharacter(char c, String font, int size, String color) {
        characters.add(new DocumentCharacter(c,
                CharacterPropertiesFactory.getProperties(font, size, color)));
    }

    /** Append a whole string, all with the same properties. */
    public void addText(String text, String font, int size, String color) {
        for (char c : text.toCharArray()) addCharacter(c, font, size, color);
    }

    /** Edit: change the properties of the character at an index. */
    public void editCharacter(int index, String font, int size, String color) {
        DocumentCharacter old = characters.get(index);
        characters.set(index, new DocumentCharacter(old.getSymbol(),
                CharacterPropertiesFactory.getProperties(font, size, color)));
    }

    /** Edit: replace the character itself, keeping its properties. */
    public void replaceCharacter(int index, char c) {
        characters.set(index, new DocumentCharacter(c, characters.get(index).getProperties()));
    }

    public CharacterProperties getCharacterProps(int index) {
        return characters.get(index).getProperties();
    }

    public int length() { return characters.size(); }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (DocumentCharacter dc : characters) sb.append(dc.getSymbol());
        return sb.toString();
    }

    /** Save: one line per character -> "charCode,font,size,color" */
    public void save(String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (DocumentCharacter dc : characters) {
                CharacterProperties p = dc.getProperties();
                out.println((int) dc.getSymbol() + "," + p.getFont() + ","
                        + p.getSize() + "," + p.getColor());
            }
        }
    }

    /** Load: rebuild the document; the factory re-shares identical properties. */
    public static Document load(String filename) throws IOException {
        Document doc = new Document();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(",");
                doc.addCharacter((char) Integer.parseInt(parts[0]),
                        parts[1], Integer.parseInt(parts[2]), parts[3]);
            }
        }
        return doc;
    }

    public void print() {
        for (DocumentCharacter dc : characters) System.out.println(dc);
    }
}
