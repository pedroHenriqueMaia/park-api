package com.pedroHenriqueMaia.demo_park_api.service;

import com.pedroHenriqueMaia.demo_park_api.entity.Usuario;
import com.pedroHenriqueMaia.demo_park_api.exception.EntityNotFoundException;
import com.pedroHenriqueMaia.demo_park_api.exception.UsernameUniqueViolationException;
import com.pedroHenriqueMaia.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    @Transactional
    public Usuario salvar(Usuario usuario){
        try{
            return usuarioRepository.save(usuario);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado.", usuario.getUsername()));
        }

    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Username id=%s não encontrado", id))
        );
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmarSenha){
        if(!novaSenha.equals(confirmarSenha)){
            throw new RuntimeException("Nova senha não confere com confirmação de senha.");
        }

        Usuario user = buscarUsuarioPorId(id);

        if(!user.getPassword().equals(senhaAtual)){
            throw new RuntimeException("Sua senha atual não confere.");
        }

        user.setPassword(novaSenha);
        return user;
    }

}
