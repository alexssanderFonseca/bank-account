package br.com.alexsdm.conta.detalhesconta.service;

import br.com.alexsdm.conta.detalhesconta.DetalheTitularDAO;
import br.com.alexsdm.conta.domain.Titular;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecuperaTitularService {

    private final DetalheTitularDAO detalheTitularDAO;

    public List<Titular> executar(String id) {
        return detalheTitularDAO.get(id);
    }
}
