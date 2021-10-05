package doctolib_service.data.jpa.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doctolib_service.data.jpa.domain.Worker;


@RestController
@RequestMapping("/api")


public interface WorkerDao extends JpaRepository <Worker, Long>{

	List<Worker> findByLastNameContainingAndFirstNameContaining(String lastName, String firstName);

	List<Worker> findByLastNameContaining(String lastName);

	List<Worker> findByFirstNameContaining(String firstName);

	List<Worker> findByEmailContaining(String email);

	Worker save(Worker worker);

}
