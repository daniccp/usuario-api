package dlp.usuarioapi.exceptions.handler;

import dlp.usuarioapi.dto.response.ErroResponse;
import dlp.usuarioapi.exceptions.BadRequestException;
import dlp.usuarioapi.exceptions.DuplicadoException;
import dlp.usuarioapi.exceptions.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - Recurso não encontrado
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 400 - Erros de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidacao(MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName;

            if (error instanceof org.springframework.validation.FieldError) {
                fieldName = ((org.springframework.validation.FieldError) error).getField();
            } else {
                fieldName = error.getObjectName();
            }
            erros.put(fieldName, error.getDefaultMessage());
        });

        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Existem campos inválidos.",
                erros
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(DuplicadoException.class)
    public ResponseEntity<ErroResponse> handleDuplicado(DuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Recurso duplicado",
                ex.getMessage()
        ));
    }

    // 500 - Erros genéricos
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGeral(Exception ex) {
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroResponse> handleBadRequest(BadRequestException ex) {
        ErroResponse erro = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}

