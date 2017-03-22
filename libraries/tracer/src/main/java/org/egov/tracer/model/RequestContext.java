package org.egov.tracer.model;

import org.slf4j.MDC;

public class RequestContext {

    public static String CORRELATION_ID = "x-correlation-id";

    private static final ThreadLocal<String> id = new ThreadLocal<>();

    public static String getId() { return id.get(); }

    public static void setId(String correlationId) {
        id.set(correlationId);
        MDC.put(RequestContext.CORRELATION_ID, correlationId);
    }
}
