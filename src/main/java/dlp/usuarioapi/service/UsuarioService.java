package dlp.usuarioapi.service;

import dlp.usuarioapi.entities.Usuario;
import dlp.usuarioapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarOuBuscarUsuario(Usuario usuario) {
        Optional<Usuario> existente = usuarioRepository.findByCpf(usuario.getCpf());

        if (existente.isPresent()) {
            return existente.get();
        }

        return usuarioRepository.save(usuario);
    }
}


