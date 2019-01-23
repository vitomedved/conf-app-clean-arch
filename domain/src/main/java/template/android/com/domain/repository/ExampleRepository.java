package template.android.com.domain.repository;

import com.annimon.stream.Optional;

import io.reactivex.Completable;
import io.reactivex.Single;
import template.android.com.domain.model.Example;

public interface ExampleRepository {

    Single<Example> fetchExample();

    Single<Optional<Example>> getExample();

    Completable saveExample(Example example);
}
