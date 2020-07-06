package aman.project.springbootstarter.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class    UserService {

    @Autowired
    private UserRepository userRepository;

    /*private List<User> users = new ArrayList<>(Arrays.asList(
            new User("100", "aman", "23", 123012),
            new User("101", "sid", "23", 385295)
    ));*/
    //@JsonIgnore
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
        //users.add(user);
    }

    public void updateUser(Integer id, User user) {
        userRepository.save(user);

    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
