package dlp.usuarioapi.controller;

import dlp.usuarioapi.entities.Usuario;
import dlp.usuarioapi.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
//    public @ResponseBody Usuario salvarUsuario(@Valid @RequestBody Usuario usuario) {
//        return usuarioRepository.save(usuario);
    @PostMapping
    public Usuario criarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable int id, @Valid @RequestBody Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }

        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }


    @GetMapping
    public Iterable<Usuario> obterUsuario() {
        return usuarioRepository.findAll();
    }

    @GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
    public Page<Usuario> obterUsuarioPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina) {
        if (qtdePagina > 5) qtdePagina = 5;
        Pageable page = PageRequest.of(numeroPagina, qtdePagina);
        return usuarioRepository.findAll(page);
    }

    @GetMapping(path = "/nome/{parteNome}")
    public Iterable<Usuario> obterUsuarioPorNome(@PathVariable String parteNome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(parteNome);
    }

    @GetMapping(path = "/{id}")
    public Optional<Usuario> obterUsuarioPorId(@PathVariable int id) {
        return usuarioRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void excluirUsuario(@PathVariable int id) {
        usuarioRepository.deleteById(id);
    }
}


