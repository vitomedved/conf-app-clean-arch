package template.android.com.domain.crypto.engine;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import template.android.com.domain.utils.numeric.ArrayUtils;

public final class AesCryptoEngine implements CryptoEngine {

    private static final int IV_LENGTH = 16;

    private static final String ALGORITHM_TRANSFORMATION = "AES/CBC/PKCS7PADDING";

    private final Object encryptLock = new Object();
    private final Object decryptLock = new Object();

    private final Cipher encryptCipher;
    private final Cipher decryptCipher;

    private final SecretKey secretKey;

    public AesCryptoEngine(final SecretKey secretKey) {

        this.secretKey = secretKey;

        try {
            encryptCipher = Cipher.getInstance(ALGORITHM_TRANSFORMATION);
            decryptCipher = Cipher.getInstance(ALGORITHM_TRANSFORMATION);

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] encrypt(final byte[] bytes) {

        try {
            final byte[] cryptogram;
            synchronized (encryptLock) {
                encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
                cryptogram = encryptCipher.doFinal(bytes);
            }

            final byte[] resultCryptogram = ArrayUtils.concatArrays(encryptCipher.getIV(), cryptogram);

            ArrayUtils.clearArray(cryptogram);

            return resultCryptogram;

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(final byte[] bytes) {

        final byte[] iv = new byte[IV_LENGTH];
        System.arraycopy(bytes, 0, iv, 0, iv.length);

        try {
            synchronized (decryptLock) {
                decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

                return decryptCipher.doFinal(bytes, IV_LENGTH, bytes.length - IV_LENGTH);
            }

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
