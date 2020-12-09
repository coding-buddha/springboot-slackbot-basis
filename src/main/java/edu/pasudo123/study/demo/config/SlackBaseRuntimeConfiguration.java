package edu.pasudo123.study.demo.config;

import com.hubspot.slack.client.SlackClient;
import com.hubspot.slack.client.SlackClientFactory;
import com.hubspot.slack.client.SlackClientRuntimeConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackBaseRuntimeConfiguration {

    @Value("${slack.token}")
    private String token;

    @Bean
    public SlackClient getClient() {
        return SlackClientFactory.defaultFactory().build(get());
    }

    private SlackClientRuntimeConfig get() {
        return SlackClientRuntimeConfig.builder()
                .setTokenSupplier(() -> token)
                .build();
    }
}
