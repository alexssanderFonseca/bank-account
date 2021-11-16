package br.com.alexsdm.conta.compartilhado.database.mapper;

import br.com.alexsdm.conta.compartilhado.database.item.TitularItem;
import br.com.alexsdm.conta.domain.Titular;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TitularItemMapper {

    TitularItem deTitular(Titular titular);
}
