package it.nerr.simpleapi.response;

import it.nerr.simpleapi.http.client.ClientException;
import it.nerr.simpleapi.http.client.ClientResponse;
import it.nerr.simpleapi.request.RouteMatcher;
import it.nerr.simpleapi.request.WebRequest;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

public interface ResponseFunction {

    static EmptyResponseTransformer emptyIfNotFound() {
        return new EmptyResponseTransformer(RouteMatcher.any(), ClientException.isStatusCode(404));
    }

    static EmptyResponseTransformer emptyIfNotFound(RouteMatcher routeMatcher) {
        return new EmptyResponseTransformer(routeMatcher, ClientException.isStatusCode(404));
    }

    static EmptyResponseTransformer emptyOnErrorStatus(RouteMatcher routeMatcher, Integer... codes) {
        return new EmptyResponseTransformer(routeMatcher, ClientException.isStatusCode(codes));
    }

    static RetryingTransformer retryOnceOnErrorStatus(Integer... codes) {
        return new RetryingTransformer(RouteMatcher.any(),
                reactor.retry.Retry.onlyIf(ClientException.isRetryContextStatusCode(codes))
                        .fixedBackoff(Duration.ofSeconds(1)).retryOnce());
    }

    static RetryingTransformer retryOnceOnErrorStatus(RouteMatcher routeMatcher, Integer... codes) {
        return new RetryingTransformer(routeMatcher,
                reactor.retry.Retry.onlyIf(ClientException.isRetryContextStatusCode(codes))
                        .fixedBackoff(Duration.ofSeconds(1)).retryOnce());
    }

    static RetryingTransformer retryWhen(RouteMatcher routeMatcher, reactor.retry.Retry<?> retry) {
        return new RetryingTransformer(routeMatcher, retry);
    }

    Function<Mono<ClientResponse>, Mono<ClientResponse>> transform(WebRequest request);
}
