package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import parser.JsonParserTest;
import shop.CartTest;
import shop.RealItemTest;
import shop.VirtualItemTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        RealItemTest.class,
        VirtualItemTest.class,
        CartTest.class,
        JsonParserTest.class
})
public class Suite1 {
}
