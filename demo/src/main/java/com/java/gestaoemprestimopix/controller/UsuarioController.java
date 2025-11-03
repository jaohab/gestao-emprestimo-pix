package com.java.gestaoemprestimopix.controller;

import com.java.gestaoemprestimopix.dto.Request.UsuarioRequest;
import com.java.gestaoemprestimopix.dto.Response.UsuarioResponse;
import com.java.gestaoemprestimopix.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // --- CADASTRAR USUÁRIO ---
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse novoUsuario = usuarioService.cadastrarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // --- AUTENTICAR USUÁRIO (login) ---
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> autenticarUsuario(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse autenticado = usuarioService.autenticarUsuario(request);
        return ResponseEntity.ok(autenticado);
    }

    // --- ATUALIZAR CADASTRO ---
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable UUID id,
            @Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse atualizado = usuarioService.atualizarUsuario(id, request);
        return ResponseEntity.ok(atualizado);
    }

    // --- BUSCAR TODOS ---
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // --- BUSCAR POR ID ---
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    // --- DELETAR ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}