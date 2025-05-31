package sv.edu.udb.service;

import sv.edu.udb.controller.request.AuthRequest;
import sv.edu.udb.controller.request.UserRequest;
import sv.edu.udb.controller.response.UserResponse;

public interface AuthService {

    String authenticateUser(final AuthRequest authRequest);
    UserResponse registerUser(UserRequest userRequest);

}
