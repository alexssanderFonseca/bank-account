package br.com.alexsdm.conta.abrirconta.dto;

import lombok.Getter;

@Getter
public class AberturaContaDTO {
    private AberturaContaDtoTitularDTO titular;
    private String senha;

    public AberturaContaEnderecoDTO getEnderecoDTO() {
        return titular.getEnderecoPrincipal();
    }
}
