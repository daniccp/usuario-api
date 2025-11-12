package dlp.usuarioapi.dto;

import dlp.usuarioapi.validation.TipoPessoaValida;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDto {

    private Long id; // opcional, mas útil para atualizações e retornos

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O documento é obrigatório.")
    private String numeroDocumento;

    @NotBlank(message = "O tipo de pessoa é obrigatório.")
    @TipoPessoaValida // valida PF ou PJ
    private String tipoPessoa; // Ex: "Física" ou "Jurídica"

    @Email(message = "E-mail inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String numeroTelefone;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataDeNascimento;

    @NotBlank(message = "O gênero é obrigatório.")
    private String genero;

    @NotBlank(message = "O nome de usuário é obrigatório.")
    private String usuario;



    public UsuarioDto() {
    }
}

