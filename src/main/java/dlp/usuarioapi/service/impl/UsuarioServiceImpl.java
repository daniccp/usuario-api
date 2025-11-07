package dlp.usuarioapi.service.impl;

import dlp.usuarioapi.entities.Usuario;
import dlp.usuarioapi.exceptions.DuplicadoException;
import dlp.usuarioapi.exceptions.RecursoNaoEncontradoException;
import dlp.usuarioapi.repositories.UsuarioRepository;
import dlp.usuarioapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar ou buscar por número de documento
    @Override
    public Usuario criar(Usuario usuario) {
        Optional<Usuario> existente = buscarPorNumeroDocumentoOuUsuario(usuario.getNumeroDocumento(), usuario.getUsuario());

        if (existente.isPresent()) {
            throw new DuplicadoException("´Número de documento ou Usuario já existe.");
        }
        return usuarioRepository.save(usuario);
    }

    private Optional<Usuario> buscarPorNumeroDocumentoOuUsuario(String numeroDocumento, String usuario) {
        return usuarioRepository.findByNumeroDocumentoOrUsuario(numeroDocumento, usuario);
    }

    // Atualizar usuário existente
    @Override
    public Usuario atualizarUsuario(int id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontratdo com id: " + id);
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    //Listar Todos
    @Override
    public Iterable<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    //Listar por página
    @Override
    public Page<Usuario> listarUsuarioPorPagina(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    //Buscar por parte do nome
    @Override
    public Iterable<Usuario> buscarPorNome(String parteNome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(parteNome);
    }

    //Buscar por ID
    @Override
    public Optional<Usuario> buscarPorId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id);
        }
        return usuario;
    }

    //Excluir por ID
    @Override
    public void excluirUsuario(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com id:" + id);
        }
        usuarioRepository.deleteById(id);
    }
}





