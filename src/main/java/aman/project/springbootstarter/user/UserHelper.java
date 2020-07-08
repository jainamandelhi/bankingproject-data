package aman.project.springbootstarter.user;

import aman.project.springbootstarter.user.model.User;
import aman.project.springbootstarter.user.model.UserRequest;
import aman.project.springbootstarter.user.model.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    public UserRequest convertUserRequest(User user)
    {
        UserRequest userRequest = new UserRequest();
        userRequest.setAge(user.getAge());
        userRequest.setUsername(user.getUsername());
        userRequest.setId(user.getId());
        return userRequest;
    }
    public UserResponse convertUserResponse(User user)
    {
        UserResponse userResponse = new UserResponse();
        userResponse.setAge(user.getAge());
        userResponse.setUsername(user.getUsername());
        userResponse.setId(user.getId());
        return userResponse;
    }
}
