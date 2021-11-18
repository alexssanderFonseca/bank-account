package br.com.alexsdm.conta.detalhesconta;

import static software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional.sortBeginsWith;


import br.com.alexsdm.conta.compartilhado.database.item.TitularItem;
import br.com.alexsdm.conta.compartilhado.database.mapper.ContaItemMapper;
import br.com.alexsdm.conta.compartilhado.database.mapper.EnderecoItemMapper;
import br.com.alexsdm.conta.compartilhado.database.mapper.TitularItemMapper;
import br.com.alexsdm.conta.domain.Titular;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DetalheTitularDAO {

    private final TitularItemMapper titularItemMapper;
    private static final String TABLE_NAME = "conta";
    private DynamoDbEnhancedClient client;
    private final DynamoDbTable<TitularItem> titularTabela;


    @Autowired
    public DetalheTitularDAO(DynamoDbEnhancedClient client, ContaItemMapper contaItemMapper, EnderecoItemMapper enderecoItemMapper,
                             TitularItemMapper titularItemMapper) {
        this.titularTabela = client.table(TABLE_NAME, TableSchema.fromClass(TitularItem.class));
        this.titularItemMapper = titularItemMapper;
        this.client = client;
    }

    public List<Titular> get(String id) {
        var key = Key.builder().partitionValue("CONTA#" + id).sortValue("TITULAR").build();
        var titularesPage = titularTabela.query(sortBeginsWith(key));
        return titularesPage.items().stream().map(titularItemMapper::paraTitular).collect(Collectors.toList());
    }


}
