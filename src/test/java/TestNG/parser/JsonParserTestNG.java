package TestNG.parser;

import com.google.gson.JsonSyntaxException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.*;

public class JsonParserTestNG {
    private Cart testCart;
    private Cart parsedTestCart;

    @BeforeClass
    public void writeToFile() {
        testCart = new Cart("testCart");
        JsonParser parser = new JsonParser();
        parser.writeToFile(testCart);
    }

    @Ignore
    public void testFileNotNull() {
        JsonParser parser = new JsonParser();
        parsedTestCart = parser.readFromFile(new File("src/main/resources/testCart.json"));
        assertNotNull(parsedTestCart);
    }

    @Test (groups = {"TestJsonParserClass"})
    public void testReadCartNameFromFile() {
        JsonParser parser = new JsonParser();
        parsedTestCart = parser.readFromFile(new File("src/main/resources/testCart.json"));
        assertEquals("testCart", parsedTestCart.getCartName());
    }

    @Test (groups = {"TestExceptions"}, expectedExceptions = NoSuchFileException.class, expectedExceptionsMessageRegExp = "File src/main/resources/yui.json not found!")
    public void testFileNotFoundException(){
        JsonParser parser = new JsonParser();
        parser.readFromFile(new File("src/main/resources/yui"));
    }

    @Test (groups = {"TestExceptions"}, expectedExceptions = {NullPointerException.class})
    public void testNoRealItem() {
        JsonParser parser = new JsonParser();
        Cart cart = parser.readFromFile(new File("src/main/resources/noRealItem.json"));
        cart.showItems();
    }

    @Test (groups = {"TestExceptions"}, expectedExceptions = {NullPointerException.class})
    public void testNoVirtualItem() {
        JsonParser parser = new JsonParser();
        Cart cart = parser.readFromFile(new File("src/main/resources/noVirtualItem.json"));
        cart.showItems();
    }

    @Test (groups = {"TestExceptions"}, expectedExceptions = {JsonSyntaxException.class})
    public void errorInJSONSyntax() {
        JsonParser parser = new JsonParser();
        Cart cart = parser.readFromFile(new File("src/main/resources/syntaxError.json"));
        cart.showItems();
    }

    @Test (groups = {"TestExceptions"}, expectedExceptions = {NullPointerException.class})
    public void fileIsEmpty() {
        JsonParser parser = new JsonParser();
        Cart cart = parser.readFromFile(new File("src/main/resources/emptyFile.json"));
        assertNull(cart.getCartName());
    }

    @Test (groups = {"TestExceptions"}, expectedExceptions = {AssertionError.class})
    public void noCartName() {
        JsonParser parser = new JsonParser();
        Cart cart = parser.readFromFile(new File("src/main/resources/noCartName.json"));
        assertEquals(cart.getCartName(), "noCartName");
    }

    @AfterClass
    public void deleteTestFile() throws IOException {
        Files.delete(Paths.get("src/main/resources/" + testCart.getCartName() + ".json"));
    }
}
