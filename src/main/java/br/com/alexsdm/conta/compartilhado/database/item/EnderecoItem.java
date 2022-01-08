package br.com.alexsdm.conta.compartilhado.database.item;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@Getter
@Setter
public class EnderecoItem {

    private String titularId;
    private String enderecoId;
    private String principal;
    private String logradouro;
    private String bairro;
    private String cep;
    private String pais;

    public void gerarId() {
        this.enderecoId = "ENDERECO#" + UUID.randomUUID();
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("pk")
    public String getTitularId() {
        return titularId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("sk")
    public String getEnderecoId() {
        return enderecoId;
    }
}
