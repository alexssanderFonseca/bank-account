package br.com.alexsdm.conta.compartilhado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsClientConfig {

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .build();
    }
}
