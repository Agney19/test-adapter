package miem.test.adapter;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.jms.admin.RMQConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Endpoint;
import org.apache.camel.LoggingLevel;
import org.apache.camel.component.rabbitmq.RabbitMQEndpoint;
import org.apache.camel.spring.spi.TransactionErrorHandlerBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RouteConfiguration extends BasicRouteConfiguration {

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
                .to("direct:prepareHeadersAndLog")
                .to("{{outgoing.to.uri}}")
                .log(LoggingLevel.INFO, getLogger(), "Successfully sent to destination");
    }
}
