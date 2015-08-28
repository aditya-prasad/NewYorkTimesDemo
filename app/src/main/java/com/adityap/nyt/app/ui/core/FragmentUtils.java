package com.adityap.nyt.app.ui.core;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtils
{
    public static void changeFragment(FragmentManager manager, @IdRes int layout, Fragment fragment, int transitionStyle)
    {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(layout, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.setTransition(transitionStyle);
        fragmentTransaction.commit();
        manager.executePendingTransactions();
    }
}
