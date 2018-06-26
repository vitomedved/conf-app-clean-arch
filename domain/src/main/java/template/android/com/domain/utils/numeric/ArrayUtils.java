package template.android.com.domain.utils.numeric;

import java.util.Arrays;
import java.util.Objects;

public final class ArrayUtils {

    private static final byte DEFAULT_BYTE_VALUE = 0;
    private static final char DEFAULT_CHAR_VALUE = '\u0000';
    private static final int DEFAULT_INT_VALUE = 0;
    private static final long DEFAULT_LONG_VALUE = 0L;

    /**
     * No instances.
     */
    private ArrayUtils() {
        throw new AssertionError();
    }

    /**
     * Clears arrays of bytes to default 0 byte value.
     * @param array array instance to clear.
     * @return array instance passed as method parameter.
     */
    public static byte[] clearArray(final byte[] array){
        Objects.requireNonNull(array, "array == null");

        Arrays.fill(array, DEFAULT_BYTE_VALUE);

        return array;
    }

    /**
     * Clears arrays of bytes to default \u0000 char value.
     * @param array array instance to clear.
     * @return array instance passed as method parameter.
     */
    public static char[] clearArray(final char[] array) {
        Objects.requireNonNull(array, "array == null");

        Arrays.fill(array, DEFAULT_CHAR_VALUE);

        return array;
    }

    /**
     * Clears arrays of ints to default 0 value.
     * @param array array instance to clear.
     * @return array instance passed as method parameter.
     */
    public static int[] clearArray(final int[] array) {
        Objects.requireNonNull(array, "array == null");

        Arrays.fill(array, DEFAULT_INT_VALUE);

        return array;
    }

    /**
     * Clears arrays of long to default 0L value.
     * @param array array instance to clear.
     * @return array instance passed as method parameter.
     */
    public static long[] clearArray(final long[] array) {
        Objects.requireNonNull(array, "array == null");

        Arrays.fill(array, DEFAULT_LONG_VALUE);

        return array;
    }

    public static char[] padArrayBeginning(final char[] array, final char paddingChar, final int requiredLength) {
        Objects.requireNonNull(array, "array == null");

        if (array.length > requiredLength) {
            throw new IllegalArgumentException("Specified length value is smaller than current array size. Array size is " + array.length
                                                       + ", and required length is " + requiredLength);

        } else if (array.length == requiredLength) {
            return array;

        } else {
            final char[] newArray = new char[requiredLength];

            for (int i = 0; i < requiredLength - array.length; i++) {
                newArray[i] = paddingChar;
            }

            System.arraycopy(array, 0, newArray, requiredLength - array.length, array.length);

            return newArray;
        }
    }

    public static char[] padArrayEnd(final char[] array, final char paddingChar, final int requiredLength) {
        Objects.requireNonNull(array, "array == null");

        if (array.length > requiredLength) {
            throw new IllegalArgumentException("Specified length value is smaller than current array size. Array size is " + array.length
                                                       + ", and required length is " + requiredLength);

        } else if (array.length == requiredLength) {
            return array;

        } else {
            final char[] newArray = new char[requiredLength];

            System.arraycopy(array, 0, newArray, 0, array.length);
            for (int i = array.length; i < requiredLength; i++) {
                newArray[i] = paddingChar;
            }

            return newArray;
        }
    }

    /**
     * Trims input array by removing elements from beginning of the array.
     * <p>
     *     For example, input array like [0, 1, 2, 3, 4, 5] would be trimmed to [2, 3, 4, 5] if required length is set to 4.
     * </p>
     * @param array byte array to trim.
     * @param requiredLength length of the array after trimming.
     * @return trimmed array or the passed instance if required length is equal to requiredLength.
     * @throws NullPointerException if array is null.
     * @throws IllegalArgumentException if required length is greater than array length.
     */
    public static byte[] trimArrayFromBeginning(final byte[] array, final int requiredLength) {
        Objects.requireNonNull(array, "array == null");

        if (requiredLength > array.length) {
            throw new IllegalArgumentException("Specified requiredLength was bigger than current array size. Array size was " + array.length
                                                       + ", and required length is " + requiredLength);

        } else if (array.length == requiredLength) {
            return array;

        } else {
            final byte[] newArray = new byte[requiredLength];
            System.arraycopy(array, array.length - requiredLength, newArray, 0, newArray.length);

            return newArray;
        }
    }
}
