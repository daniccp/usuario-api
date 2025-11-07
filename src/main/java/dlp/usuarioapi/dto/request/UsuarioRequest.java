package dlp.usuarioapi.dto.request;

import dlp.usuarioapi.dto.UsuarioDto;
import lombok.Data;

@Data
public class UsuarioRequest extends UsuarioDto {

    // aqui so vai ter os atributos que eu necessito que o usuario me informa, sendo que,  ele sempre vai ser criado
    //como ativo e a data e hora de criação eu tbm vou saber o momento que estiver criando ou alterando
}
