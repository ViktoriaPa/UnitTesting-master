package TestNG.shop;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import shop.RealItem;

public class RealItemTestNG {
    private RealItem car;

    @Test
    public void testSetWeight() {
        car = new RealItem();
        car.setName("BMW");
        car.setPrice(50000.0);
        car.setWeight(1000.0);

        assertEquals(1000.0, car.getWeight());
    }
}
