package template.android.com.data.network.client;

import rx.Single;
import template.android.com.domain.model.Example;

public interface ExampleClient {

    Single<Example> getExample();
}
