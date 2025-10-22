package dlp.usuarioapi.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nome;

    @Column(unique = true)
    @NotBlank
    private String cpf;

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

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String cpf, String email, String numeroDeTelefone, LocalDate dataDeNascimento, String genero, String usuario, String senha, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.numeroDeTelefone = numeroDeTelefone;
        this.dataDeNascimento = dataDeNascimento;
        this.genero = genero;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDeTelefone() {
        return numeroDeTelefone;
    }

    public void setNumeroDeTelefone(String numeroDeTelefone) {
        this.numeroDeTelefone = numeroDeTelefone;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
