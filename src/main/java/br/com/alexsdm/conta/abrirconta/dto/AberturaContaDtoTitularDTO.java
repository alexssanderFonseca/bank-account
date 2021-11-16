package br.com.alexsdm.conta.abrirconta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AberturaContaDtoTitularDTO {
    private String nomeCompleto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    private String cpf;
    private AberturaContaEnderecoDTO enderecoPrincipal;
}
