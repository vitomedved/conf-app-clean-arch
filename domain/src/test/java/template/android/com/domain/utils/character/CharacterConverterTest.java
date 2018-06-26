package template.android.com.domain.utils.character;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public final class CharacterConverterTest {

    @Test
    public void testConvertingByteToChar() throws Exception {
        Assert.assertEquals('t', CharacterConverter.toChar((byte) 116));
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingNullCharacterArrayThrowsNullPointerException() throws Exception {
        CharacterConverter.toBytes(null);
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingNullCharacterArrayWithSpecifiedCharsetThrowsNullPointerException() throws Exception {
        CharacterConverter.toBytes(null, StandardCharsets.US_ASCII);
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingCharacterArrayWithNullCharsetThrowsNullPointerException() throws Exception {
        CharacterConverter.toBytes(new char[]{}, null);
    }

    @Test
    public void testConvertingEmptyCharArrayToEmptyByteArray() throws Exception {
        final byte[] bytes = CharacterConverter.toBytes(new char[]{});

        Assert.assertNotNull(bytes);
        Assert.assertEquals(0, bytes.length);
    }

    @Test
    public void testConvertingCharArrayToByteArrayUsingDefaultCharset() throws Exception {
        final byte[] bytes = CharacterConverter.toBytes("test".toCharArray());

        Assert.assertNotNull(bytes);
        Assert.assertEquals(4, bytes.length);
        Assert.assertArrayEquals(new byte[]{116, 101, 115, 116}, bytes);
    }

    @Test
    public void testConvertingCharArrayToByteArrayUsingAsciiCharset() throws Exception {
        final byte[] bytes = CharacterConverter.toBytes("test".toCharArray(), StandardCharsets.US_ASCII);

        Assert.assertNotNull(bytes);
        Assert.assertEquals(4, bytes.length);
        Assert.assertArrayEquals(new byte[]{116, 101, 115, 116}, bytes);
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingNullCharArrayToRawByteArrayThrowsNullPointerException() throws Exception {
        CharacterConverter.toBytesRaw(null);
    }

    @Test
    public void testConvertingCharArrayToRawByteArray() throws Exception {
        Assert.assertArrayEquals(new byte[]{0, 116}, CharacterConverter.toBytesRaw("t".toCharArray()));
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingNullRawByteArrayToCharArrayThrowsNullPointerException() throws Exception {
        CharacterConverter.toCharsFromRawBytes(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertingOddByteArrayToCharsThrowsIllegalArgumentException() throws Exception {
        CharacterConverter.toCharsFromRawBytes(new byte[1]);
    }

    @Test
    public void testConvertingRawBytesToCharArray() throws Exception {
        Assert.assertArrayEquals("test".toCharArray(), CharacterConverter.toCharsFromRawBytes(new byte[]{0, 116, 0, 101, 0, 115, 0, 116}));
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingNullByteArrayThrowsNullPointerException() throws Exception {
        CharacterConverter.toChars(null);
    }

    @Test(expected = NullPointerException.class)
    public void testConvertingByteArrayWithNullCharsetThrowsNullPointerException() throws Exception {
        CharacterConverter.toChars(new byte[]{}, null);
    }

    @Test
    public void testConvertingEmptyByteArrayToEmptyCharacterArray() throws Exception {
        final char[] chars = CharacterConverter.toChars(new byte[]{});

        Assert.assertNotNull(chars);
        Assert.assertEquals(0, chars.length);
    }

    @Test
    public void testConvertingByteArrayToCharacterArray() throws Exception {
        final char[] chars = CharacterConverter.toChars(new byte[]{116, 101, 115, 116});

        Assert.assertNotNull(chars);
        Assert.assertEquals(4, chars.length);
        Assert.assertArrayEquals("test".toCharArray(), chars);
    }

    @Test
    public void testConvertingByteArrayToCharacterArrayUsingAsciiCharset() throws Exception {
        final char[] chars = CharacterConverter.toChars(new byte[]{116, 101, 115, 116}, StandardCharsets.US_ASCII);

        Assert.assertNotNull(chars);
        Assert.assertEquals(4, chars.length);
        Assert.assertArrayEquals("test".toCharArray(), chars);
    }

    @Test
    public void testConvertingLongToCharRepresentation() throws Exception {
        Assert.assertEquals(-0, 0);

        Assert.assertArrayEquals(new char[]{'0'}, CharacterConverter.toCharRepresentation(-0L));
        Assert.assertArrayEquals(new char[]{'0'}, CharacterConverter.toCharRepresentation(0L));

        Assert.assertArrayEquals(new char[]{'-', '1'}, CharacterConverter.toCharRepresentation(-1L));
        Assert.assertArrayEquals(new char[]{'1'}, CharacterConverter.toCharRepresentation(1L));

        Assert.assertArrayEquals(new char[]{'-', '1', '2', '3', '4'}, CharacterConverter.toCharRepresentation(-1234L));
        Assert.assertArrayEquals(new char[]{'1', '2', '3', '4'}, CharacterConverter.toCharRepresentation(1234L));

        Assert.assertArrayEquals(new char[]{'-', '1', '0', '0', '0', '0', '0', '0'}, CharacterConverter.toCharRepresentation(-1_000_000L));
        Assert.assertArrayEquals(new char[]{'1', '0', '0', '0', '0', '0', '0'}, CharacterConverter.toCharRepresentation(1_000_000L));

        // Integer MIN and MAX values.
        Assert.assertArrayEquals(new char[]{'-', '2', '1', '4', '7', '4', '8', '3', '6', '4', '8'}, CharacterConverter.toCharRepresentation(-2_147_483_648));
        Assert.assertArrayEquals(new char[]{'2', '1', '4', '7', '4', '8', '3', '6', '4', '7'}, CharacterConverter.toCharRepresentation(2_147_4836_47));

        Assert.assertArrayEquals(new char[]{'-', '1', '2', '3', '4', '5', '6', '7', '8', '9', '9', '9'}, CharacterConverter.toCharRepresentation(-12_345_678_999L));
        Assert.assertArrayEquals(new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '9', '9'}, CharacterConverter.toCharRepresentation(12_345_678_999L));

        // Long.MAX_VALUE
        Assert.assertArrayEquals(new char[]{'9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '7'},
                                 CharacterConverter.toCharRepresentation(9_223_372_036_854_775_807L));

        // Long.MIN_VALUE
        Assert.assertArrayEquals(new char[]{'-', '9', '2', '2', '3', '3', '7', '2', '0', '3', '6', '8', '5', '4', '7', '7', '5', '8', '0', '8'},
                                 CharacterConverter.toCharRepresentation(-9_223_372_036_854_775_808L));
    }
}
