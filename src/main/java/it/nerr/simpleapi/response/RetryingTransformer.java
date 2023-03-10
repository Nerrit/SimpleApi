package it.nerr.simpleapi.response;

import it.nerr.simpleapi.http.client.ClientResponse;
import it.nerr.simpleapi.request.RouteMatcher;
import it.nerr.simpleapi.request.WebRequest;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.util.function.Function;

public class RetryingTransformer implements ResponseFunction {

    private final RouteMatcher routeMatcher;
    private final reactor.retry.Retry<?> retryFactory;

    public RetryingTransformer(RouteMatcher routeMatcher, reactor.retry.Retry<?> retryFactory) {
        this.routeMatcher = routeMatcher;
        this.retryFactory = retryFactory;
    }

    @Override
    public Function<Mono<ClientResponse>, Mono<ClientResponse>> transform(WebRequest request) {
        if (routeMatcher.matches(request)) {
            return mono -> mono.retryWhen(Retry.withThrowable(retryFactory));
        }
        return mono -> mono;
    }
}
