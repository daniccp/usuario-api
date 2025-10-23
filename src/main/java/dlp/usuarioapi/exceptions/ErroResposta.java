package dlp.usuarioapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@Data
@AllArgsConstructor
public class ErroResposta {

    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
    private Map<String, String> detalhes;

    public ErroResposta(LocalDateTime timestamp, int status, String erro, String mensagem) {
        this.timestamp = timestamp;
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.detalhes = new HashMap<>();
    }
}


