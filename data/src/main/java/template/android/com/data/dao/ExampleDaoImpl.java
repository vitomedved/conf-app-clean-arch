package template.android.com.data.dao;

import com.annimon.stream.Optional;

import template.android.com.domain.model.Example;

public final class ExampleDaoImpl implements ExampleDao {

    @Override
    public Optional<Example> getExample() {
        return Optional.empty();
    }

    @Override
    public void saveExample(final Example example) {
        // NO-OP
    }
}
