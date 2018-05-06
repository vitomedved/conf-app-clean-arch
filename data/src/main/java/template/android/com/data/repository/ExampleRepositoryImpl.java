package template.android.com.data.repository;

import com.annimon.stream.Optional;

import rx.Completable;
import rx.Single;
import template.android.com.data.dao.ExampleDao;
import template.android.com.data.network.client.ExampleClient;
import template.android.com.domain.model.Example;
import template.android.com.domain.repository.ExampleRepository;

public final class ExampleRepositoryImpl implements ExampleRepository {

    private final ExampleClient exampleClient;
    private final ExampleDao exampleDao;

    public ExampleRepositoryImpl(final ExampleClient exampleClient, final ExampleDao exampleDao) {
        this.exampleClient = exampleClient;
        this.exampleDao = exampleDao;
    }

    @Override
    public Single<Example> fetchExample() {
        return exampleClient.getExample();
    }

    @Override
    public Single<Optional<Example>> getExample() {
        // TODO
        return Single.just(Optional.empty());
    }

    @Override
    public Completable saveExample(final Example example) {
        return Completable.complete();
//        return Completable.fromAction(() -> exampleDao.insert(example));
    }
}
