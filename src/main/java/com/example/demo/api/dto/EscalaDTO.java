package com.example.demo.api.dto;

import com.example.demo.model.entity.Escala;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscalaDTO {
    private Long id;
    private String titulo;
    private String dataInicio;
    private String dataFim;
    private Integer qtdDias;

    public static EscalaDTO create(Escala escala) {
        ModelMapper modelMapper = new ModelMapper();
        EscalaDTO dto = modelMapper.map(escala, EscalaDTO.class);

        if (dto.getDataInicio() != null && dto.getQtdDias() != null) {
            LocalDate inicio = LocalDate.parse(dto.getDataInicio(), DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate fim = inicio.plusDays(dto.getQtdDias());
            dto.setDataFim(fim.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        return dto;
    }
}


