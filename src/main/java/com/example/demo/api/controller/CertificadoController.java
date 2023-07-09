package com.example.demo.api.controller;

import com.example.demo.api.dto.CertificadoDTO;
import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Certificado;
import com.example.demo.service.CertificadoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/certificados")
@RequiredArgsConstructor
@CrossOrigin

public class CertificadoController {

    private final CertificadoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Certificado> certificados = service.getCertificados();
        return ResponseEntity.ok(certificados.stream().map(CertificadoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Certificado> certificado = service.getCertificadoById(id);
        if (!certificado.isPresent()) {
            return new ResponseEntity("Certificado não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(certificado.map(CertificadoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CertificadoDTO dto) {
        try {
            Certificado certificado = converter(dto);
            certificado = service.salvar(certificado);
            return new ResponseEntity(certificado, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CertificadoDTO dto) {
        if (!service.getCertificadoById(id).isPresent()) {
            return new ResponseEntity("Certificado não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Certificado certificado = converter(dto);
            certificado.setId(id);
            service.salvar(certificado);
            return ResponseEntity.ok(certificado);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Certificado> certificado = service.getCertificadoById(id);
        if (!certificado.isPresent()) {
            return new ResponseEntity("Certificado não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(certificado.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Certificado converter(CertificadoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Certificado certificado = modelMapper.map(dto, Certificado.class);

        return certificado;
    }


}
