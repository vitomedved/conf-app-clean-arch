package template.android.com.data.network.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import template.android.com.data.network.model.ApiExample;

public interface ExampleApiService {

    @GET("/")
    Single<ApiExample> getExample();
}
