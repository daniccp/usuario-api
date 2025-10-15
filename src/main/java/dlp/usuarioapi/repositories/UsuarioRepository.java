package dlp.usuarioapi.repositories;

import dlp.usuarioapi.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    List<Usuario> findByNomeContainingIgnoreCase(String parteNome);
}
