package br.com.alexsdm.conta.funcionalidades.abrirconta.service;


import br.com.alexsdm.conta.dominio.Conta;
import br.com.alexsdm.conta.funcionalidades.abrirconta.dao.AberturaContaDAO;
import br.com.alexsdm.conta.funcionalidades.abrirconta.dto.AberturaContaDTO;
import br.com.alexsdm.conta.funcionalidades.abrirconta.mapper.ContaMapper;
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
    private final ContaMapper contaMapper;
    private final SnsClient snsClient;
    @Value("${aws-sns-arn}")
    private String snsArn;
    private AberturaContaDTO aberturaContaDTO;

    public String executar(AberturaContaDTO aberturaContaDTO) {
        this.aberturaContaDTO = aberturaContaDTO;
        var conta = deContaDTO();
        conta.setNumero(getNumeroConta());
        var contaAberta = aberturaContaDAO.abrir(conta);
        enviarNotificacao(contaAberta);
        return contaAberta.getId();

    }

    private Conta deContaDTO() {
        return contaMapper.toConta(aberturaContaDTO);
    }


    private void enviarNotificacao(Conta conta) {
        var publish = PublishRequest.builder()
                .message(conta.toString())
                .topicArn(snsArn)
                .build();
        snsClient.publish(publish);
    }

    private String getNumeroConta() {
        numeroContaService.gerarNovoNumeroConta();
        return numeroContaService.getConta();
    }

}
