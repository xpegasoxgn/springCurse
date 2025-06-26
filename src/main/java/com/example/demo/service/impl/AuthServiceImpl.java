package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.security.JwtUtils;
import com.example.demo.controller.dto.LoginRequest;
import com.example.demo.controller.dto.RegisterRequest;
import com.example.demo.dto.jwt.JwtLoginResponse;
import com.example.demo.model.auth.Rol;
import com.example.demo.model.auth.Sesion;
import com.example.demo.model.auth.Usuarios;
import com.example.demo.repository.auth.RolRepository;
import com.example.demo.repository.auth.SesionRepository;
import com.example.demo.repository.auth.UsuarioRepository;
import com.example.demo.service.AuthService;


@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private JwtUtils jwtUtils;

    //dependencia de spring

    @Autowired
    private PasswordEncoder passwordEncoders;

    @Override
    public JwtLoginResponse login(LoginRequest request){
        Usuarios user = usuarioRepository.findByUsername(request.getUsername())
        .orElseThrow(()-> new org.springframework.security.authentication.BadCredentialsException("usuario no encontrado"));

        System.out.print("password "+request.getPassword());
        BCryptPasswordEncoder  codigo = new BCryptPasswordEncoder ();
        
        System.out.println("codigo : "+codigo.encode(request.getPassword()));
        if(!passwordEncoders.matches(request.getPassword(), user.getPassword())){
            throw  new org.springframework.security.authentication.BadCredentialsException("Credenciales Invalidas");
        }
        String token=jwtUtils.generarToken(user);

        Sesion sesion=new Sesion();
        sesion.setUsuario(user);
        sesion.setToken(token);
        sesion.setFechaGeneracion(LocalDateTime.now());
        sesion.setFechaExpiracion(LocalDateTime.now().plusHours(2));
        sesion.setIp("10.0.12.64");
        sesion.setActivo(true);
        sesionRepository.save(sesion);

        List<String> roles =user.getRoles()
        .stream()
        .map(rol-> rol.getNombre())
        .collect(Collectors.toList());

        return new JwtLoginResponse(token, user.getUsername(),   roles);
    }

    @Override
    public void  register(RegisterRequest request) throws  Exception{

        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw  new Exception("El username ya esta siendo utilizado. Escoja otro");
        }
        Usuarios newUser= new Usuarios();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setNombre(request.getNombre());
        newUser.setPaterno(request.getPaterno());
        newUser.setMaterno(request.getMaterno());

        newUser.setPassword(passwordEncoders.encode(request.getPassword()));
        Set<Rol> roles=new HashSet<>();
        for (String nombreRol :request.getRoles()) {
            Rol rol=rolRepository.findByNombre(nombreRol)
                .orElseThrow(()-> new Exception("Rol no encontrado"+nombreRol));

            roles.add(rol);
            
        }
        newUser.setRoles(roles);
        usuarioRepository.save(newUser);

    }
}




