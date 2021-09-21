package doctolib_service.data.jpa.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doctolib_service.data.jpa.domain.Worker;


@RestController
@RequestMapping("/api")


public interface WorkerDao extends JpaRepository <Worker, Long>{

}
