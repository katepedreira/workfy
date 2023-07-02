package com.example.demo.api.dto;

import com.example.demo.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

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
        dto.idCertificado = usuario.getCertificado().getId();
        dto.tituloCertificado = usuario.getCertificado().getTitulo();
        dto.idEscala = usuario.getEscala().getId();
        dto.tituloEscala = usuario.getEscala().getTitulo();
        return dto;
    }


}
