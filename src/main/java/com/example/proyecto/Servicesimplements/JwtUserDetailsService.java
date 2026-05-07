package com.example.proyecto.Servicesimplements;

import com.example.proyecto.Entities.Usuario;
import com.example.proyecto.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario user = repo.findByCorreoUsuario(correo);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no existe: " + correo);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        if (user.getRoles() != null) {
            user.getRoles().forEach(rol -> {
                roles.add(new SimpleGrantedAuthority(rol.getRol()));
            });
        }

        return new org.springframework.security.core.userdetails.User(
                user.getCorreoUsuario(),
                user.getContrasenaUsuario(),
                user.getEnabled(), // enabled
                true,              // accountNonExpired
                true,              // credentialsNonExpired
                true,              // accountNonLocked
                roles              // <--- Aquí es donde Spring lee los permisos
        );
    }
}
