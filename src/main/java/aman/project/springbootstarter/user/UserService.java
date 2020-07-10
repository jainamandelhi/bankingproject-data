package aman.project.springbootstarter.user;

import aman.project.springbootstarter.account.AccountRepository;
import aman.project.springbootstarter.account.model.Account;
import aman.project.springbootstarter.user.model.User;
import aman.project.springbootstarter.user.model.UserAccounts;
import aman.project.springbootstarter.user.model.UserRequest;
import aman.project.springbootstarter.user.model.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public List<UserResponse> getAllUsers() {
        logger.info("Retrieving list of all users");
        List<User> users = userRepository.findAll();
        List<UserResponse>userResponseList = new ArrayList<>();
        for(User user: users)
        {
            UserResponse userResponse = new UserResponse();
            userResponse = userHelper.convertUserResponse(user);
            userResponseList.add(userResponse);
        }
        logger.info("List retrieved");
        return userResponseList;
    }

    public UserResponse getUser(Integer id) {
            logger.info("Finding user with given Id");
            Optional<User> user = userRepository.findById(id);
            UserResponse userResponse = new UserResponse();
            userResponse = userHelper.convertUserResponse(user.get());
            logger.info("User found");
            return userResponse;
    }

    public void addUser(UserRequest userRequest) {
        String mobileNo = userRequest.getMobileNo();
        Integer age = userRequest.getAge();
        if(mobileNo.length() != 10)
        {
            throw new ArithmeticException("Invalid mobile no");
        }
        else if(age < 18)
        {
            throw new ArithmeticException("User must be above 18 years of age");
        }
        else {
            User user = new User();
            user.setAge(age);
            user.setUsername(userRequest.getUsername());
            user.setAddress(userRequest.getAddress());
            user.setIdentityType(userRequest.getIdentityType());
            user.setMobileNo(mobileNo);
            userRepository.save(user);
        }
        //users.add(user);
    }

    public void updateUser(Integer id, UserRequest userRequest) {
        String mobileNo = userRequest.getMobileNo();
        Integer age = userRequest.getAge();
        if(mobileNo.length() != 10)
        {
            throw new ArithmeticException("Invalid mobile no");
        }
        else {
            User user = new User();
            user.setAge(age);
            user.setUsername(userRequest.getUsername());
            user.setAddress(userRequest.getAddress());
            user.setIdentityType(userRequest.getIdentityType());
            user.setMobileNo(mobileNo);
            userRepository.save(user);
        }
    }

    public void deleteUser(Integer id)
    {
        try {
            userRepository.deleteById(id);
            logger.info("User successfully deleted");
        }
        catch(NoSuchElementException e)
        {
            logger.info("No such user exists");
        }
    }

    public List<UserAccounts> findAccountsByUserId(Integer id) {
        logger.info("Searching for the accounts of the user with id: "+id);
        UserAccounts userAccounts = new UserAccounts();
        List<UserAccounts> userAccountsList = new ArrayList<>();
        try {
            List<Account>accountList = accountRepository.findByUser(id);
            for(Account account: accountList)
            {
                userAccounts.setAccountId(account.getId());
                userAccounts.setAccountType(account.getAccountType());
                userAccountsList.add(userAccounts);
            }
            logger.info("Total Accounts found: "+userAccountsList.size());
        }
        catch(NoSuchElementException e) {
            logger.info("No such user exists");
        }
        return userAccountsList;
    }
}
