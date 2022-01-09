package br.com.alexsdm.conta.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Titular {

    private String id;
    private String email;
    private String nomeCompleto;
    private String dataNascimento;
    private String cpf;
    private Endereco endereco;

}
