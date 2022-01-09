package br.com.alexsdm.conta.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class Endereco {

    private String enderecoId;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;


}
