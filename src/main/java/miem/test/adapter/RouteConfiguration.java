package miem.test.adapter;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RouteConfiguration extends BasicRouteConfiguration {

    private static final String ROUTING_KEY_HEADER = "rabbitmq.ROUTING_KEY";

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    public void configure() throws Exception {

//        errorHandler(new TransactionErrorHandlerBuilder());

        super.configure();

        from("{{incoming.from.uri}}")
//                .transacted()
                .routeId("IN.MQ")
                .setHeader(ROUTING_KEY_HEADER, simple("{{queue.out}}"))
                .to("direct:prepareHeadersAndLog")
                .to("{{outgoing.to.uri}}")
                .log(LoggingLevel.INFO, getLogger(), "Successfully sent to the destination");
    }
}
