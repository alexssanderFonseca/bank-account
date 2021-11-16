package br.com.alexsdm.conta.abrirconta.service;


import br.com.alexsdm.conta.abrirconta.dao.AberturaContaDAO;
import br.com.alexsdm.conta.abrirconta.dto.AberturaContaDTO;
import br.com.alexsdm.conta.abrirconta.mapper.EnderecoMapper;
import br.com.alexsdm.conta.abrirconta.mapper.TitularMapper;
import br.com.alexsdm.conta.domain.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AberturaContaService {

    private final AberturaContaDAO aberturaContaDAO;
    private final NumeroContaService numeroContaService;
    private final TitularMapper titularMapper;
    private final EnderecoMapper enderecoMapper;

    public void executar(AberturaContaDTO aberturaContaDto) {
        var titular = titularMapper.toTitular(aberturaContaDto.getTitular());
        var endereco = enderecoMapper.toEndereco(aberturaContaDto.getEnderecoDTO());
        endereco.definirComoPrincipal();
        var conta = new Conta(aberturaContaDto.getSenha(), getNumeroConta());

        aberturaContaDAO.abrir(conta, titular, endereco);
    }

    private String getNumeroConta() {
        numeroContaService.gerarNovoNumeroConta();
        return numeroContaService.getConta();
    }

}
