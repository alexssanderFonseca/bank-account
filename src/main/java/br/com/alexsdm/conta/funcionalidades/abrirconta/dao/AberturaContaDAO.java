package br.com.alexsdm.conta.funcionalidades.abrirconta.dao;

import br.com.alexsdm.conta.compartilhado.database.item.ContaItem;
import br.com.alexsdm.conta.compartilhado.database.item.EnderecoItem;
import br.com.alexsdm.conta.compartilhado.database.item.TitularItem;
import br.com.alexsdm.conta.compartilhado.database.mapper.ContaItemMapper;
import br.com.alexsdm.conta.compartilhado.database.mapper.EnderecoItemMapper;
import br.com.alexsdm.conta.compartilhado.database.mapper.TitularItemMapper;
import br.com.alexsdm.conta.dominio.Conta;
import br.com.alexsdm.conta.dominio.Endereco;
import br.com.alexsdm.conta.dominio.Titular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class AberturaContaDAO {

    private final ContaItemMapper contaItemMapper;
    private final EnderecoItemMapper enderecoItemMapper;
    private final TitularItemMapper titularItemMapper;
    private static final String TABLE_NAME = "conta";
    private DynamoDbEnhancedClient client;


    @Autowired
    public AberturaContaDAO(DynamoDbEnhancedClient client, ContaItemMapper contaItemMapper, EnderecoItemMapper enderecoItemMapper,
                            TitularItemMapper titularItemMapper) {

        this.contaItemMapper = contaItemMapper;
        this.enderecoItemMapper = enderecoItemMapper;
        this.titularItemMapper = titularItemMapper;
        this.client = client;
    }

    public Conta abrir(Conta conta) {
        var contaItem = paraContaItem(conta);
        var titularItem = paraTitularItem(conta.getTitular(), contaItem.getId());
        var enderecoItem = paraEnderecoItem(conta.getEndereco(), titularItem.getId());

        persistirDados(contaItem, titularItem, enderecoItem);

        return contaItem.paraConta(titularItem.paraTitular(enderecoItem), contaItem.getId());
    }

    private ContaItem paraContaItem(Conta conta) {
        var contaItem = contaItemMapper.deConta(conta);
        contaItem.gerarId();
        return contaItem;
    }

    private TitularItem paraTitularItem(Titular titular, String idConta) {
        var titularItem = titularItemMapper.deTitular(titular);
        titularItem.gerarId();
        titularItem.setContaId(idConta);
        return titularItem;
    }

    private EnderecoItem paraEnderecoItem(Endereco endereco, String titularId) {
        var enderecoItem = enderecoItemMapper.deEndereco(endereco);
        enderecoItem.gerarId();
        enderecoItem.setTitularId(titularId);
        return enderecoItem;
    }

    private void persistirDados(ContaItem contaItem, TitularItem titularItem, EnderecoItem enderecoItem) {
        var contaTabela = client.table(TABLE_NAME, TableSchema.fromClass(ContaItem.class));
        var titularTabela = client.table(TABLE_NAME, TableSchema.fromClass(TitularItem.class));
        var enderecoTabela = client.table(TABLE_NAME, TableSchema.fromClass(EnderecoItem.class));

        client.transactWriteItems(r -> r.addPutItem(contaTabela, contaItem)
                .addPutItem(titularTabela, titularItem)
                .addPutItem(enderecoTabela, enderecoItem));
    }


}
