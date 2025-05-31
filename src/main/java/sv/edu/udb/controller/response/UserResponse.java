package sv.edu.udb.controller.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String username;

    private String role;

    private String email;

    private LocalDate creationDate;

    // Don't include password in the response for security reasons
}
