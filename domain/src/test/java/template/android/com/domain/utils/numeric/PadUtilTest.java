package template.android.com.domain.utils.numeric;

import org.junit.Assert;
import org.junit.Test;

public final class PadUtilTest {

    @Test
    public void testPaddingByteArray() throws Exception {
        Assert.assertArrayEquals(new byte[]{0, 0, 0, 0, (byte) 0xFF}, PadUtil.padWithZeroes(new byte[]{(byte) 0xFF}, 5));
    }

    @Test
    public void testPaddingByteArrayWhichAlreadyHasRequiredSiye() throws Exception {
        Assert.assertArrayEquals(new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF},
                                 PadUtil.padWithZeroes(new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}, 5));
    }

    @Test
    public void testPaddingIntArray() throws Exception {
        Assert.assertArrayEquals(new int[]{0, 0, 1, 2},
                                 PadUtil.padWithZeroes(new int[]{1, 2}, 4));
    }

    @Test
    public void testPaddingIntArrayWhichAlreadyHasRequiredSiye() throws Exception {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4},
                                 PadUtil.padWithZeroes(new int[]{1, 2, 3, 4}, 2));
    }
}
