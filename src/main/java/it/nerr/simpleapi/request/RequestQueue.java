package it.nerr.simpleapi.request;

import reactor.core.publisher.Flux;

public interface RequestQueue<T> {

    boolean push(T request);

    Flux<T> requests();
}
