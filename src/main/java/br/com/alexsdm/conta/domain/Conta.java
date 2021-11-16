package br.com.alexsdm.conta.domain;

import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@AllArgsConstructor
public class Conta {

    private String id;

    private static final String AGENCIA = "0001";

    private String numero;

    private static final String CODIGO_BANCO = "777";

    @NotNull
    @Length(min = 4, max = 4)
    private String senha;

    private Long momentoCadastro;


    public Conta(String senha, String numero) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.numero = numero;
        this.momentoCadastro = Instant.now().toEpochMilli();
    }

}
