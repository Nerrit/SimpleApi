package it.nerr.simpleapi.route;

import it.nerr.simpleapi.request.WebRequest;
import it.nerr.simpleapi.request.WebResponse;

public interface Router {

    WebResponse exchange(WebRequest request);
}
