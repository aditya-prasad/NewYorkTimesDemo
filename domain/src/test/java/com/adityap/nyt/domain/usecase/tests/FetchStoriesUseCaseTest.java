package com.adityap.nyt.domain.usecase.tests;

import com.adityap.nyt.domain.model.story.Story;
import com.adityap.nyt.domain.model.story.StoryService;
import com.adityap.nyt.domain.usecase.FetchStoriesUseCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FetchStoriesUseCaseTest
{
    @Mock
    StoryService storyService;

    FetchStoriesUseCase fetchStoriesUseCase;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);

        Observable<List<Story>> observable = Observable.just(null);

        when(storyService.getStories(anyInt())).thenReturn(observable);

        fetchStoriesUseCase = new FetchStoriesUseCase(storyService);
    }

    @Test
    public void shouldPass()
    {
        Assert.assertTrue("Hello, World!", true);
    }

    @Test
    public void test()
    {
        fetchStoriesUseCase.setSection(1);
        fetchStoriesUseCase.execute();

        verify(storyService).getStories(1);
    }

    @Test
    public void shouldFail()
    {
        Assert.assertTrue(false);
    }
}
