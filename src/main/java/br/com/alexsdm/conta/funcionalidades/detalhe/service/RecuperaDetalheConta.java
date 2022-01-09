package br.com.alexsdm.conta.funcionalidades.detalhe.service;

import br.com.alexsdm.conta.dominio.Conta;
import br.com.alexsdm.conta.dominio.Endereco;
import br.com.alexsdm.conta.dominio.Titular;
import br.com.alexsdm.conta.funcionalidades.detalhe.dao.DetalheContaDAO;
import br.com.alexsdm.conta.funcionalidades.detalhe.dto.DetalheContaDTO;
import br.com.alexsdm.conta.funcionalidades.detalhe.dto.DetalheContaEnderecoDTO;
import br.com.alexsdm.conta.funcionalidades.detalhe.dto.DetalheContaTitularDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperaDetalheConta {

    private final DetalheContaDAO detalheTitularDAO;
    private String contaId;

    public DetalheContaDTO executar(String contaId) {
        this.contaId = contaId;
        var conta = detalheTitularDAO.detalhe(this.contaId);
        return DetalheContaDTO.builder()
                .agencia(Conta.AGENCIA)
                .banco(Conta.CODIGO_BANCO)
                .numero(conta.getNumero())
                .titular(getTitularDTO(conta.getTitular()))
                .build();

    }

    private DetalheContaTitularDTO getTitularDTO(Titular titular) {
        return DetalheContaTitularDTO.builder().nomeCompleto(titular.getNomeCompleto())
                .email(titular.getEmail())
                .endereco(getEnderecoDTO(titular.getEndereco()))
                .build();
    }

    private DetalheContaEnderecoDTO getEnderecoDTO(Endereco endereco) {
        return DetalheContaEnderecoDTO.builder().logradouro(endereco.getLogradouro())
                .bairro(endereco.getBairro())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .build();
    }
}
