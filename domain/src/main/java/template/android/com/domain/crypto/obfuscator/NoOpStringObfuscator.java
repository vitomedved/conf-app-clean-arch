package template.android.com.domain.crypto.obfuscator;

public final class NoOpStringObfuscator implements StringObfuscator {

    @Override
    public String obfuscateStringWithSHA1(final String value) {
        return value;
    }
}
