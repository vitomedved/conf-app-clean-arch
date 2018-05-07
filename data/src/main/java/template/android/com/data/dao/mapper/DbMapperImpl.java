package template.android.com.data.dao.mapper;

import template.android.com.data.dao.model.ExampleDbModel;
import template.android.com.domain.model.Example;
import template.android.com.domain.utils.StringUtils;

public final class DbMapperImpl implements DbMapper {

    private final StringUtils stringUtils;

    public DbMapperImpl(final StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    @Override
    public Example toExample(final ExampleDbModel model) {
        return new Example(model.id);
    }
}
