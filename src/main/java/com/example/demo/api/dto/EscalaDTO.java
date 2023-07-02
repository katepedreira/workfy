package com.example.demo.api.dto;

import com.example.demo.model.entity.Escala;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EscalaDTO {
    private Long id;
    private String titulo;
    private Date dataInicio;
    private Date dataFim;
    private Integer qtdDias;

    public static EscalaDTO create(Escala escala) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(escala, EscalaDTO.class);
    }

}
