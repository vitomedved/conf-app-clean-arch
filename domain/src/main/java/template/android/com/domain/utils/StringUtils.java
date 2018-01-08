package template.android.com.domain.utils;

import java.util.List;

public interface StringUtils {

    boolean isEmpty(String text);

    String itOrDefault(String value, String defaultValue);

    String mapIntegerListToStringWithSeparator(List<Integer> items, String separator);

    List<Integer> parseToIntegerList(String csvList, String separator);
}
