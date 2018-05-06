package template.android.com.data.dao.mapper;

import template.android.com.data.dao.model.ExampleDbModel;
import template.android.com.domain.model.Example;

public interface DbMapper {

    Example toExample(ExampleDbModel model);
}
