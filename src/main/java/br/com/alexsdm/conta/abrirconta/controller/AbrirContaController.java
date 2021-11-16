package br.com.alexsdm.conta.abrirconta.controller;

import br.com.alexsdm.conta.abrirconta.dto.AberturaContaDTO;
import br.com.alexsdm.conta.abrirconta.service.AberturaContaService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class AbrirContaController {

    private final AberturaContaService aberturaContaService;

    @PostMapping
    public ResponseEntity<?> abrir(@Valid @RequestBody AberturaContaDTO aberturaContaDto) {
        aberturaContaService.executar(aberturaContaDto);
        return ResponseEntity.ok().build();
    }
}
