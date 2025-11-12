package dlp.usuarioapi.dto.request;

import dlp.usuarioapi.dto.UsuarioDto;
import dlp.usuarioapi.validation.DocumentoValido;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@DocumentoValido
@EqualsAndHashCode(callSuper = true)
public class UsuarioRequest extends UsuarioDto {

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

}