package com.adityap.nyt.app.tests;

import android.os.Build;
import android.test.mock.MockContext;

import com.adityap.nyt.BuildConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
@RunWith(RobolectricGradleTestRunner.class)
public class AppTest
{
    @Test
    public void shouldPass()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void shouldFail()
    {
        Assert.assertTrue(false);
    }
}
