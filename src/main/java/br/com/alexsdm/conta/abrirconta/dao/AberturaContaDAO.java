package br.com.alexsdm.conta.abrirconta.dao;

import br.com.alexsdm.conta.compartilhado.database.item.ContaItem;
import br.com.alexsdm.conta.compartilhado.database.item.EnderecoItem;
import br.com.alexsdm.conta.compartilhado.database.item.TitularItem;
import br.com.alexsdm.conta.compartilhado.database.mapper.ContaItemMapper;
import br.com.alexsdm.conta.compartilhado.database.mapper.EnderecoItemMapper;
import br.com.alexsdm.conta.compartilhado.database.mapper.TitularItemMapper;
import br.com.alexsdm.conta.domain.Conta;
import br.com.alexsdm.conta.domain.Endereco;
import br.com.alexsdm.conta.domain.Titular;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class AberturaContaDAO {

    private final ContaItemMapper contaItemMapper;
    private final EnderecoItemMapper enderecoItemMapper;
    private final TitularItemMapper titularItemMapper;
    private static final String TABLE_NAME = "conta";
    private DynamoDbEnhancedClient client;
    private final DynamoDbTable<ContaItem> contaTabela;
    private final DynamoDbTable<TitularItem> titularTabela;
    private final DynamoDbTable<EnderecoItem> enderecoTabela;


    @Autowired
    public AberturaContaDAO(DynamoDbEnhancedClient client, ContaItemMapper contaItemMapper, EnderecoItemMapper enderecoItemMapper,
                            TitularItemMapper titularItemMapper) {
        this.contaTabela = client.table(TABLE_NAME, TableSchema.fromClass(ContaItem.class));
        this.titularTabela = client.table(TABLE_NAME, TableSchema.fromClass(TitularItem.class));
        this.enderecoTabela = client.table(TABLE_NAME, TableSchema.fromClass(EnderecoItem.class));
        this.contaItemMapper = contaItemMapper;
        this.enderecoItemMapper = enderecoItemMapper;
        this.titularItemMapper = titularItemMapper;
        this.client = client;
    }

    public Map<String, Object> abrir(Conta conta, Titular titular, Endereco endereco) {
        var contaItem = contaItemMapper.deConta(conta);
        contaItem.gerarId();
        var titularItem = titularItemMapper.deTitular(titular);
        titularItem.gerarId();
        titularItem.setContaId(contaItem.getId());
        var enderecoItem = enderecoItemMapper.deEndereco(endereco);
        enderecoItem.gerarId();
        enderecoItem.setTitularId(titularItem.getId());

        client.transactWriteItems(r -> r.addPutItem(contaTabela, contaItem)
                .addPutItem(titularTabela, titularItem)
                .addPutItem(enderecoTabela, enderecoItem));

        return Map.of(TABLE_NAME, contaItem, "titular", titularItem, "endereco", enderecoItem);
    }


}
