package br.com.alexsdm.conta.compartilhado.database.item;

import br.com.alexsdm.conta.dominio.Conta;
import br.com.alexsdm.conta.dominio.Titular;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@Getter
@NoArgsConstructor
@Setter
@ToString
public class ContaItem {

    private String id;

    private String sk;

    private String numero;

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
        this.sk = id;
        return sk;
    }

    public Conta paraConta(Titular titular, String idConta) {
        return Conta.builder()
                .id(idConta.replace("CONTA#", ""))
                .numero(this.numero)
                .titular(titular)
                .build();
    }

}