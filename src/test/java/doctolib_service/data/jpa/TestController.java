package doctolib_service.data.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import doctolib_service.data.jpa.dao.WorkerDao;
import doctolib_service.data.jpa.domain.Worker;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes =SampleDataJpaApplication.class)
@AutoConfigureMockMvc 
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
//@AutoConfigureTestDatabase
public class TestController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WorkerDao repository;

    @After
    public void resetDb() {
       // repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
    	  Worker optWorker1 =new Worker("Alexandrie","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff");

	       mvc.perform(post("/api/workers").contentType(MediaType.APPLICATION_JSON)
	    		   .content(JsonUtil.toJson(optWorker1)));
	      List<Worker> found = repository.findAll();
	       //System.out.println(found);
	       System.out.println("**************************");
	       assertThat(found).extracting(Worker::getFirstName).containsOnly("Alexandrie");
	          }

  /*  @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
        createTestEmployee("bob");
        createTestEmployee("alex");

        // @formatter:off
        mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
          .andExpect(jsonPath("$[0].name", is("bob")))
          .andExpect(jsonPath("$[1].name", is("alex")));
        // @formatter:on
    }*/
}