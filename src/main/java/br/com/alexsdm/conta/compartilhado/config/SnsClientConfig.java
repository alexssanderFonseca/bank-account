package br.com.alexsdm.conta.compartilhado.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsClientConfig {

    @Value("${aws-region}")
    private String regiao;

    @Value("${aws-endpoint-sqs}")
    private String endpoint;

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .build();
    }
}
