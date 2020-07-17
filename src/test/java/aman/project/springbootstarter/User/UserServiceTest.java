package aman.project.springbootstarter.User;

import aman.project.springbootstarter.user.UserRepository;
import aman.project.springbootstarter.user.UserService;
import aman.project.springbootstarter.user.model.IdentityType;
import aman.project.springbootstarter.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService = new UserService();

	@Test
	public void testGetAllUsers() throws Exception {
		List<User>users = new ArrayList<>();
		User user = new User();
		user.setMobileNo("9876543210");
		user.setIdentityType(IdentityType.PAN);
		user.setAddress("Delhi");
		user.setUsername("aman");
		user.setAge(21);
		users.add(user);
		user = new User();
		user.setMobileNo("8876543210");
		user.setIdentityType(IdentityType.PAN);
		user.setAddress("Delhi");
		user.setUsername("sid");
		user.setAge(21);
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);

		assertEquals(2, userService.getAllUsers().size());
	}
}
