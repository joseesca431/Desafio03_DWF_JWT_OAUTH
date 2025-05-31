package sv.edu.udb.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import sv.edu.udb.persistence.domain.User;
import sv.edu.udb.persistence.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        return processOAuth2User(oauth2User);
    }

    private OAuth2User processOAuth2User(OAuth2User oauth2User) {
        String githubUsername = oauth2User.getAttribute("login");
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // Si no hay email público, usar el username como email temporal
        if (email == null || email.isEmpty()) {
            email = githubUsername + "@github.local";
        }

        Optional<User> userOptional = userRepository.findByUsername(githubUsername);

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            // Actualizar información si es necesario
            if (!user.getEmail().equals(email)) {
                user.setEmail(email);
                userRepository.save(user);
            }
        } else {
            // Crear nuevo usuario
            user = User.builder()
                    .username(githubUsername)
                    .email(email)
                    .password("") // No password for OAuth users
                    .role("USER")
                    .creationDate(LocalDate.now())
                    .oauthProvider("github")
                    .build();
            userRepository.save(user);
        }

        // Crear un OAuth2User personalizado que incluya la información del usuario
        return new CustomOAuth2User(oauth2User, user);
    }
}
