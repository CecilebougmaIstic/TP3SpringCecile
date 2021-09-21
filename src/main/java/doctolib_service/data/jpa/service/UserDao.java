package doctolib_service.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import doctolib_service.data.jpa.domain.User;


public interface UserDao extends JpaRepository <User, Long> {

	//public  User findById(long id);
	public  User findByEmail(String email);
	
		

}
