package sv.edu.udb;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Para generar una contraseña encriptada con bcrypt
 */
public class BCryptGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "adminFernando";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Contraseña encriptada:");
        System.out.println(encodedPassword);
    }
}