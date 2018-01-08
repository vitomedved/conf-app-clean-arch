package template.android.com.domain.utils;

import com.annimon.stream.Stream;

import java.util.Collections;
import java.util.List;

public final class StringUtilsImpl implements StringUtils {

    private static final String EMPTY = "";

    @Override
    public boolean isEmpty(final String text) {
        return text == null || text.isEmpty();
    }

    @Override
    public String itOrDefault(final String value, final String defaultValue) {
        if (defaultValue == null) {
            throw new IllegalArgumentException("defaultValue == null");
        }

        return isEmpty(value) ? defaultValue : value;
    }

    @Override
    public String mapIntegerListToStringWithSeparator(final List<Integer> items, final String separator) {
        if (items == null || items.isEmpty()) {
            return EMPTY;
        }

        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < items.size(); i ++) {
            stringBuilder.append(items.get(i));

            if (i < items.size() - 1) {
                stringBuilder.append(separator);
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public List<Integer> parseToIntegerList(final String csvList, final String separator) {
        if (isEmpty(csvList)) {
            return Collections.emptyList();
        }

        return Stream.of(csvList.split(separator))
                     .map(Integer::parseInt)
                     .toList();
    }
}
