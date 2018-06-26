package template.android.com.domain.utils.crypto;

import org.junit.Assert;
import org.junit.Test;

import template.android.com.domain.model.Pair;
import template.android.com.domain.utils.byteformat.HEX;

public final class CryptoUtilsTest {

    @Test
    public void testExtractingIVFromPayload() throws Exception {
        final byte[] payload = HEX.fromHEX("0101010101010101010101010101010111111111111111111111111111111111");

        final Pair<byte[], byte[]> pair = CryptoUtils.getIvFromPayload(payload);

        Assert.assertArrayEquals(pair.getFirst(), HEX.fromHEX("01010101010101010101010101010101"));
        Assert.assertArrayEquals(pair.getSecond(), HEX.fromHEX("11111111111111111111111111111111"));
    }

    @Test
    public void textAppendingIVToPayload() throws Exception {
        final byte[] iv = HEX.fromHEX("01010101010101010101010101010101");
        final byte[] cryptogram = HEX.fromHEX("11111111111111111111111111111111");

        Assert.assertArrayEquals(CryptoUtils.concatIVToPayload(iv, cryptogram), HEX.fromHEX("0101010101010101010101010101010111111111111111111111111111111111"));
    }
}
