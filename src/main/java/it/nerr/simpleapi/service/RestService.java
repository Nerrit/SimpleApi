package it.nerr.simpleapi.service;

import it.nerr.simpleapi.route.Router;

public abstract class RestService {

    private final Router router;

    protected RestService(Router router) {
        this.router = router;
    }

    protected Router getRouter() {
        return router;
    }
}
