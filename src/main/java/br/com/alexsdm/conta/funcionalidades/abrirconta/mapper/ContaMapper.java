package br.com.alexsdm.conta.funcionalidades.abrirconta.mapper;

import br.com.alexsdm.conta.dominio.Conta;
import br.com.alexsdm.conta.funcionalidades.abrirconta.dto.AberturaContaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    Conta toConta(AberturaContaDTO aberturaContaDto);
}
