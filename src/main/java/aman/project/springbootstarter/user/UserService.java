package aman.project.springbootstarter.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.topic.Topic;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	private List<User> users = new ArrayList<>(Arrays.asList(
			new User("100", "aman", "23", 123012),
			new User("101", "sid", "23", 385295)
			));
	public List<User> getAllUsers()
	{
		List<User>users = (List<User>)userRepository.findAll(); 
		return users;
	}
	public Optional<User> getUser(String id) {
		return userRepository.findById(id);
	}
	public void addUser(User user) {
		userRepository.save(user);
		//users.add(user);
	}
	public void updateUser(String id, User user) {
		userRepository.save(user);
		
	}
	public void deleteUser(String id) {
		userRepository.deleteById(id);		
	}
}
