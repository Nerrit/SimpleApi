package it.nerr.simpleapi.request;

import it.nerr.simpleapi.http.ExchangeStrategies;
import it.nerr.simpleapi.response.ResponseFunction;
import it.nerr.simpleapi.util.ReactorResources;

import java.util.List;

public class RouterOptions {

    private final String token;
    private final ReactorResources reactorResources;
    private final ExchangeStrategies exchangeStrategies;
    private final List<ResponseFunction> responseTransformers;
    private final GlobalRateLimiter globalRateLimiter;
    private final RequestQueueFactory requestQueueFactory;
    private final String baseUrl;

    public RouterOptions(String token, ReactorResources reactorResources, ExchangeStrategies exchangeStrategies,
                         List<ResponseFunction> responseTransformers, GlobalRateLimiter globalRateLimiter,
                         RequestQueueFactory requestQueueFactory, String baseUrl) {
        this.token = token;
        this.reactorResources = reactorResources;
        this.exchangeStrategies = exchangeStrategies;
        this.responseTransformers = responseTransformers;
        this.globalRateLimiter = globalRateLimiter;
        this.requestQueueFactory = requestQueueFactory;
        this.baseUrl = baseUrl;
    }

    public String getToken() {
        return token;
    }

    public ReactorResources getReactorResources() {
        return reactorResources;
    }

    public ExchangeStrategies getExchangeStrategies() {
        return exchangeStrategies;
    }

    public List<ResponseFunction> getResponseTransformers() {
        return responseTransformers;
    }

    public GlobalRateLimiter getGlobalRateLimiter() {
        return globalRateLimiter;
    }

    public RequestQueueFactory getRequestQueueFactory() {
        return requestQueueFactory;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
