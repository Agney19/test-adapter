package miem.test.adapter;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;

public abstract class BasicRouteConfiguration extends RouteBuilder {

    protected abstract Logger getLogger();

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .log(LoggingLevel.ERROR, getLogger(), "Exception in route ${routeId} on ${exchangeId}")
                .log(LoggingLevel.ERROR, getLogger(), "Message headers: ${headers}")
                .log(LoggingLevel.ERROR, getLogger(), "Message body: ${body}")
                .log(LoggingLevel.ERROR, getLogger(), "Stacktrace: ${exception,stacktrace}");

        from("direct:prepareHeadersAndLog")
                .routeId("LOG")
                .setProperty("originalHeaders", simple("${headers}"))
                .setProperty("originalBody", simple("${body}"))
                .log(LoggingLevel.INFO, getLogger(), "Incoming message headers: ${headers}")
                .log(LoggingLevel.INFO, getLogger(), "Incoming message body: ${body}");
    }
}
