package dlp.usuarioapi.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nome;

    @Column(name = "numero_documento", unique = true, nullable = false)
    @NotBlank
    private String numeroDocumento;

    @Column(name = "tipo_pessoa", nullable = false)
    @NotBlank
    private String tipoPessoa;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String numeroDeTelefone;

    @NotNull
    private LocalDate dataDeNascimento;

    @NotBlank
    private String genero;

    @NotBlank
    @Column(unique = true)
    private String usuario;

    @NotBlank
    private String senha;

    private boolean ativo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    public Usuario() {

    }

    @PrePersist
    protected void onCreate() {
        this.ativo = true; // Sempre começa ativo
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = dataCriacao;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
