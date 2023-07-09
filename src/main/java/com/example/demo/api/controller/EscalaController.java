package com.example.demo.api.controller;

import com.example.demo.api.dto.EscalaDTO;
import com.example.demo.exception.RegraNegocioException;
import com.example.demo.model.entity.Escala;
import com.example.demo.service.EscalaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/escalas")
@RequiredArgsConstructor
@CrossOrigin

public class EscalaController {

    private final EscalaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Escala> escalas = service.getEscalas();
        return ResponseEntity.ok(escalas.stream().map(EscalaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Escala> escala = service.getEscalaById(id);
        if (!escala.isPresent()) {
            return new ResponseEntity("Escala não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(escala.map(EscalaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody EscalaDTO dto) {
        try {
            Escala escala = converter(dto);
            escala = service.salvar(escala);
            return new ResponseEntity(escala, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody EscalaDTO dto) {
        if (!service.getEscalaById(id).isPresent()) {
            return new ResponseEntity("Escala não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Escala escala = converter(dto);
            escala.setId(id);
            service.salvar(escala);
            return ResponseEntity.ok(escala);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Escala> escala = service.getEscalaById(id);
        if (!escala.isPresent()) {
            return new ResponseEntity("Escala não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(escala.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Escala converter(EscalaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Escala escala = modelMapper.map(dto, Escala.class);

        return escala;
    }


}
