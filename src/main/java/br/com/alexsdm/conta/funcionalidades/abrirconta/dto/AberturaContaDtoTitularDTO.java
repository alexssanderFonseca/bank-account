package br.com.alexsdm.conta.funcionalidades.abrirconta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class AberturaContaDtoTitularDTO {
    private String nomeCompleto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @PastOrPresent
    private LocalDate dataNascimento;
    @CPF
    private String cpf;
    private AberturaContaEnderecoDTO endereco;
    @Email
    private String email;
}
