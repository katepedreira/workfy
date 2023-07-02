package com.example.demo.api.dto;

import com.example.demo.model.entity.Certificado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CertificadoDTO {

    private Long id;
    private String titulo;
    private Date dataEmissao;
    private Date dataVencimento;

    public static CertificadoDTO create(Certificado certificado) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(certificado, CertificadoDTO.class);
    }

}
