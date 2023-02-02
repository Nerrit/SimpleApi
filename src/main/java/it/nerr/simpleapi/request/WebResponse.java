package it.nerr.simpleapi.request;

import it.nerr.simpleapi.http.client.ClientResponse;
import it.nerr.simpleapi.util.ReactorResources;
import reactor.core.publisher.Mono;

public class WebResponse {

    private final Mono<ClientResponse> responseMono;
    private final ReactorResources reactorResources;

    public WebResponse(Mono<ClientResponse> responseMono, ReactorResources reactorResources) {
        this.responseMono = responseMono;
        this.reactorResources = reactorResources;
    }

    public <T> Mono<T> bodyToMono(Class<T> responseClass) {
        return responseMono.flatMap(res -> res.bodyToMono(responseClass))
                .publishOn(reactorResources.getBlockingTaskScheduler());
    }

    public Mono<Void> skipBody() {
        return responseMono.flatMap(ClientResponse::skipBody).publishOn(reactorResources.getBlockingTaskScheduler());
    }

    public Mono<ClientResponse> mono() {
        return responseMono;
    }
}
