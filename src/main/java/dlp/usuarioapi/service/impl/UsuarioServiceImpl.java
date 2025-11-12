package dlp.usuarioapi.service.impl;
import dlp.usuarioapi.dto.request.UsuarioRequest;
import dlp.usuarioapi.dto.response.UsuarioResponse;
import dlp.usuarioapi.entities.Usuario;
import dlp.usuarioapi.exceptions.DuplicadoException;
import dlp.usuarioapi.exceptions.RecursoNaoEncontradoException;
import dlp.usuarioapi.repositories.UsuarioRepository;
import dlp.usuarioapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar a partir de DTO (versão usada pelo controller)
    @Override
    public UsuarioResponse criar(UsuarioRequest request) {
        Optional<Usuario> existente = usuarioRepository.findByNumeroDocumentoOrUsuario(
                request.getNumeroDocumento(), request.getUsuario()
        );

        if (existente.isPresent()) {
            throw new DuplicadoException("Número de documento ou usuário já existe.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setNumeroDocumento(request.getNumeroDocumento());
        usuario.setTipoPessoa(request.getTipoPessoa());
        usuario.setEmail(request.getEmail());
        usuario.setNumeroDeTelefone(request.getNumeroTelefone());
        usuario.setDataDeNascimento(request.getDataDeNascimento());
        usuario.setGenero(request.getGenero());
        usuario.setUsuario(request.getUsuario());
        usuario.setSenha(request.getSenha());
        usuario.setAtivo(true);
        usuario.setDataCriacao(LocalDateTime.now());

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponse(salvo);
    }

    // Atualizar usuário existente
    @Override
    public UsuarioResponse atualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));

        usuario.setNome(request.getNome());
        usuario.setNumeroDocumento(request.getNumeroDocumento());
        usuario.setTipoPessoa(request.getTipoPessoa());
        usuario.setEmail(request.getEmail());
        usuario.setNumeroDeTelefone(request.getNumeroTelefone());
        usuario.setDataDeNascimento(request.getDataDeNascimento());
        usuario.setGenero(request.getGenero());
        usuario.setUsuario(request.getUsuario());
        usuario.setDataAtualizacao(LocalDateTime.now());
        // se quiser permitir troca de senha:
        // usuario.setSenha(request.getSenha());

        usuario = usuarioRepository.save(usuario);
        return toResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> listarUsuario() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UsuarioResponse> listarUsuarioPorPagina(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    public List<UsuarioResponse> buscarPorNome(String parteNome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(parteNome)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioResponse> buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));
        return Optional.of(toResponse(usuario));
    }

    @Override
    public void excluirUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Conversão de entidade → Response DTO
    private UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId((long) usuario.getId());
        response.setNome(usuario.getNome());
        response.setNumeroDocumento(usuario.getNumeroDocumento());
        response.setTipoPessoa(usuario.getTipoPessoa());
        response.setEmail(usuario.getEmail());
        response.setNumeroTelefone(usuario.getNumeroDeTelefone());
        response.setDataDeNascimento(usuario.getDataDeNascimento());
        response.setGenero(usuario.getGenero());
        response.setUsuario(usuario.getUsuario());
        response.setAtivo(usuario.isAtivo());
        response.setDataCriacao(usuario.getDataCriacao());
        return response;
    }
}
