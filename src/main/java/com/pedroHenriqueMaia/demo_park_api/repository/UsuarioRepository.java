package com.pedroHenriqueMaia.demo_park_api.repository;

import com.pedroHenriqueMaia.demo_park_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}