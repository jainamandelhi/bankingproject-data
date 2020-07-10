package aman.project.springbootstarter.user;
import java.util.List;
import java.util.NoSuchElementException;

import aman.project.springbootstarter.user.model.UserAccounts;
import aman.project.springbootstarter.user.model.UserRequest;
import aman.project.springbootstarter.user.model.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@RequestMapping("/users")
	public List<UserResponse> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@RequestMapping("/users/{id}")
	public UserResponse getUser(@PathVariable Integer id)
	{
		UserResponse userResponse = new UserResponse();
		try {
			userResponse = userService.getUser(id);
		}
		catch(NoSuchElementException e) {
			logger.info("No such user exists");
		}
		return userResponse;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public void addUser(@RequestBody UserRequest userRequest)
	{
		userService.addUser(userRequest);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
	public void updateUser(@RequestBody UserRequest userRequest, @PathVariable Integer id) {

		userService.updateUser(id, userRequest);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

	@RequestMapping("/users/{id}/account")
	public List<UserAccounts> findAccountsByUserId(@PathVariable Integer id) {
		return userService.findAccountsByUserId(id);
	}
}

