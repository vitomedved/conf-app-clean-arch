package template.android.com.domain.utils;

import java.util.Enumeration;
import java.util.List;

public interface ListUtils {

    boolean isEmpty(final List<?> list);

    <T> List<T> reverse(List<T> list);

    <T> List<T> toList(Enumeration<T> enumeration);
}
