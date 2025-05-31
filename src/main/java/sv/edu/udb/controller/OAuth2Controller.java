package sv.edu.udb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/oauth2")
public class OAuth2Controller {

    @GetMapping("/authorization/github")
    public Map<String, String> getGitHubAuthUrl() {
        return Map.of(
                "authUrl", "/oauth2/authorization/github",
                "message", "Redirect to this URL to start GitHub OAuth flow"
        );
    }
}
