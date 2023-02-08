package parser;

import jdk.nashorn.internal.ir.annotations.Ignore;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

    @After
    public void deleteTestFile() throws IOException {
        Files.delete(Paths.get("src/main/resources/" + testCart.getCartName() + ".json"));
    }
}