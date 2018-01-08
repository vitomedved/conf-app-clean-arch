package template.android.com.domain.utils.usecase;

import rx.Single;
import template.android.com.domain.model.Example;

public interface GetExampleUseCase {

    Single<Example> execute();
}
