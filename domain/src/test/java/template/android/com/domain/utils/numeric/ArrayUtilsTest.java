package template.android.com.domain.utils.numeric;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lbabic on 20/02/2018.
 */
public final class ArrayUtilsTest {

    @Test
    public void testClearingByteArray() throws Exception {
        final byte[] array = new byte[]{0xF};

        Assert.assertArrayEquals(new byte[array.length], ArrayUtils.clearArray(array));
    }

    @Test
    public void testClearingCharArray() throws Exception {
        final char[] array = new char[]{'c'};

        Assert.assertArrayEquals(new char[array.length], ArrayUtils.clearArray(array));
    }

    @Test
    public void testClearingIntArray() throws Exception {
        final int[] array = new int[]{100};

        Assert.assertArrayEquals(new int[array.length], ArrayUtils.clearArray(array));
    }

    @Test
    public void testClearingLongArray() throws Exception {
        final long[] array = new long[]{100L};

        Assert.assertArrayEquals(new long[array.length], ArrayUtils.clearArray(array));
    }

    @Test(expected = NullPointerException.class)
    public void testPadArrayBeginningThrowsNullPointerExceptionForNullArray() throws Exception {
        ArrayUtils.padArrayBeginning(null, '0', 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPadArrayBeginningThrowsIllegalArgumentExceptionWhenRequiredLengthIsSmallerThanArrayLength() throws Exception {
        ArrayUtils.padArrayBeginning(new char[10], '0', 5);
    }

    @Test
    public void testPadArrayBeginningWhenArrayIsSmallerTharRequiredLength() throws Exception {
        Assert.assertArrayEquals("ab".toCharArray(), ArrayUtils.padArrayBeginning("b".toCharArray(), 'a', 2));
        Assert.assertArrayEquals("aaab".toCharArray(), ArrayUtils.padArrayBeginning("b".toCharArray(), 'a', 4));
        Assert.assertArrayEquals("aaaaaaaaaaab".toCharArray(), ArrayUtils.padArrayBeginning("b".toCharArray(), 'a', 12));
    }

    @Test
    public void testPadArrayBeginningReturnsSameInstanceWhenRequiredLengthEqualsArrayLength() throws Exception {
        final char[] array = "test".toCharArray();

        Assert.assertSame(array, ArrayUtils.padArrayBeginning(array, '0', array.length));
    }

    @Test(expected = NullPointerException.class)
    public void testPadArrayEndThrowsNullPointerExceptionForNullArray() throws Exception {
        ArrayUtils.padArrayEnd(null, '0', 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPadArrayEndThrowsIllegalArgumentExceptionWhenRequiredLengthIsSmallerThanArrayLength() throws Exception {
        ArrayUtils.padArrayEnd(new char[10], '0', 5);
    }

    @Test
    public void testPadArrayEndWhenArrayIsSmallerTharRequiredLength() throws Exception {
        Assert.assertArrayEquals("ba".toCharArray(), ArrayUtils.padArrayEnd("b".toCharArray(), 'a', 2));
        Assert.assertArrayEquals("baaa".toCharArray(), ArrayUtils.padArrayEnd("b".toCharArray(), 'a', 4));
        Assert.assertArrayEquals("baaaaaaaaaaa".toCharArray(), ArrayUtils.padArrayEnd("b".toCharArray(), 'a', 12));
    }

    @Test
    public void testPadArrayEndReturnsSameInstanceWhenRequiredLengthEqualsArrayLength() throws Exception {
        final char[] array = "test".toCharArray();

        Assert.assertSame(array, ArrayUtils.padArrayBeginning(array, '0', array.length));
    }

    @Test(expected = NullPointerException.class)
    public void testTrimmingArrayFromBeginningReturningNullPointerExceptionForNullArray() throws Exception {
        ArrayUtils.trimArrayFromBeginning(null, 0);
    }

    @Test
    public void testTrimmingArrayFromBeginningReturnsSameInstanceWhenRequiredLengthIsSameAsArraySize() throws Exception {
        final byte[] array = "text".getBytes();

        Assert.assertSame(array, ArrayUtils.trimArrayFromBeginning(array, array.length));
    }

    @Test
    public void testTrimmingArrayFromBeginningWhenRequiredLengthIsLessThanArraySize() throws Exception {
        final byte[] array = new byte[]{0x0, 0x1, 0x2, 0x3, 0x4, 0x5};

        Assert.assertArrayEquals(new byte[]{0x3, 0x4, 0x5}, ArrayUtils.trimArrayFromBeginning(array, 3));
    }
}
