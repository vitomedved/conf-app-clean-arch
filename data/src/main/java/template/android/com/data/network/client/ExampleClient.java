package template.android.com.data.network.client;

import io.reactivex.Single;
import template.android.com.domain.model.Example;

public interface ExampleClient {

    Single<Example> getExample();
}
