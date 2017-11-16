package com.test.eventtracker;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.jetty.server.Slf4jRequestLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 *
 */
@SpringBootApplication
@ServletComponentScan
@ComponentScan("com.test.eventtracker")
@EnableCaching
@EnableWebMvc
@EnableJpaRepositories("com.test.eventtracker.persistence.repository")
public class EventTrackerApplication {
    private final static Logger logger = LoggerFactory.getLogger(EventTrackerApplication.class);

    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServers;


    @Value("${cache.expiration.time:1800}")
    private Integer authenticationTimeWindow;

    @Value("${ignore.ssl.cert:false}")
    private boolean ignoreSSLCertificate;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public Producer<Long, String> kafkaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    @Bean
    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory(
            @Value("${server.port:8080}") final String mainPort) {

        final JettyEmbeddedServletContainerFactory factory =
                new JettyEmbeddedServletContainerFactory(Integer.valueOf(mainPort));


        factory.addServerCustomizers((JettyServerCustomizer) server ->
                server.setRequestLog(new Slf4jRequestLog()));

        return factory;
    }


    public static void main(String[] args) {

        DefaultPropertiesApplication.run(EventTrackerApplication.class, args);
    }


}
