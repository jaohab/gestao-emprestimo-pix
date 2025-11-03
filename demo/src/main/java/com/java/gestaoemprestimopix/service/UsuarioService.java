package com.java.gestaoemprestimopix.service;

import com.java.gestaoemprestimopix.dto.Request.UsuarioRequest;
import com.java.gestaoemprestimopix.dto.Response.UsuarioResponse;
import com.java.gestaoemprestimopix.entity.Usuario;
import com.java.gestaoemprestimopix.enums.StatusUsuario;
import com.java.gestaoemprestimopix.exception.EntityNotFoundException;
import com.java.gestaoemprestimopix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Cadastro de novo usuário
     */
    @Transactional
    public UsuarioResponse cadastrarUsuario(UsuarioRequest request) {

        if (request.getCpf() == null || request.getCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório para o cadastro.");
        }

        if (usuarioRepository.existsByCpf(request.getCpf())) {
            throw new IllegalArgumentException("Já existe um usuário com este CPF.");
        }
        if (request.getEmail() != null && usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }

        Usuario usuario = new Usuario();
        usuario.setCpf(request.getCpf());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setDataCadastro(LocalDate.now());
        usuario.setStatus(StatusUsuario.ATIVO);

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponse(salvo);
    }

    /**
     * Autenticação de usuário (por CPF ou e-mail)
     */
    @Transactional(readOnly = true)
    public UsuarioResponse autenticarUsuario(UsuarioRequest request) {
        String login = request.getEmail() != null ? request.getEmail() : request.getCpf();

        Usuario usuario = (login.contains("@")
                ? usuarioRepository.findByEmail(login)
                : usuarioRepository.findByCpf(login))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o login informado."));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta. Verifique suas credenciais.");
        }

        if (usuario.getStatus() == StatusUsuario.DELETADO) {
            throw new IllegalStateException("Esta conta foi deletada e não pode ser acessada.");
        }

        if (usuario.getStatus() == StatusUsuario.SUSPENSO) {
            throw new IllegalStateException("Esta conta está suspensa temporariamente.");
        }

        return toResponse(usuario);
    }

    /**
     * Atualização de cadastro
     */
    @Transactional
    public UsuarioResponse atualizarUsuario(UUID id, UsuarioRequest request) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));

        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            if (usuarioRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
            }
            usuarioExistente.setEmail(request.getEmail());
        }

        if (request.getSenha() != null && !request.getSenha().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(request.getSenha()));
        }

        Usuario atualizado = usuarioRepository.save(usuarioExistente);
        return toResponse(atualizado);
    }

    /**
     * Listar todos os usuários
     */
    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Buscar usuário por ID
     */
    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));
        return toResponse(usuario);
    }

    /**
     * "Deletar" usuário (altera status para DELETADO)
     */
    @Transactional
    public void deletarUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));

        if (usuario.getStatus() == StatusUsuario.DELETADO) {
            return;
        }

        usuario.setStatus(StatusUsuario.DELETADO);
        usuarioRepository.save(usuario);
    }

    /**
     * Suspender usuário (altera status para SUSPENSO)
     */
    @Transactional
    public void suspenderUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));

        if (usuario.getStatus() == StatusUsuario.SUSPENSO) {
            return;
        }

        usuario.setStatus(StatusUsuario.SUSPENSO);
        usuarioRepository.save(usuario);
    }

    // --- Conversão interna (Entity → Response DTO)
    private UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setCpf(usuario.getCpf());
        response.setEmail(usuario.getEmail());
        response.setDataCadastro(usuario.getDataCadastro());
        response.setStatus(usuario.getStatus());
        return response;
    }
}