package template.android.com.data.network.converter;

import template.android.com.data.network.model.ApiExample;
import template.android.com.domain.model.Example;

public interface ApiMapper {

    Example mapToExample(ApiExample apiExample);
}
