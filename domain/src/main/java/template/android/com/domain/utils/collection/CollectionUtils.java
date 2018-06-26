package template.android.com.domain.utils.collection;

import java.util.Collection;

public interface CollectionUtils {

    /**
     * Evaluates if a collection is empty or not.
     *
     * <p>
     * Collection will be evaluated as empty if there are no items in collection or if collection is {@code null}.
     * </p>
     *
     * @param collection collection to evaluate.
     * @return true if collection is empty, false otherwise.
     */
    boolean isEmpty(Collection<?> collection);
}
