package com.company.coursesapi.controllers;

import com.company.coursesapi.dao.UsuarioDao;
import com.company.coursesapi.models.Usuario;
import com.company.coursesapi.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Alejandro");
        usuario.setApellido("Dubon");
        usuario.setEmail("adubon@gmail.com");
        usuario.setTelefono("30254152");
        return usuario;
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        String usuarioId = jwtUtil.getKey(token);
        if (usuarioId == null){
            return new ArrayList<>();
        }

        return usuarioDao.getUsuarios();
    }



    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public String registrarUsuario(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(7, 1024, 2, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrarUsuario(usuario);
        return "Usuario creado con exito";
    }

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public String deleteUsuario(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        String usuarioId = jwtUtil.getKey(token);
        if (usuarioId == null){
            return "Token invalido";
        }
        usuarioDao.deleteUsuario(id);
        return "Usuario " + id + " eliminado con exito";
    }

}
