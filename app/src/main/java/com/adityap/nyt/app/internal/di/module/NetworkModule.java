package com.adityap.nyt.app.internal.di.module;

import com.adityap.nyt.app.config.NewYorkTimesApiConstants;
import com.adityap.nyt.app.internal.di.qualifier.NytGson;
import com.adityap.nyt.app.internal.di.scope.ApplicationScope;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module
public class NetworkModule
{
    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient()
    {
        return new OkHttpClient();
    }

    @Provides
    @ApplicationScope
    public RestAdapter provideRestAdapter(OkHttpClient okHttpClient, @NytGson Gson gson)
    {
        return new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson))
                .setEndpoint(NewYorkTimesApiConstants.API_ENDPOINT)
                .setRequestInterceptor(request ->
                        request.addQueryParam("api-key", NewYorkTimesApiConstants.API_KEY))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}
