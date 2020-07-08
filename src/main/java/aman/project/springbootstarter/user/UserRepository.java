package aman.project.springbootstarter.user;

import aman.project.springbootstarter.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository	
public interface UserRepository extends JpaRepository<User,Integer>{

}
