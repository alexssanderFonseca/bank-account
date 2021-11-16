package br.com.alexsdm.conta.compartilhado.database.mapper;

import br.com.alexsdm.conta.compartilhado.database.item.EnderecoItem;
import br.com.alexsdm.conta.domain.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoItemMapper {

    EnderecoItem deEndereco(Endereco endereco);
}
