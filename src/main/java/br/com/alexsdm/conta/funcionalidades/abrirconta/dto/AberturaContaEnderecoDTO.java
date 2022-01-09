package br.com.alexsdm.conta.funcionalidades.abrirconta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AberturaContaEnderecoDTO {
    private String logradouro;
    private String bairro;
    private String cep;
    private String pais;
    private String cidade;
}
