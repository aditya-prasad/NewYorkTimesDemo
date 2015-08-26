package com.adityap.nyt.domain.core;

import rx.Observable;

public interface UseCase<T>
{
    Observable<T> execute();
}
