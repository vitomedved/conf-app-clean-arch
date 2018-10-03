package template.android.com.domain.crypto.engine;

import template.android.com.domain.crypto.provider.SymmetricKeyProvider;

public final class CryptoEngineFactoryImpl implements CryptoEngineFactory {

    private final SymmetricKeyProvider symmetricKeyProvider;

    public CryptoEngineFactoryImpl(final SymmetricKeyProvider symmetricKeyProvider) {
        this.symmetricKeyProvider = symmetricKeyProvider;
    }

    @Override
    public CryptoEngine createCryptoEngineForKeyAlias(final String alias) {
        return new AesCryptoEngine(symmetricKeyProvider.getSymmetricAesCbcPkcs7PaddingKeyForAlias(alias));
    }
}
