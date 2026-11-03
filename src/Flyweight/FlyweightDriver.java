package Flyweight;

import java.io.IOException;

public class FlyweightDriver {
    public static void main(String[] args) throws IOException {
        Document doc = new Document();

        // "HelloWorldCS3560" with 5 different property combinations
        doc.addText("Hello", "Arial", 12, "Red");
        doc.addText("World", "Calibri", 14, "Blue");
        doc.addText("CS", "Verdana", 16, "Black");
        doc.addText("35", "Arial", 12, "Blue");
        doc.addText("60", "Calibri", 16, "Red");

        System.out.println("Document text: " + doc.getText());
        System.out.println("Characters in document: " + doc.length());
        System.out.println("Flyweight objects created: " + CharacterPropertiesFactory.getPoolSize());

        // save to disk, then load it back
        doc.save("document.txt");
        Document loaded  = Document.load("document.txt");

        System.out.println("\nLoaded text: " + loaded.getText());
        loaded.print();

        System.out.println("\nFlyweight objects after load: " + CharacterPropertiesFactory.getPoolSize());

        System.out.println("Same object for the two 1's " + (doc.getCharacterProps(2) == doc.getCharacterProps(3)));


    }

}
