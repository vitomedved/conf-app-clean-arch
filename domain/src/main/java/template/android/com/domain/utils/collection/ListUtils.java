package template.android.com.domain.utils.collection;

import java.util.Enumeration;
import java.util.List;

public interface ListUtils {

    /**
     * Evaluates if given list is empty or not.
     *
     * <p>
     * List will be evaluated as empty if and only if there are no items in list or list is {@code null}.
     * </p>
     *
     * @param list list to evaluate.
     * @return true if list is empty, false otherwise.
     */
    boolean isEmpty(final List<?> list);

    /**
     * Reverts input list items order.
     *
     * <p>
     *     Same list is always returned as passed one. If {@code null} value is passed, {@link NullPointerException} is thrown.
     * </p>
     *
     * @param list list to reverse.
     * @throws NullPointerException if list is {@code null}.
     * @return list in reversed order.
     */
    <T> List<T> reverse(List<T> list);

    /**
     * Converts enumeration to a list.
     *
     * <p>
     *     {@link NullPointerException} will be thrown if list is {@code null}.
     * </p>
     *
     * @param enumeration enumeration to convert to list.
     * @throws NullPointerException if a list is {@code null}.
     * @return enumeration converted to a list.
     */
    <T> List<T> toList(Enumeration<T> enumeration);
}
