package dlp.usuarioapi.repositories;

import dlp.usuarioapi.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

   Optional<Usuario> findByCpf(String cpf);

   Iterable<Usuario>findByNomeContainingIgnoreCase(String parteNome);

    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);


}
