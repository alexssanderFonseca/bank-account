package br.com.alexsdm.conta.abrirconta.service;


import br.com.alexsdm.conta.abrirconta.dao.AberturaContaDAO;
import br.com.alexsdm.conta.abrirconta.dto.AberturaContaDTO;
import br.com.alexsdm.conta.abrirconta.mapper.EnderecoMapper;
import br.com.alexsdm.conta.abrirconta.mapper.TitularMapper;
import br.com.alexsdm.conta.domain.Conta;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
@RequiredArgsConstructor
public class AberturaContaService {

    private final AberturaContaDAO aberturaContaDAO;
    private final NumeroContaService numeroContaService;
    private final TitularMapper titularMapper;
    private final EnderecoMapper enderecoMapper;
    private final SnsClient snsClient;
    @Value("${aws-sns-arn}")
    private String snsArn;

    public void executar(AberturaContaDTO aberturaContaDto) {
        var titular = titularMapper.toTitular(aberturaContaDto.getTitular());
        var endereco = enderecoMapper.toEndereco(aberturaContaDto.getEnderecoDTO());
        var conta = new Conta(aberturaContaDto.getSenha(), getNumeroConta());
        endereco.definirComoPrincipal();
        var dadosConta = aberturaContaDAO.abrir(conta, titular, endereco);
        enviarNotificacao(dadosConta);

    }

    private void enviarNotificacao(Map<String, Object> dadosConta) {
        var publish = PublishRequest.builder()
                .message(dadosConta.toString())
                .topicArn(snsArn)
                .build();
        snsClient.publish(publish);
    }

    private String getNumeroConta() {
        numeroContaService.gerarNovoNumeroConta();
        return numeroContaService.getConta();
    }

}
