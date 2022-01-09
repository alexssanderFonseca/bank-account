package br.com.alexsdm.conta.funcionalidades.abrirconta.controller;

import br.com.alexsdm.conta.funcionalidades.abrirconta.dto.AberturaContaDTO;
import br.com.alexsdm.conta.funcionalidades.abrirconta.service.AberturaContaService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class AbrirContaController {

    private final AberturaContaService aberturaContaService;

    @PostMapping
    public ResponseEntity<?> abrir(@Valid @RequestBody AberturaContaDTO aberturaContaDto, UriComponentsBuilder uriBuilder) {
        var id = aberturaContaService.executar(aberturaContaDto);
        var uri = uriBuilder.path("contas/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.ok().location(uri).build();
    }
}
