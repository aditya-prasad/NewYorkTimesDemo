package com.adityap.nyt.data.story.tests;

import com.adityap.nyt.data.story.StoryServiceImpl;
import com.adityap.nyt.data.story.cache.StoryCache;
import com.adityap.nyt.data.story.cloud.StoryApi;
import com.adityap.nyt.domain.core.Storage;
import com.adityap.nyt.domain.model.story.Story;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class StoryServiceImplTest
{
    @Mock
    StoryApi storyApi;

    @Mock
    StoryCache storyCache;

    @Mock
    Storage storage;

    StoryServiceImpl storyService;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);

        storyService = new StoryServiceImpl(storyApi, storyCache, storage);
    }

    @Test
    public void testGetStories()
    {
        when(storyCache.getStories(anyInt())).thenReturn(Observable.<List<Story>>empty());

        Assert.assertTrue(!storyService.getStories(1).toBlocking().toIterable().iterator()
                                       .hasNext());
    }

    @Test
    public void shouldFail()
    {
        Assert.assertTrue(false);
    }
}
