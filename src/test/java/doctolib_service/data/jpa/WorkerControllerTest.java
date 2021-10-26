package doctolib_service.data.jpa;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.fasterxml.jackson.databind.ObjectMapper;

import doctolib_service.data.jpa.dao.WorkerDao;
import doctolib_service.data.jpa.domain.Worker;
import doctolib_service.data.jpa.web.WorkerController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkerControllerTest {
	
	@Autowired
	private MockMvc mvc;
	private static final ObjectMapper om = new ObjectMapper();
	 @MockBean
	 Worker WorkerForTest;
	
	 @MockBean
	 private WorkerDao workerDaoForTest;
	 
	 
	@Autowired
	 private WorkerController workerControllerTest;
	 
	 @Before
	    public void init() {

	        Optional<Worker> optWorker = Optional.of( new Worker(1L,"Alexandrie","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff"));
	        when(workerDaoForTest.findById(1L)).thenReturn(optWorker);
	     
	       
	        }

	  //@Test
	    public void testMethodeGetWorkerById() throws Exception {
	        // when
	        MockHttpServletResponse response = mvc.perform(
	                get("/workers/{Id}", 1)
	                        .accept(MediaType.APPLICATION_JSON))
	                .andReturn().getResponse();

	        // then
	        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	        //assertThat(response.getHeaders("X-SUPERHERO-APP")).containsOnly("super-header");
	    }
	    
	
	  @Test
	  public void MethodeGetWorkerById_shouldReturnStatus200() throws Exception {
		 
		     mvc.perform(get("/api/workers/{Id}",1)
		       .accept(MediaType.APPLICATION_JSON))
		       .andExpect(status().isOk())
		       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		       .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
		       .andReturn().getResponse();
		        
		      //verify(workerDaoForTest, atLeastOnce()).getWorkerById();
		       //verifyNoMoreInteractions(workerDaoForTest);
		     //assertEquals(1L,workerDaoForTest.findById(1L));
		    	     }
@Test
	  public void whenPostWorkers_thenCreateWorkers_thenReturnJsonArray() throws  IOException, Exception {
		 
		   /*Create a list of workers*/
	        Worker optWorker1 =new Worker("Alexandrie","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff");
		 //Worker optWorker2= new Worker(2L,"Alex","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		// "12azzeee","MenuisierSoudeur","12assff");
	        
	      //  Worker optWorker3= new Worker(2L,"Sarah","SaralDupontnio","Gastonbadonnnjjjj@yahoo.fr",
		    		// "12azzeee","MenuisierSoudeur","12assff");
	        
	        //workerDaoForTest.saveAndFlush(optWorker1);
	        //workerDaoForTest.save(optWorker1);
	        System.out.println("--------------------------");
	        System.out.println(optWorker1.toString());
	        //System.out.println(workerDaoForTest.save(optWorker1));
	        System.out.println("------------------------------");
	        //workerDaoForTest.saveAndFlush(optWorker2);
	       // workerDaoForTest.saveAndFlush(optWorker3);
	        //List<Worker> found = workerDaoForTest.findAll();
	        //when (workerDaoForTest.findAll()).thenReturn(found);
	        //System.out.println("***********************");
	        //System.out.println(found);
	       //System.out.println("**************************");
	       //.content(JsonUtil.toJson(bob))
	    
	       mvc.perform(post("/api/workers").contentType(MediaType.APPLICATION_JSON)
	    		   .content(JsonUtil.toJson(optWorker1)));
	     //.andExpect(status().isCreated());
	     //.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
	    // .andExpect((MockMvcResultMatchers.jsonPath("$[0].firstName").value(optWorker1.getFirstName())))
	    // .andExpect((MockMvcResultMatchers.jsonPath("$[1].firstName").value(optWorker2.getFirstName())))
	     //.andExpect((MockMvcResultMatchers.jsonPath("$[2].firstName").value(optWorker3.getFirstName())));
	       System.out.println("fgghjjkkll");
	       //testing creating a worker
	       List<Worker> found = workerDaoForTest.findAll();
	       System.out.println(found);
	       System.out.println("**************************");
	       //assertThat(found).extracting(Worker::getFirstName).contains("Alexandrie");
	       assertThat(found).extracting(Worker::getFirstName).containsOnly("Alexandrie");
	       //verify(workerDaoForTest, VerificationModeFactory.times(1)).
	       //reset(workerDaoForTest);
	        }
	  
	
		    
	 /*Create a Worker Data for tests*/ 
	  

	/*
	 *  Worker optWorker =new Worker(1L,"Alexandrie","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff");
	        
	        
	       Worker optWorker2=  new Worker(2L,"Alex","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff");
	      Worker optWorker3=  new Worker(2L,"Sarah","SaralDupontnio","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff");
	       List<Worker> WorkerallWorkers = Arrays.asList(optWorker2, optWorker3, optWorker3);
	       */  
	
	  
	 

	@AfterEach
		public void afterTest() throws Exception {
		  
		}
	  /*
	   *  
		  Optional<Worker> optWorker = Optional.of( new Worker(1L,"Alexandrie","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff"));
	        
	        
	        Optional<Worker> optWorker2= Optional.of( new Worker(2L,"Alex","djopnk","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff"));
	        
	        Optional<Worker> optWorker3= Optional.of( new Worker(2L,"Sarah","SaralDupontnio","Gastonbadonnnjjjj@yahoo.fr",
		    		 "12azzeee","MenuisierSoudeur","12assff"));
	       
	        List<Optional<Worker>> allWorkers = Arrays.asList(optWorker2, optWorker3, optWorker3);

	        when(workerDaoForTest.findAll()).thenReturn(allWorkers); 
	   */

}
