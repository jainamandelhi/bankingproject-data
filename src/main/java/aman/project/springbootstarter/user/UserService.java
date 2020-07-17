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
    public List<UserResponse> getAllUsers() throws Exception {
        logger.info("Retrieving list of all users");
        List<User> users = userRepository.findAll();
        List<UserResponse>userResponseList = new ArrayList<>();
        for(User user: users)
        {
            UserResponse userResponse = new UserResponse();
            userResponse = userHelper.convertUserResponse(user);
            userResponseList.add(userResponse);
        }
        if(userResponseList.size() == 0)
            throw new Exception("No user found in the database");
        logger.info("List retrieved");
        logger.info("Total no of users are "+userResponseList.size());
        return userResponseList;
    }

    public UserResponse getUser(Integer id) {
        logger.info("Finding user with given Id");
        UserResponse userResponse = new UserResponse();
        try {
            Optional<User> user = userRepository.findById(id);
            userResponse = userHelper.convertUserResponse(user.get());
            logger.info("User found");
        }
        catch(NoSuchElementException e) {
            logger.info("No such user exists");
        }
        return userResponse;
    }

    public void addUser(UserRequest userRequest) throws Exception {
        String mobileNo = userRequest.getMobileNo();
        Integer age = userRequest.getAge();
        if(mobileNo.length() != 10)
        {
            throw new Exception("Invalid mobile no");
        }
        else if(age < 18)
        {
            throw new Exception("User must be above 18 years of age");
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

    public void updateUser(Integer id, UserRequest userRequest) {
        String mobileNo = userRequest.getMobileNo();
        if(mobileNo.length() != 10)
        {
            throw new ArithmeticException("Invalid mobile no");
        }
        else {
            User user = new User();
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

    public List<UserAccounts> findAccountsByUserId(Integer id) throws Exception {
        logger.info("Searching for the accounts of the user with id: "+id);
        UserAccounts userAccounts = new UserAccounts();
        List<UserAccounts> userAccountsList = new ArrayList<>();
            List<Account>accountList = accountRepository.findByUserId(id);
            if(accountList.size() == 0)
                throw new Exception("No bank account for the given user exists");
            for(Account account: accountList)
            {
                userAccounts.setAccountId(account.getId());
                userAccounts.setAccountType(account.getAccountType());
                userAccountsList.add(userAccounts);
            }
            logger.info("Total Accounts found: "+userAccountsList.size());
        return userAccountsList;
    }
}
