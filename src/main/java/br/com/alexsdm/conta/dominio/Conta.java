package br.com.alexsdm.conta.dominio;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Conta {

    public static final String AGENCIA = "0001";
    public static final String CODIGO_BANCO = "777";
    private String id;
    private  String numero;
    private final Long momentoCadastro = Instant.now().toEpochMilli();
    private final Titular titular;

    public Endereco getEndereco() {
        return this.titular.getEndereco();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
