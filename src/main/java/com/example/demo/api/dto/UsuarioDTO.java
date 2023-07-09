package com.example.demo.api.dto;

import com.example.demo.model.entity.Certificado;
import com.example.demo.model.entity.Escala;
import com.example.demo.model.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String cpf;

    private Long idCertificado;

    private String tituloCertificado;

    private Long idEscala;

    private String tituloEscala;

    public static UsuarioDTO create(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);

        Optional<Certificado> certificadoOptional = Optional.ofNullable(usuario.getCertificado());
        if (certificadoOptional.isPresent()) {
            Certificado certificado = certificadoOptional.get();
            dto.idCertificado = certificado.getId();
            dto.tituloCertificado = certificado.getTitulo();
        }

        Optional<Escala> escalaOptional = Optional.ofNullable(usuario.getEscala());
        if (escalaOptional.isPresent()) {
            Escala escala = escalaOptional.get();
            dto.idEscala = escala.getId();
            dto.tituloEscala = escala.getTitulo();
        }

        return dto;
    }

}
