package dlp.usuarioapi.controller;

import dlp.usuarioapi.dto.request.UsuarioRequest;
import dlp.usuarioapi.dto.response.UsuarioResponse;
import dlp.usuarioapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

     @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request
    ) {
        UsuarioResponse response = usuarioService.atualizarUsuario(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.listarUsuario();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/pagina/{numeroPagina}/{qtdePagina}")
    public ResponseEntity<Page<UsuarioResponse>> listarUsuariosPorPagina(
            @PathVariable int numeroPagina,
            @PathVariable int qtdePagina
    ) {
        if (qtdePagina > 5) qtdePagina = 5;
        Pageable page = PageRequest.of(numeroPagina, qtdePagina);
        Page<UsuarioResponse> pagina = usuarioService.listarUsuarioPorPagina(page);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/nome/{parteNome}")
    public ResponseEntity<List<UsuarioResponse>> buscarPorNome(@PathVariable String parteNome) {
        List<UsuarioResponse> usuarios = (List<UsuarioResponse>) usuarioService.buscarPorNome(parteNome);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponse>> buscarPorId(@PathVariable Long id) {
        Optional<UsuarioResponse> usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
