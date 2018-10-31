package template.android.com.device.crypto.provider;

import android.os.Build;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyStore;

import javax.crypto.SecretKey;

import template.android.com.domain.crypto.CommonCryptoConstants;
import template.android.com.domain.crypto.provider.SymmetricKeyProvider;

public final class SymmetricKeyProviderV23Test {

    private SymmetricKeyProvider symmetricKeyProvider;

    @Before
    public void setUp() throws Exception {
        symmetricKeyProvider = new SymmetricKeyProviderV23Impl(KeyStore.getInstance(CommonCryptoConstants.ANDROID_KEY_STORE));
    }

    @Test
    public void name() {

        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);

        final String alias = "alias";

        final SecretKey secretKey = symmetricKeyProvider.getSymmetricAesCbcPkcs7PaddingKeyForAlias(alias);

        Assert.assertNotNull(secretKey);

        symmetricKeyProvider.deleteSymmetricKey(alias);
    }
}
