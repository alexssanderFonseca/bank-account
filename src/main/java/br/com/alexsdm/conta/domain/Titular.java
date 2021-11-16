package br.com.alexsdm.conta.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Titular {

    private String id;
    private String nomeCompleto;
    private String dataNascimento;
    private String cpf;
}
