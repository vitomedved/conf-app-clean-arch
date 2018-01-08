package template.android.com.domain.usecase;

import com.annimon.stream.Optional;

import rx.Observable;
import template.android.com.domain.model.Example;
import template.android.com.domain.repository.ExampleRepository;

public final class GetExampleUseCaseImpl implements GetExampleUseCase {

    private final ExampleRepository repository;

    public GetExampleUseCaseImpl(final ExampleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Example> execute() {
        return getExampleFromCache().concatWith(fetchExample())
                                    .distinct();
    }

    private Observable<Example> getExampleFromCache() {
        return repository.getExample()
                         .toObservable()
                         .filter(Optional::isPresent)
                         .map(Optional::get);
    }

    private Observable<Example> fetchExample() {
        return repository.fetchExample()
                         .toObservable()
                         .flatMapCompletable(repository::saveExample);
    }
}
