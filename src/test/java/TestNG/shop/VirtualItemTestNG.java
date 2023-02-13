package TestNG.shop;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import shop.VirtualItem;

public class VirtualItemTestNG {
    private VirtualItem disk;
    @Test
    public void testSetSizeOnDisk() {
        disk = new VirtualItem();
        disk.setName("Linux");
        disk.setPrice(19.99);
        disk.setSizeOnDisk(1000);

        assertEquals(1000, disk.getSizeOnDisk());
    }
}
