package template.android.com.domain.utils.byteformat;

import java.util.Objects;

public final class HEX {

    private static final char[] HEX = "0123456789ABCDEF".toCharArray();

    private HEX() {
        throw new AssertionError();
    }

    /**
     * Converts byte array to HEX format.
     * @param input byte array to convert.
     * @return HEX representation of an array.
     */
    public static String toHEX(final byte[] input) {

        Objects.requireNonNull(input, "input == null");

        final char[] output = new char[2 * input.length];
        for (int i = 0; i < input.length; ++i) {
            output[2 * i] = HEX[(input[i] & 0xF0) >>> 4];
            output[2 * i + 1] = HEX[input[i] & 0x0F];
        }

        return new String(output);
    }

    /**
     * Converts array of byte arrays to HEX format.
     * @param arrays an array of byte arrays to HEX format.
     * @return HEX representation of arrays.
     */
    public static String toHEX(final byte[]... arrays) {

        Objects.requireNonNull(arrays, "arrays == null");

        final StringBuilder sb = new StringBuilder();
        for (final byte[] bs : arrays) {
            sb.append(toHEX(bs));
        }

        return sb.toString();
    }

    /**
     * Converts single byte into two HEX characters.
     * @param input byte input.
     * @return HEX representation of a single byte.
     */
    public static String toHEX(final byte input) {

        final char[] output = new char[2];

        output[0] = HEX[(input & 0xF0) >>> 4];
        output[1] = HEX[input & 0x0F];

        return new String(output);
    }

    /**
     * Converts HEX input into byte array.
     * @param hexInput input to convert.
     * @return byte array representation of HEX string input.
     */
    public static byte[] fromHEX(final String hexInput) {

        Objects.requireNonNull(hexInput, "hexInput == null");

        final int length = hexInput.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("hexInput must have even amount of characters.");
        }

        final byte[] output = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            output[i / 2] = (byte) ((Character.digit(hexInput.charAt(i), 16) << 4) + Character.digit(hexInput.charAt(i + 1), 16));
        }

        return output;
    }
}
