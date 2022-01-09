package br.com.alexsdm.conta.funcionalidades.abrirconta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ContaCriadaDTO {
    private String titular;
    private String email;
}
