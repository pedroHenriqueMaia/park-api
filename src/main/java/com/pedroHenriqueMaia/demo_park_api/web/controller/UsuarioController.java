package com.pedroHenriqueMaia.demo_park_api.web.controller;

import com.pedroHenriqueMaia.demo_park_api.entity.Usuario;
import com.pedroHenriqueMaia.demo_park_api.service.UsuarioService;
import com.pedroHenriqueMaia.demo_park_api.web.dto.UsuarioCreateDto;
import com.pedroHenriqueMaia.demo_park_api.web.dto.UsuarioResponseDto;
import com.pedroHenriqueMaia.demo_park_api.web.dto.UsuarioSenhaDto;
import com.pedroHenriqueMaia.demo_park_api.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getUserById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UsuarioSenhaDto senhadto) {
        Usuario user = usuarioService.editarSenha(id, senhadto.getSenhaAtual(), senhadto.getNovaSenha(), senhadto.getConfirmarSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }

}
