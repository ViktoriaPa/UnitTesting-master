package junit5.shop;

import org.junit.Assert;
import org.junit.Test;
import shop.VirtualItem;

public class VirtualItemTest {
    private VirtualItem disk;

    @Test
    public void testSetSizeOnDisk() {
        disk = new VirtualItem();
        disk.setName("Linux");
        disk.setPrice(19.99);
        disk.setSizeOnDisk(1000);

        Assert.assertEquals(1000, disk.getSizeOnDisk(), 1);
    }
}