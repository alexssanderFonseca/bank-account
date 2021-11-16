package br.com.alexsdm.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@SpringBootApplication
public class BankApplication {

    @Autowired
    private DynamoDbEnhancedClient client;

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }


}
