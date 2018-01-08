package template.android.com.domain.utils.usecase;

import rx.Single;
import template.android.com.domain.model.Example;
import template.android.com.domain.repository.ExampleRepository;

public final class GetExampleUseCaseImpl implements GetExampleUseCase {

    private final ExampleRepository exampleRepository;

    public GetExampleUseCaseImpl(final ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    @Override
    public Single<Example> execute() {
        return exampleRepository.fetchExample();
    }
}
