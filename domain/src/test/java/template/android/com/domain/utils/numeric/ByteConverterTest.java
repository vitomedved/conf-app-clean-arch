package template.android.com.domain.utils.numeric;

import org.junit.Assert;
import org.junit.Test;

import template.android.com.domain.utils.numeric.ByteConverter;

/**
 * Created by lbabic on 18/02/2018.
 */

public final class ByteConverterTest {

    @Test
    public void testConvertingIntToByte() throws Exception {
        Assert.assertArrayEquals(new byte[]{(byte) 0xF0, 0x0F, (byte) 0xFF, 0}, ByteConverter.toBytes(0xF0_0F_FF_00));
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingNullByteArrayToIntThrowsNullPointerException() throws Exception {
        ByteConverter.toInt(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertingByteArrayNotLengthOfLengthThrowsIllegalArgumentException() throws Exception {
        ByteConverter.toInt(new byte[8]);
    }

    @Test
    public void testConvertingByteArrayToInt() throws Exception {
        Assert.assertEquals(0x00_00_00_0F, ByteConverter.toInt(new byte[]{0, 0, 0, (byte) 0x0F}));
        Assert.assertEquals(0xF0_0F_F0_0F, ByteConverter.toInt(new byte[]{(byte) 0xF0, (byte) 0x0F, (byte) 0xF0, (byte) 0x0F}));
        Assert.assertEquals(0x88_77_22_11, ByteConverter.toInt(new byte[]{(byte) 0x88, (byte) 0x77, (byte) 0x22, (byte) 0x11}));
        Assert.assertEquals(0xFF_FF_FF_FF, ByteConverter.toInt(new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}));
    }

    @Test
    public void testConvertingLongToBytes() throws Exception {
        Assert.assertArrayEquals(new byte[]{(byte) 0xFF, (byte) 0xF1, (byte) 0xF1, (byte) 0xFF, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01},
                                 ByteConverter.toBytes(0xFF_F1_F1_FF_00_00_01_01L));

        Assert.assertArrayEquals(new byte[]{(byte) 0x00, (byte) 0x11, (byte) 0x22, (byte) 0xF2, (byte) 0x15, (byte) 0x51, (byte) 0x42, (byte) 0x07},
                                 ByteConverter.toBytes(0x00_11_22_F2_15_51_42_07L));

        Assert.assertArrayEquals(new byte[]{(byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0, (byte) 0xF0},
                                 ByteConverter.toBytes(0xF0_F0_F0_F0_F0_F0_F0_F0L));
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingNullByteArrayToLongThrowsNullPointerException() throws Exception {
        ByteConverter.toLong(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertingByteArrayOfWrongLengthToLongThrowsIllegalArgumentException() throws Exception {
        ByteConverter.toLong(new byte[10]);
    }

    @Test
    public void testConvertingByteArrayToLong() throws Exception {
        Assert.assertEquals(0x7F_FF_0F_F0_0F_F0_0F_F0L,
                            ByteConverter.toLong(new byte[]{(byte) 0x7F, (byte) 0xFF, (byte) 0x0F, (byte) 0xF0,
                                                            (byte) 0x0F, (byte) 0xF0, (byte) 0x0F, (byte) 0xF0}));

        Assert.assertEquals(0x7F_00_00_FF_98_31_13_00L,
                            ByteConverter.toLong(new byte[]{(byte) 0x7F, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
                                                            (byte) 0x98, (byte) 0x31, (byte) 0x13, (byte) 0x00}));
    }
}
