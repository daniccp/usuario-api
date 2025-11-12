package dlp.usuarioapi.service;

import dlp.usuarioapi.dto.request.UsuarioRequest;
import dlp.usuarioapi.dto.response.UsuarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioResponse criar(UsuarioRequest usuarioRequest);

    UsuarioResponse atualizarUsuario(Long id, UsuarioRequest usuarioRequest);

    List<UsuarioResponse> listarUsuario();

    Page<UsuarioResponse> listarUsuarioPorPagina(Pageable page);

    List<UsuarioResponse> buscarPorNome(String parteNome);

    Optional<UsuarioResponse> buscarPorId(Long id);

    void excluirUsuario(Long id);


}
