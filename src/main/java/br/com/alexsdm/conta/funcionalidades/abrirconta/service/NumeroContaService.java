package br.com.alexsdm.conta.funcionalidades.abrirconta.service;


import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class NumeroContaService {

    // TODO SUBSITUIR POR CACHE EXTERNO
    private final Map<String, String> numeroContasRegistradas = new HashMap<>();
    private static final String NUM_CONTA_CHAVE = "num_conta";

    public void gerarNovoNumeroConta() {
        var contaAtual = buscaNumeroContasAtual();
        var proximaConta = String.valueOf(Long.parseLong(contaAtual) + 1);
        proximaConta = StringUtils.leftPad(proximaConta, 4, "0");
        setaProxima(proximaConta);
    }

    private String buscaNumeroContasAtual() {
        var numeroAtual = numeroContasRegistradas.get(NUM_CONTA_CHAVE);
        if (this.getConta() == null) {
            numeroAtual = "0";
        }
        return numeroAtual;
    }

    public String getConta() {
        return this.numeroContasRegistradas.get(NUM_CONTA_CHAVE);
    }

    private void setaProxima(String numeroConta) {
        this.numeroContasRegistradas.put(NUM_CONTA_CHAVE, numeroConta);
    }


}
