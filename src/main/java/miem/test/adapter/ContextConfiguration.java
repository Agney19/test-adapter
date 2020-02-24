package miem.test.adapter;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    @Bean
    public ConnectionFactory factory1() {
        ConnectionFactory f = new ConnectionFactory();
        f.setUsername("admin");
        f.setPassword("admin");
        f.setHost("192.168.1.50");

        return f;
    }
}
