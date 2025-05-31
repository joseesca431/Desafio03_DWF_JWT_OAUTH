package sv.edu.udb.persistence.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "role", length = 20, nullable = false)
    private String role;

    @Column(nullable = false)
    @FutureOrPresent
    private LocalDate creationDate;

    @Column(name = "oauth_provider")
    private String oauthProvider;


    // Asegurar valores válidos
    public void setRole(String role) {
        if (!"ADMIN".equals(role) && !"USER".equals(role)) {
            throw new IllegalArgumentException("Rol inválido: " + role);
        }
        this.role = role;
    }
    // Metodo para verificar si es usuario OAuth
    public boolean isOAuthUser() {
        return oauthProvider != null && !oauthProvider.isEmpty();
    }
}
