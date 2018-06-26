package template.android.com.domain.utils.collection;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty(final List<?> list) {
        return collectionUtils.isEmpty(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> reverse(final List<T> list) {
        Objects.requireNonNull(list, "list == null");

        Collections.reverse(list);

        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<T> toList(final Enumeration<T> enumeration) {
        Objects.requireNonNull(enumeration, "enumeration == null");

        final ArrayList<T> list = new ArrayList<>();

        while (enumeration.hasMoreElements()) {
            list.add(enumeration.nextElement());
        }

        list.trimToSize();

        return list;
    }
}
