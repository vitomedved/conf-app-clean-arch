package template.android.com.domain.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public final class ListUtilsImpl implements ListUtils {

    private final CollectionUtils collectionUtils;

    public ListUtilsImpl(final CollectionUtils collectionUtils) {
        this.collectionUtils = collectionUtils;
    }

    @Override
    public boolean isEmpty(final List<?> list) {
        return collectionUtils.isEmpty(list);
    }

    @Override
    public <T> List<T> reverse(final List<T> list) {
        Objects.requireNonNull(list, "list == null");

        Collections.reverse(list);

        return list;
    }

    @Override
    public <T> List<T> toList(final Enumeration<T> enumeration) {
        Objects.requireNonNull(enumeration, "enumeration == null");

        final List<T> list = new ArrayList<>();

        while (enumeration.hasMoreElements()) {
            list.add(enumeration.nextElement());
        }

        return list;
    }
}
