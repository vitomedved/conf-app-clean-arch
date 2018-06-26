package template.android.com.domain.utils.crypto;

import template.android.com.domain.model.Pair;

public final class CryptoUtils {

    private CryptoUtils() {
        throw new AssertionError();
    }

    public static Pair<byte[], byte[]> getIvFromPayload(final byte[] payload) {
        final byte[] iv = new byte[16];
        final byte[] cryptogram = new byte[payload.length - iv.length];

        System.arraycopy(payload, 0, iv, 0, iv.length);
        System.arraycopy(payload, iv.length, cryptogram, 0, cryptogram.length);

        return new Pair<>(iv, cryptogram);
    }

    public static byte[] concatIVToPayload(final byte[] iv, final byte[] cryptogram) {
        final byte[] payload = new byte[iv.length + cryptogram.length];

        System.arraycopy(iv, 0, payload, 0, iv.length);
        System.arraycopy(cryptogram, 0, payload, iv.length, cryptogram.length);

        return payload;
    }
}
