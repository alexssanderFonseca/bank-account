package br.com.alexsdm.conta.abrirconta.mapper;

import br.com.alexsdm.conta.abrirconta.dto.AberturaContaEnderecoDTO;
import br.com.alexsdm.conta.domain.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEndereco(AberturaContaEnderecoDTO aberturaContaEnderecoDTO);

}
