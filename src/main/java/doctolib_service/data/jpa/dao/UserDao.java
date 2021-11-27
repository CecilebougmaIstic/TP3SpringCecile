package doctolib_service.data.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import doctolib_service.data.jpa.domain.User;


public interface UserDao extends JpaRepository <User, Long> {

	public  User findByEmail(String email);

	public List<User> findByEmailContaining(String email);

	public List<User> findByFirstNameContaining(String firstName);

	public List<User> findByLastNameContaining(String lastName);

	public List<User> findByLastNameContainingAndFirstNameContaining(String lastName, String firstName);

	
		

}
