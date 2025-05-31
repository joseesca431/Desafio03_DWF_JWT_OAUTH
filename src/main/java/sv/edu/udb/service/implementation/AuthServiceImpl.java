package sv.edu.udb.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sv.edu.udb.controller.request.AuthRequest;
import sv.edu.udb.controller.request.UserRequest;
import sv.edu.udb.controller.response.UserResponse;
import sv.edu.udb.persistence.domain.User;
import sv.edu.udb.persistence.repository.UserRepository;
import sv.edu.udb.security.JwtUtil;
import sv.edu.udb.service.AuthService;
import sv.edu.udb.service.mapper.UserMapper;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public String authenticateUser(final AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return jwtUtil.generateToken(userDetails);

        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(UNAUTHORIZED, "Credenciales incorrectas");
        }
    }

    public UserResponse registerUser(UserRequest userRequest) {
        // Validar si el usuario ya existe
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("El email ya existe");
        }

        // Crear nuevo usuario
        User user = User.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .role("USER")
                .creationDate(LocalDate.now())
                .build();

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}