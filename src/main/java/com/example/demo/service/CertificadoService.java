package com.example.demo.service;

import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Certificado;
import com.example.demo.model.repository.CertificadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class CertificadoService {

    private CertificadoRepository repository;

    public CertificadoService(CertificadoRepository repository) {
        this.repository = repository;
    }

    public List<Certificado> getCertificados() {
        return repository.findAll();
    }

    public Optional<Certificado> getCertificadoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Certificado salvar(Certificado certificado) {
        validar(certificado);
        return repository.save(certificado);
    }

    @Transactional
    public void excluir(Certificado certificado) {
        Objects.requireNonNull(certificado.getId());
        repository.delete(certificado);
    }

    public void validar(Certificado certificado) {
        if (certificado.getDataEmissao() == null) {
            throw new RegraNegocioException("Data de emissão inválida");
        }
    }

}
