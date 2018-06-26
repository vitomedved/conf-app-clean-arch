package template.android.com.domain.utils.numeric;

import java.util.Objects;

public final class PadUtil {

    private PadUtil() {
        throw new AssertionError();
    }

    /**
     * Pads passed byte array with leading zeroes if required length is greater than array length. If the array is longer than required length,
     * parameter instance is returned without any modification.
     * @param in byte array to pad.
     * @param requiredLength required length of padded array.
     * @return padded array.
     * @throws NullPointerException if <i>in</i> array is null.
     */
    public static byte[] padWithZeroes(final byte[] in, final int requiredLength) {
        Objects.requireNonNull(in, "in == null");

        if (in.length < requiredLength) {
            byte[] result = new byte[requiredLength];
            System.arraycopy(in, 0, result, requiredLength - in.length, in.length);
            return result;

        } else {
            return in;
        }
    }

    /**
     * Pads passed int array with leading zeroes if required length is greater than array length. If the array is longer than required length,
     * parameter instance is returned without any modification.
     * @param in int array to pad.
     * @param requiredLength required length of padded array.
     * @return padded array.
     * @throws NullPointerException if <i>in</i> array is null.
     */
    public static int[] padWithZeroes(final int[] in, final int requiredLength) {
        Objects.requireNonNull(in, "in == null");

        if (in.length < requiredLength) {
            int[] result = new int[requiredLength];
            System.arraycopy(in, 0, result, requiredLength - in.length, in.length);

            return result;

        } else {
            return in;
        }
    }
}
