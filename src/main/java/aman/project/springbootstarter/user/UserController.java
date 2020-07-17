package aman.project.springbootstarter.user;
import java.util.List;
import java.util.NoSuchElementException;

import aman.project.springbootstarter.user.model.UserAccounts;
import aman.project.springbootstarter.user.model.UserRequest;
import aman.project.springbootstarter.user.model.UserResponse;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	@ApiOperation(value = "Get users",
				  notes = "Hit the api to get the list of all the users")
	public List<UserResponse> getAllUsers() throws Exception {
		return userService.getAllUsers();
	}


	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
	@ApiOperation(value = "Get user by id",
			notes = "Provide user id to get information about the user")
	public UserResponse getUser(@PathVariable Integer id)
	{
		return userService.getUser(id);
	}


	@RequestMapping(method = RequestMethod.POST, value = "/users")
	@ApiOperation(value = "Add user",
			notes = "Add details of the user")
	public void addUser(@RequestBody UserRequest userRequest) throws Exception {
		userService.addUser(userRequest);
	}



	@RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
	@ApiOperation(value = "Update user",
			notes = "Provide user id to update the information about the user")
	public void updateUser(@RequestBody UserRequest userRequest, @PathVariable Integer id) {

		userService.updateUser(id, userRequest);
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	@ApiOperation(value = "Delete user",
			notes = "Provide user id to delete the user")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}



	@RequestMapping(method = RequestMethod.GET, value = "/users/{id}/account")
	@ApiOperation(value = "Get accounts of a user",
			notes = "Provide user id to get details of the bank accounts associated with him/her")
	public List<UserAccounts> findAccountsByUserId(@PathVariable Integer id) throws Exception {
		return userService.findAccountsByUserId(id);
	}
}

