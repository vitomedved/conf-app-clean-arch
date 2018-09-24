package template.android.com.domain.crypto.provider;

public final class SymmetricKeyProviderException extends RuntimeException {

    public SymmetricKeyProviderException() {

    }

    public SymmetricKeyProviderException(final String s) {
        super(s);
    }

    public SymmetricKeyProviderException(final String s, final Throwable throwable) {
        super(s, throwable);
    }

    public SymmetricKeyProviderException(final Throwable throwable) {
        super(throwable);
    }

    public SymmetricKeyProviderException(final String s, final Throwable throwable, final boolean b, final boolean b1) {
        super(s, throwable, b, b1);
    }
}
