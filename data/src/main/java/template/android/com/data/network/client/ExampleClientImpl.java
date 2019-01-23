package template.android.com.data.network.client;

import io.reactivex.Single;
import template.android.com.data.network.converter.ApiMapper;
import template.android.com.data.network.service.ExampleApiService;
import template.android.com.domain.model.Example;

public final class ExampleClientImpl implements ExampleClient {

    private final ApiMapper apiMapper;
    private final ExampleApiService apiService;

    public ExampleClientImpl(final ApiMapper apiMapper, final ExampleApiService apiService) {
        this.apiMapper = apiMapper;
        this.apiService = apiService;
    }

    @Override
    public Single<Example> getExample() {
        return Single.just(new Example(100));
//        return apiService.getExample()
//                         .map(apiMapper::mapToExample);
    }
}
