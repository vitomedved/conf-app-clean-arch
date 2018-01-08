package template.android.com.domain.utils;

import java.util.Collections;
import java.util.List;

public final class ListUtilsImpl implements ListUtils {

    @Override
    public boolean isEmpty(final List<?> list) {
        return list == null || list.isEmpty();
    }

    @Override
    public <T> List<T> reverse(final List<T> list) {
        Collections.reverse(list);

        return list;
    }
}
