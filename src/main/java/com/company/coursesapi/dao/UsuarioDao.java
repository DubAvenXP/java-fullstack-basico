package com.company.coursesapi.dao;


import com.company.coursesapi.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();


    void deleteUsuario(Long id);

    void registrarUsuario(Usuario usuario);

    Usuario verificarCredenciales(Usuario usuario);
}
