package dlp.usuarioapi.dto.response;

import dlp.usuarioapi.dto.UsuarioDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class UsuarioResponse extends UsuarioDto {

    //Eu quero que retorne tudo, inclusive ID menos a senha.

        private boolean ativo;
        private LocalDateTime dataCriacao;


    }

