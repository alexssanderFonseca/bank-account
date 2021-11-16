package br.com.alexsdm.conta.compartilhado.database.mapper;

import br.com.alexsdm.conta.compartilhado.database.item.ContaItem;
import br.com.alexsdm.conta.domain.Conta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaItemMapper {

    ContaItem deConta(Conta conta);

}
