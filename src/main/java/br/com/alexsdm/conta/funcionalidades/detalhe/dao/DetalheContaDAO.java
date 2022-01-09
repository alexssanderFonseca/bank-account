package br.com.alexsdm.conta.funcionalidades.detalhe.dao;

import br.com.alexsdm.conta.compartilhado.database.item.ContaItem;
import br.com.alexsdm.conta.compartilhado.database.item.EnderecoItem;
import br.com.alexsdm.conta.compartilhado.database.item.TitularItem;
import br.com.alexsdm.conta.compartilhado.database.mapper.TitularItemMapper;
import br.com.alexsdm.conta.dominio.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

@Repository
@RequiredArgsConstructor
public class DetalheContaDAO {

    private final DynamoDbEnhancedClient client;

    private final TitularItemMapper titularItemMapper;
    private static final String TABLE_NAME = "conta";


    public Conta detalhe(String contaId) {
        var contaItem = getConta(contaId);
        var titularItem = getTitular(contaItem.getId());
        var enderecoItem = getEndereco(titularItem.getId());
        return contaItem.paraConta(titularItem.paraTitular(enderecoItem), contaId);

    }

    private ContaItem getConta(String contaId) {
        var contaItemTable = getTabelaPara(TableSchema.fromClass(ContaItem.class));
        var key = Key.builder().partitionValue("CONTA#" + contaId).sortValue("CONTA#" + contaId).build();
        return contaItemTable.getItem(key);
    }

    private TitularItem getTitular(String contaId) {
        var titutarItem = getTabelaPara(TableSchema.fromClass(TitularItem.class));
        var key = Key.builder().partitionValue(contaId).sortValue("TITULAR#").build();
        return titutarItem.query(QueryConditional.sortBeginsWith(key)).items().iterator().next();
    }

    private EnderecoItem getEndereco(String titularId) {
        var enderecoItem = getTabelaPara(TableSchema.fromClass(EnderecoItem.class));
        var key = Key.builder().partitionValue(titularId).sortValue("ENDERECO#").build();
        return enderecoItem.query(QueryConditional.sortBeginsWith(key)).items().iterator().next();
    }


    private <T> DynamoDbTable<T> getTabelaPara(TableSchema<T> schema) {
        return client.table(TABLE_NAME, schema);
    }


}
