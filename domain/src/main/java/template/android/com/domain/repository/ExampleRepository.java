package template.android.com.domain.repository;

import com.annimon.stream.Optional;

import rx.Completable;
import rx.Single;
import template.android.com.domain.model.Example;

public interface ExampleRepository {

    Single<Example> fetchExample();

    Single<Optional<Example>> getExample();

    Completable saveExample(Example example);
}
