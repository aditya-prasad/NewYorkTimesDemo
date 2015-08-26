package com.adityap.nyt.data.story.cloud;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface StoryApi
{
    @GET("/{section}.json")
    Observable<StoryApiResponse> getStories(@Path("section") String section);
}
