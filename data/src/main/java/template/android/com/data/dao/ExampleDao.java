package template.android.com.data.dao;

import com.annimon.stream.Optional;

import template.android.com.domain.model.Example;

public interface ExampleDao {

    void saveExample(Example example);

    Optional<Example> getExample();
}
