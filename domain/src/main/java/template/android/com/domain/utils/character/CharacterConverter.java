package template.android.com.domain.utils.character;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

import template.android.com.domain.utils.numeric.ArrayUtils;

public final class CharacterConverter {

    private static final char[] LONG_MIN_VALUE_AS_CHARS = String.valueOf(Long.MIN_VALUE).toCharArray();

    /**
     * No instances.
     */
    private CharacterConverter() {
        throw new AssertionError();
    }

    /**
     * Converts byte value to char value by simply casting to <i>char</i>.
     * @param value byte value to convert to <i>char</i>.
     * @return <i>char</i> value of byte value that is passed.
     */
    public static char toChar(final byte value) {
        return (char) value;
    }

    /**
     * Convenience method for {@link hr.asseco.android.tokenbasesdk.helpers.CharacterConverter#toBytes(char[], Charset)} with UTF-8 used as charset.
     */
    public static byte[] toBytes(final char[] chars) {
        return toBytes(chars, StandardCharsets.UTF_8);
    }

    /**
     * Converts char array to bytes using UTF-8 charset rules.
     *
     * @param chars   Array of characters to convert to bytes.
     * @param charset Charset to use to convert input char array to byte array.
     * @return byte representation of input character array
     * @throws NullPointerException if <i>chars</i> or <i>charset</i> is null.
     */
    public static byte[] toBytes(final char[] chars, final Charset charset) {
        Objects.requireNonNull(chars, "chars == null");
        Objects.requireNonNull(chars, "charset == null");

        final CharBuffer charBuffer = CharBuffer.wrap(chars);
        final ByteBuffer byteBuffer = charset.encode(charBuffer);

        final byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());

        ArrayUtils.clearArray(charBuffer.array());
        ArrayUtils.clearArray(byteBuffer.array());

        return bytes;
    }

    /**
     * Converts char array to byte array by splitting each character array into two bytes.
     * @param chars Array of characters to convert to bytes.
     * @return Byte array representation of input character array.
     * @throws NullPointerException if <i>chars</i> is null.
     */
    public static byte[] toBytesRaw(final char[] chars) {
        Objects.requireNonNull(chars, "chars == null");

        final byte[] bytes = new byte[chars.length << 1];
        for (int i = 0; i < chars.length; i++) {
            bytes[i << 1] = (byte) (chars[i] >>> 8);
            bytes[(i << 1) + 1] = (byte) (chars[i] & 0xFF);
        }

        return bytes;
    }

    /**
     * Converts byte array representing raw converted chars into char array, eg. combines two consecutive bytes into single character.
     * @param bytes byte array representing characters in raw format (UTF-16 without endianess bytes)
     * @return char array decoded fro bytes.
     * @throws NullPointerException if <i>bytes</i> is null.
     */
    public static char[] toCharsFromRawBytes(final byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes == null");
        if (bytes.length % 2 != 0) {
            throw new IllegalArgumentException("Odd number of bytes should be in array, but " + bytes.length + " found");
        }

        final char[] chars = new char[bytes.length >> 1];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (bytes[i << 1] << 8 | (bytes[(i << 1) + 1]));
        }

        return chars;
    }

    /**
     * Convenience method for {@link hr.asseco.android.tokenbasesdk.helpers.CharacterConverter#toChars(byte[], Charset)} with UTF-8 used as charset.
     */
    public static char[] toChars(final byte[] bytes) {
        return toChars(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Converts byte array to chars using specified charset.
     *
     * @param bytes   Array of bytes to convert to characters.
     * @param charset Charset to use to convert input byte array to char array.
     * @return Byte representation of input character array
     */
    public static char[] toChars(final byte[] bytes, final Charset charset) {
        Objects.requireNonNull(bytes, "Bytes array cannot be null.");
        Objects.requireNonNull(bytes, "Charset cannot be null.");

        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        final CharBuffer charBuffer = charset.decode(byteBuffer);

        final char[] chars = Arrays.copyOfRange(charBuffer.array(), charBuffer.position(), charBuffer.limit());

        ArrayUtils.clearArray(charBuffer.array());
        ArrayUtils.clearArray(byteBuffer.array());

        return chars;
    }

    /**
     * Converts long value to character array representation taking into account minus sign.
     * <p>
     * For example: <br/>
     * 1234L -> {'1', '2', '3', '4'} or -1234L -> {'-', '1', '2', '3', '4'}
     * </p>
     * @param value long value to convert to char array representation.
     * @return character array representing long value that has been passed.
     */
    public static char[] toCharRepresentation(final long value) {
        if (value == Long.MIN_VALUE) {
            return LONG_MIN_VALUE_AS_CHARS.clone();
        }

        final boolean isNegative = value < 0;

        final int arraySize = value == 0 ? 1 : (int)(Math.log10((double) Math.abs(value)) + 1) + (isNegative ? 1 : 0);
        final char[] valueAsChars = new char[arraySize];

        long valueTemp = Math.abs(value);
        for (int i = valueAsChars.length - 1; i >= 0; i--) {
            valueAsChars[i] = (char) ((valueTemp % 10) + '0');
            valueTemp /= 10;
        }

        if (isNegative) {
            valueAsChars[0] = '-';
        }

        return valueAsChars;
    }
}
