package template.android.com.domain.usecase;

import rx.Observable;
import template.android.com.domain.model.Example;

public interface GetExampleUseCase {

    Observable<Example> execute();
}
