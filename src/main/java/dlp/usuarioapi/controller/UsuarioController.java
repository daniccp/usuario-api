package dlp.usuarioapi.controller;

import dlp.usuarioapi.entities.Usuario;
import dlp.usuarioapi.service.UsuarioService;
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
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario criarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.criar(usuario);
    }


    // os paramentros de entrada será o usuario request
    //o retorno do metodo será usuario response

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable int id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @GetMapping
    public Iterable<Usuario> obterUsuario() {
        return usuarioService.listarUsuario();
    }

    @GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
    public Page<Usuario> obterUsuarioPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdePagina) {
        if (qtdePagina > 5) qtdePagina = 5;
        Pageable page = PageRequest.of(numeroPagina, qtdePagina);
        return usuarioService.listarUsuarioPorPagina(page);
    }

    @GetMapping(path = "/nome/{parteNome}")
    public Iterable<Usuario> obterUsuarioPorNome(@PathVariable String parteNome) {
        return usuarioService.buscarPorNome(parteNome);
    }

    @GetMapping(path = "/{id}")
    public Optional<Usuario> obterUsuarioPorId(@PathVariable int id) {
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public void excluirUsuario(@PathVariable int id) {

        usuarioService.excluirUsuario(id);
    }
}


