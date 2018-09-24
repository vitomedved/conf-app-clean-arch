package template.android.com.device.crypto.provider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyStore;

import javax.crypto.SecretKey;

import template.android.com.domain.crypto.provider.SymmetricKeyProvider;

public final class SymmetricKeyProviderv23Test {

    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    private SymmetricKeyProvider symmetricKeyProvider;

    @Before
    public void setUp() throws Exception {
        symmetricKeyProvider = new SymmetricKeyProviderV23Impl(KeyStore.getInstance(ANDROID_KEY_STORE));
    }

    @Test
    public void name() {

        final String alias = "alias";

        final SecretKey secretKey = symmetricKeyProvider.getSymmetricAesCbcPkcs7PaddingKeyForAlias(alias);

        Assert.assertNotNull(secretKey);

        symmetricKeyProvider.deleteSymmetricKey(alias);
    }
}
