package doctolib_service.data.jpa.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import doctolib_service.data.jpa.SampleDataJpaApplication;
import doctolib_service.data.jpa.dao.WorkerDao;
import doctolib_service.data.jpa.domain.Worker;
import doctolib_service.data.jpa.test.JsonUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes =SampleDataJpaApplication.class)
@AutoConfigureMockMvc 
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)

@DisplayName("Test of the class of WorkerController")
public class WorkerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WorkerDao repository;

    @DisplayName("Test of the  Post API  (\"/api/workers\"); name: createWorker() ")
    @Test
    public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
    	  Worker optWorker1 =new Worker("Alexandrie","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff");

	       mvc.perform(post("/api/workers").contentType(MediaType.APPLICATION_JSON)
	    		   .content(JsonUtil.toJson(optWorker1)))//;
	       .andExpect(status().isCreated());
	      List<Worker> found = repository.findAll();
	       assertThat(found).extracting(Worker::getFirstName).contains("Alexandrie");
	      
	          }

    @DisplayName("Test of the  GET API (\"/api/workers/{Id}\"); method name: createWorker() ")
	  @Test
	  public void MethodeGetWorkerById_shouldReturnStatus200() throws Exception {
		 
		  MockHttpServletResponse response=     mvc.perform(get("/api/workers/{Id}",1)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk())
		       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		       .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
		       .andReturn().getResponse();
		     assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	  }
    
@Test
public void whenPostWorkers_thenCreateWorkers_thenReturnJsonArray() throws  IOException, Exception {

/*Create a list of workers*/
Worker optWorker1 =new Worker("Alexandr","djopnk","Gastonbadonnnjjjj@yahoo.fr",
"12azzeee","MenuisierSoudeur","12assff");


mvc.perform(post("/api/workers").contentType(MediaType.APPLICATION_JSON)
.content(JsonUtil.toJson(optWorker1)));
//.andExpect(status().isCreated());

//testing creating a worker
List<Worker> found = repository.findAll();
//System.out.println(found);
System.out.println("**************************");
//assertThat(found).extracting(Worker::getFirstName).contains("Alexandrie");
assertThat(found).extracting(Worker::getFirstName).contains("Alexandrie");

}

@After
public void resetDb() {
   // repository.deleteAll();
}
}