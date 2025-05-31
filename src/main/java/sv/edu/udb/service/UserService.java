package sv.edu.udb.service;

import sv.edu.udb.controller.request.UserRequest;
import sv.edu.udb.controller.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse currentUser();

    List<UserResponse> getAllUsers();

    UserResponse createUser(final UserRequest userRequest);

}
