package junit5.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit5.parser.JsonParserTest;
import junit5.shop.CartTest;
import junit5.shop.RealItemTest;
import junit5.shop.VirtualItemTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        RealItemTest.class,
        VirtualItemTest.class,
        CartTest.class,
        JsonParserTest.class
})
public class UnitTests {
}
