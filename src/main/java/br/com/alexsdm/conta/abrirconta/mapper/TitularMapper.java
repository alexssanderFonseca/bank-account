package br.com.alexsdm.conta.abrirconta.mapper;

import br.com.alexsdm.conta.abrirconta.dto.AberturaContaDtoTitularDTO;
import br.com.alexsdm.conta.domain.Titular;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TitularMapper {

    Titular toTitular(AberturaContaDtoTitularDTO aberturaContaDtoTitularDTO);
}
