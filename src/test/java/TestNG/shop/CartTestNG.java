package TestNG.shop;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.testng.Assert.assertEquals;

public class CartTestNG {
    private Cart testCart;
    private RealItem car;
    private VirtualItem disk;
    @BeforeClass
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

    @Test (groups = {"TestCartClass"})
    public void testDeleteRealItem() {
        testCart.deleteRealItem(car);

        assertEquals(24.0, testCart.getTotalPrice());
    }

    @Test (groups = {"TestCartClass"})
    public void testDeleteVirtualItem() {
        testCart.deleteVirtualItem(disk);

        assertEquals(0.0, testCart.getTotalPrice());
    }
}
