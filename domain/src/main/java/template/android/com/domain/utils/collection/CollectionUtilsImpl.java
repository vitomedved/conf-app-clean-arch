package template.android.com.domain.utils.collection;

import java.util.Collection;

public final class CollectionUtilsImpl implements CollectionUtils {

    @Override
    public boolean isEmpty(final Collection<?> collection) {
        if (collection == null) {
            return true;
        }

        return collection.isEmpty();
    }
}
