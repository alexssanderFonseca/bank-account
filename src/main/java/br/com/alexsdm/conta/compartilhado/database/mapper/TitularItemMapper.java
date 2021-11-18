package br.com.alexsdm.conta.compartilhado.database.mapper;

import br.com.alexsdm.conta.compartilhado.database.item.TitularItem;
import br.com.alexsdm.conta.domain.Titular;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TitularItemMapper {

    TitularItem deTitular(Titular titular);

    @Mapping(source = "titularItem", target = "id", qualifiedByName = "id")
    Titular paraTitular(TitularItem titularItem);

    @Named("id")
    default String titularId(TitularItem titularItem) {
        var id = titularItem.getId();
        var indexTralha = id.indexOf("#");
        return id.substring(indexTralha + 1);
    }
}
