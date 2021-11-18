package br.com.alexsdm.conta.detalhesconta.controller;


import br.com.alexsdm.conta.detalhesconta.service.RecuperaTitularService;
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

    private final RecuperaTitularService recuperaTitularService;

    @GetMapping("/{id}/titulares")
    public ResponseEntity<?> getTitular(@PathVariable String id) {
        return ResponseEntity.ok(recuperaTitularService.executar(id));
    }
}
