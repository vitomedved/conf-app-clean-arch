package template.android.com.domain.utils.numeric;

import java.util.Objects;

public final class ByteConverter {

    private static final int SHORT_SIZE_IN_BYTES = Short.SIZE >> 3;
    private static final int INT_SIZE_IN_BYTES = Integer.SIZE >> 3;
    private static final int LONG_SIZE_IN_BYTES = Long.SIZE >> 3;

    /**
     * No outer instances
     */
    private ByteConverter() {
        throw new AssertionError();
    }

    /**
     * Converts int value to byte array of length 4.
     * @param value - int value to convert to byte representation.
     */
    public static byte[] toBytes(final int value) {

        final byte[] bytes = new byte[INT_SIZE_IN_BYTES];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte) ((value >> ((3 - i) * 8)) & 0xFF);
        }

        return bytes;
    }

    /**
     * Converts byte array to int value.
     * @param bytes - array of bytes to convert to int. Must be at most 4 bytes in length.
     * @throws NullPointerException if array of bytes is null
     * @throws IllegalArgumentException if array is longer than 4 bytes.
     */
    public static int toInt(final byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes == null");
        if (bytes.length > INT_SIZE_IN_BYTES) {
            throw new IllegalArgumentException("Byte array must have 4 items, but was " + bytes.length);
        }

        final int length = bytes.length < INT_SIZE_IN_BYTES ? bytes.length : INT_SIZE_IN_BYTES;

        int value = 0;
        for (int i = 0; i < length; i++) {
            value |= (bytes[i] & 0xFF) << ((length - 1 - i) * 8);
        }

        return value;
    }

    /**
     * Converts long value to byte array of length 8.
     * @param value - long value to convert to byte representation.
     */
    public static byte[] toBytes(final long value) {

        final byte[] bytes = new byte[LONG_SIZE_IN_BYTES];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte) ((value >> ((7 - i) * 8)) & 0xFF);
        }

        return bytes;
    }

    /**
     * Converts byte array to int value.
     * @param bytes - array of bytes to convert to long. Must be at most 8 bytes in length.
     * @throws NullPointerException if array is null
     * @throws IllegalArgumentException if array is longer than 8 bytes.
     */
    public static long toLong(final byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes == null");
        if (bytes.length > LONG_SIZE_IN_BYTES) {
            throw new IllegalArgumentException("Byte array must have 8 items, but was " + bytes.length);
        }

        final int length = bytes.length < LONG_SIZE_IN_BYTES ? bytes.length : LONG_SIZE_IN_BYTES;

        long value = 0;
        for (int i = 0; i < length; i++) {
            value |= (long) (bytes[i] & 0xFF) << ((length - 1 - i) * 8);
        }

        return value;
    }
}
