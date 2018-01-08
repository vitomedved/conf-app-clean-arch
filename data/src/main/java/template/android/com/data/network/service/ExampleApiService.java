package template.android.com.data.network.service;

import retrofit2.http.GET;
import rx.Single;
import template.android.com.data.network.model.ApiExample;

public interface ExampleApiService {

    @GET
    Single<ApiExample> getExample();
}
