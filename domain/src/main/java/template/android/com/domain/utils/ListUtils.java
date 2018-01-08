package template.android.com.domain.utils;

import java.util.List;

public interface ListUtils {

    boolean isEmpty(final List<?> list);

    <T> List<T> reverse(List<T> list);
}
