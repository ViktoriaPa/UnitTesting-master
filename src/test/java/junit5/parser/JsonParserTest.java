package junit5.parser;

import com.google.gson.JsonSyntaxException;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class JsonParserTest {
    private Cart testCart;
    private Cart parsedTestCart;

    @Before
    public void writeToFile() {
        testCart = new Cart("testCart");
        JsonParser parser = new JsonParser();
        parser.writeToFile(testCart);
    }

    @Ignore
    public void testFileNotNull() {
        JsonParser parser = new JsonParser();
        parsedTestCart = parser.readFromFile(new File("src/main/resources/testCart.json"));
        Assert.assertNotNull(parsedTestCart);
    }

    @Test
    public void testReadCartNameFromFile() {
        JsonParser parser = new JsonParser();
        parsedTestCart = parser.readFromFile(new File("src/main/resources/testCart.json"));
        Assert.assertEquals("testCart", parsedTestCart.getCartName());
    }

    @Test
    public void testFileNotFoundException() {
        NoSuchFileException exception = Assertions.assertThrows(NoSuchFileException.class, () -> {
            JsonParser parser = new JsonParser();
            parser.readFromFile(new File("src/main/resources/yui"));
        });
        Assert.assertEquals("File src/main/resources/yui.json not found!", exception.getMessage());
    }
    @Test
    public void testOtherExceptions() {
        //testNoRealItem
        Assertions.assertThrows(NullPointerException.class, () -> {
            JsonParser parser = new JsonParser();
            Cart cart = parser.readFromFile(new File("src/main/resources/noRealItem.json"));
            cart.showItems();
        });

        //testNoVirtualItem
        Assertions.assertThrows(NullPointerException.class, () -> {
            JsonParser parser = new JsonParser();
            Cart cart = parser.readFromFile(new File("src/main/resources/noVirtualItem.json"));
            cart.showItems();
        });

        //errorInJSONSyntax
        Assertions.assertThrows(JsonSyntaxException.class, () -> {
            JsonParser parser = new JsonParser();
            Cart cart = parser.readFromFile(new File("src/main/resources/syntaxError.json"));
            cart.showItems();
        });

        //fileIsEmpty
        Assertions.assertThrows(NullPointerException.class, () -> {
            JsonParser parser = new JsonParser();
            Cart cart = parser.readFromFile(new File("src/main/resources/emptyFile.json"));
            Assert.assertNull(cart.getCartName());
        });

        //noCartName
        Assertions.assertThrows(ComparisonFailure.class, () -> {
            JsonParser parser = new JsonParser();
            Cart cart = parser.readFromFile(new File("src/main/resources/noCartName.json"));
            Assert.assertEquals(cart.getCartName(), "noCartName");
        });
    }

    @After
    public void deleteTestFile() throws IOException {
        Files.delete(Paths.get("src/main/resources/" + testCart.getCartName() + ".json"));
    }
}