package template.android.com.domain.utils.string;

import java.util.Objects;

public final class StringStrippingUtilsImpl implements StringStrippingUtils {

    private static final String EMPTY = "";

    private static final String NON_DIGIT_CHARACTERS_REGEX = "\\D";

    private final StringUtils stringUtils;

    public StringStrippingUtilsImpl(final StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    @Override
    public String removeNonDigitCharactersFromString(final String text) {

        Objects.requireNonNull(text, "Text must not be null!");

        if (stringUtils.isEmpty(text)) {
            return text;
        }

        return text.replaceAll(NON_DIGIT_CHARACTERS_REGEX, EMPTY);
    }
}
