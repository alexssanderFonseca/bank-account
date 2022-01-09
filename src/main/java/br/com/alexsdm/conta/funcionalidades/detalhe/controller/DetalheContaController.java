package br.com.alexsdm.conta.funcionalidades.detalhe.controller;


import br.com.alexsdm.conta.funcionalidades.detalhe.service.RecuperaDetalheConta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class DetalheContaController {

    private final RecuperaDetalheConta recuperaDetalheConta;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTitular(@PathVariable String id) {
        return ResponseEntity.ok(recuperaDetalheConta.executar(id));
    }
}
