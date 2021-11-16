package br.com.alexsdm.conta.compartilhado.database.item;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@Getter
@NoArgsConstructor
@Setter
public class ContaItem {

    private String id;

    private String sk;

    private String agencia;

    private String numero;

    private String codigoBanco;

    private String senha;

    private Long momentoCadastro;

    public void gerarId() {
        this.id = "CONTA#" + UUID.randomUUID();
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("pk")
    public String getId() {
        return id;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("sk")
    public String getSk() {
        return this.id;
    }


}