package br.com.alexsdm.conta.compartilhado.database.item;

import br.com.alexsdm.conta.dominio.Titular;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@Getter
@Setter
@ToString
public class TitularItem {
    private String id;
    private String contaId;
    private String nomeCompleto;
    private String dataNascimento;
    private String email;
    private String cpf;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("pk")
    public String getContaId() {
        return contaId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("sk")
    public String getId() {
        return id;
    }

    public void setContaId(String contaId) {
        this.contaId = contaId;
    }

    public void gerarId() {
        this.id = "TITULAR#" + UUID.randomUUID();
    }

    public Titular paraTitular(EnderecoItem enderecoItem) {
        return Titular.builder()
                .cpf(this.cpf)
                .dataNascimento(this.dataNascimento)
                .email(this.email)
                .nomeCompleto(this.nomeCompleto)
                .endereco(enderecoItem.paraEndereco())
                .build();
    }
}
