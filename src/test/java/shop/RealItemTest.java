package shop;

import org.junit.Assert;
import org.junit.Test;

public class RealItemTest {
    private RealItem car;

    @Test
    public void testSetWeight() {
        car = new RealItem();
        car.setName("BMW");
        car.setPrice(50000.0);
        car.setWeight(1000.0);

        Assert.assertEquals(1000.0, car.getWeight(), 1);
    }
}