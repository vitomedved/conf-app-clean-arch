package template.android.com.domain.utils.string;

import java.util.List;

public interface StringUtils {

    /**
     * Checks whether {@code text} value is {@code null} or empty String.
     *
     * @param text text to evaluate.
     * @return true if {@code text} is empty or {@code null}.
     */
    boolean isEmpty(String text);

    /**
     * Returns {@code value} if non-empty, {@code defaultValue} otherwise.
     *
     * <p>
     * If value passed as parameter, evaluated with {@link #isEmpty(String)} is not empty, value itself is returned, otherwise
     * default value is returned. {@code defaultValue} must not be {@code null}, otherwise {@link NullPointerException} is thrown.
     * </p>
     *
     * @param value        value itself if not empty, default value otherwise.
     * @param defaultValue fallback value to return in case original value is empty.
     * @return value if non empty, defaultValue otherwise.
     * @throws NullPointerException if {@code defaultValue} is {@code null}.
     */
    String itOrDefault(String value, String defaultValue);

    /**
     * Maps list of Integers to a single String using specified separator.
     *
     * @param items     list of items to combine into a single String.
     * @param separator value to use as separator for Integer values.
     * @return elements of {@code items} list combined into single String using specified separator.
     */
    String mapIntegerListToStringWithSeparator(List<Integer> items, String separator);

    /**
     * Parses input {@code csvList} to list of Integers using specified separator.
     *
     * @param csvList   String representation of list to split.
     * @param separator separator to use for splitting input String.
     * @return List of Integers from input {@code csvList} splitted by {@code separator}.
     */
    List<Integer> parseToIntegerList(String csvList, String separator);

    /**
     * Checks whether {@code value} contains only numbers and/or letters.
     *
     * @param value String value that is checked for numbers and letters
     * @return Boolean value whether string contains only numbers and/or letters. This will also return false if string is empty.
     */
    Boolean isAlphaNumeric(String value);
}
