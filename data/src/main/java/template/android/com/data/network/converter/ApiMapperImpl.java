package template.android.com.data.network.converter;

import template.android.com.data.network.model.ApiExample;
import template.android.com.domain.model.Example;
import template.android.com.domain.utils.StringUtils;

public final class ApiMapperImpl implements ApiMapper {

    private final StringUtils stringUtils;

    public ApiMapperImpl(final StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    @Override
    public Example mapToExample(final ApiExample apiExample) {
        return new Example(apiExample.id);
    }
}
