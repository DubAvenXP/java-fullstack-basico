package com.company.coursesapi.controllers;

import com.company.coursesapi.dao.UsuarioDao;
import com.company.coursesapi.models.Usuario;
import com.company.coursesapi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
        Usuario userDB = usuarioDao.verificarCredenciales(usuario);

        if(userDB == null){
            return null;
        }

        return jwtUtil.create(String.valueOf(userDB.getId()), userDB.getEmail());
    }
}
