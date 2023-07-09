package com.example.demo.api.dto;

import com.example.demo.model.entity.Escala;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.ZoneId;
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
        EscalaDTO dto = modelMapper.map(escala, EscalaDTO.class);

        LocalDate inicio = dto.getDataInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fim = inicio.plusDays(dto.getQtdDias());
        dto.setDataFim(Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return dto;
    }
}

