package sv.edu.udb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.request.AuthRequest;
import sv.edu.udb.controller.request.UserRequest;
import sv.edu.udb.controller.response.AuthResponse;
import sv.edu.udb.controller.response.UserResponse;
import sv.edu.udb.service.AuthService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        String username = authRequest.getUsername();
        String token = authService.authenticateUser(authRequest);

        AuthResponse response = new AuthResponse(username, token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public UserResponse register(@Valid @RequestBody UserRequest userRequest) {
        return authService.registerUser(userRequest);
    }
}