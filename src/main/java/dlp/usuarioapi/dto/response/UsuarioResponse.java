package dlp.usuarioapi.dto.response;

import dlp.usuarioapi.dto.UsuarioDto;
import lombok.Data;

@Data
public class UsuarioResponse extends UsuarioDto {

    //Eu quero que retorne tudo, inclusive ID menos a senha.
}
