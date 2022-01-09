package br.com.alexsdm.conta.funcionalidades.detalhe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalheContaDTO {

    private String numero;
    private String agencia;
    private String banco;
    private DetalheContaTitularDTO titular;

}
