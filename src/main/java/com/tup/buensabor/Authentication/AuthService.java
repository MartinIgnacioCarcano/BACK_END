package com.tup.buensabor.Authentication;

import com.tup.buensabor.entities.Usuario;
import com.tup.buensabor.repositories.UsuarioRepository;
import com.tup.buensabor.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            UserDetails usuario = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
            System.out.println(usuario.getUsername());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (authentication.isAuthenticated()) {
                System.out.println("Tiene token y es: ");
                String token = jwtService.getToken(usuario);
                System.out.println(token);
                return AuthResponse.builder()
                        .token(token)
                        .build();
            } else {
                System.out.println("no tiene token");
                return null;
            }
        } catch (Exception e) {
            System.out.println("problema al traer el usuario: ");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public AuthResponse register(RegisterRequest request) {
        try {
            Usuario usuario = new Usuario(request.getUsername(), passwordEncoder.encode(request.getPassword()));
            System.out.println(usuario.getUsername());
            usuarioRepository.save(usuario);
            String token = jwtService.getToken(usuario);

            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
