package shop;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartTest {
    private Cart testCart;
    private RealItem car;
    private VirtualItem disk;

    @BeforeEach
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

        Assert.assertEquals(24.0, testCart.getTotalPrice(), 1);
    }

    @Test
    public void testDeleteVirtualItem() {
        testCart.deleteVirtualItem(disk);

        Assert.assertEquals(60000.0, testCart.getTotalPrice(), 1);
    }
}