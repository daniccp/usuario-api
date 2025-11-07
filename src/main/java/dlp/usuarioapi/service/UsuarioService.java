package dlp.usuarioapi.service;

import dlp.usuarioapi.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {

    Usuario criar(Usuario usuario);

    Usuario atualizarUsuario(int id, Usuario usuario);

    Iterable<Usuario> listarUsuario();

    Page<Usuario> listarUsuarioPorPagina(Pageable pageable);

    Iterable<Usuario> buscarPorNome(String parteNome);

    Optional<Usuario> buscarPorId(int id);

    void excluirUsuario(int id);

}