package aman.project.springbootstarter.user;

import aman.project.springbootstarter.account.AccountRepository;
import aman.project.springbootstarter.account.model.Account;
import aman.project.springbootstarter.account.model.AccountStatement;
import aman.project.springbootstarter.user.model.User;
import aman.project.springbootstarter.user.model.UserAccounts;
import aman.project.springbootstarter.user.model.UserRequest;
import aman.project.springbootstarter.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class    UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private AccountRepository accountRepository;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse>userResponseList = new ArrayList<>();
        for(User user: users)
        {
            UserResponse userResponse = new UserResponse();
            userResponse = userHelper.convertUserResponse(user);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    public UserResponse getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        UserResponse userResponse = new UserResponse();
        userResponse = userHelper.convertUserResponse(user.get());
        return userResponse;
    }

    public void addUser(UserRequest userRequest) {
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setUsername(userRequest.getUsername());
        userRepository.save(user);
        //users.add(user);
    }

    public void updateUser(Integer id, UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setAge(userRequest.getAge());
        userRepository.save(user);
    }

    public void deleteUser(Integer id)
    {
        userRepository.deleteById(id);
    }

    public List<UserAccounts> findAccountsByUserId(Integer id) {
        List<Account>accountList = accountRepository.findByUser(id);
        UserAccounts userAccounts = new UserAccounts();
        List<UserAccounts> userAccountsList = new ArrayList<>();
        for(Account account: accountList)
        {
            userAccounts.setAccountId(account.getId());
            userAccounts.setAccountType(account.getAccountType());
            userAccountsList.add(userAccounts);
        }
        return userAccountsList;
    }
}
