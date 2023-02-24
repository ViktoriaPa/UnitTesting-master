package junit5.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

public class CartTest {
    private final static int ADMISSIBLE_ERROR_BETWEEN_ER_AND_AR = 1;
    private Cart testCart;
    private RealItem car;
    private VirtualItem disk;
    @Before
    public void createItems() {
        testCart = new Cart("testCart");

        car = new RealItem();
        car.setName("BMW");
        car.setPrice(50000.0);
        car.setWeight(1000.0);
        testCart.addRealItem(car);

        disk = new VirtualItem();
        disk.setName("Linux");
        disk.setPrice(20.00);
        disk.setSizeOnDisk(1000.0);
        testCart.addVirtualItem(disk);
    }

    @Test
    public void testDeleteRealItem() {
        testCart.deleteRealItem(car);

        Assert.assertEquals(24.0, testCart.getTotalPrice(), ADMISSIBLE_ERROR_BETWEEN_ER_AND_AR);
    }

    @Test
    public void testDeleteVirtualItem() {
        testCart.deleteVirtualItem(disk);

        Assert.assertEquals(60000, testCart.getTotalPrice(), ADMISSIBLE_ERROR_BETWEEN_ER_AND_AR);
    }
}