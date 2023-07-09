package com.example.demo.service;

import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Escala;
import com.example.demo.model.repository.EscalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class EscalaService {

    private EscalaRepository repository;

    public EscalaService(EscalaRepository repository) {
        this.repository = repository;
    }

    public List<Escala> getEscalas() {
        return repository.findAll();
    }

    public Optional<Escala> getEscalaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Escala salvar(Escala escala) {
        validar(escala);
        return repository.save(escala);
    }

    @Transactional
    public void excluir(Escala escala) {
        Objects.requireNonNull(escala.getId());
        repository.delete(escala);
    }

    public void validar(Escala escala) {
        if (escala.getDataInicio() == null || escala.getDataFim() == null) {
            throw new RegraNegocioException("Data inválida");
        }
        if (escala.getQtdDias() == null) {
            throw new RegraNegocioException("Quantidade de dias inválida");
        }
    }
}
