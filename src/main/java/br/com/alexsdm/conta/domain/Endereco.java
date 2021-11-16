package br.com.alexsdm.conta.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Endereco {

    private String enderecoId;
    private String principal;
    private String logradouro;
    private String bairro;
    private String cep;
    private String pais;

    public void definirComoPrincipal() {
        this.principal = Boolean.TRUE.toString();
    }


}
