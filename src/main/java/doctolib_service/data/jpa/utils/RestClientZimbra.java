package doctolib_service.data.jpa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Base64;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import doctolib_service.data.jpa.SampleDataJpaApplication;
import doctolib_service.data.jpa.utils.model.Appt;
import doctolib_service.data.jpa.utils.model.Root;
import io.swagger.annotations.ApiOperation;

public class RestClientZimbra {
	
	@ApiOperation(value = "Classe de connexion à l'Api Zimbra "
			+ " pour identifier les plages disponibles pour un utilisateur "
			+ "donné afin de pouvoir prendre un RDV")
	
	public static String connexionApiZimbra(String email,String password,String appointementStart,
			String appointementEnd) {
		
	    System.out.println("15email_adress=" + email);
	    System.out.println("15password=" + password);
	  
	    String result = null;
	    try {
	        URL url = new URL("https://partage.univ-rennes1.fr/home/" + email + "/calendar?fmt=json&start=" +
	        		appointementStart +
	                
	                "&end=" +
	                appointementEnd);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        String encoded = Base64.getEncoder().encodeToString((email+":"+password).getBytes(StandardCharsets.UTF_8));  //Java 8
	        conn.setRequestProperty("Authorization", "Basic "+encoded);
	        /*
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        Authenticator.setDefault(new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(email, password.toCharArray());
	            }
	        });*/
	        conn.setRequestMethod("GET");
	        //conn.setRequestProperty("--user", email + ":" + password);
	        if (conn.getResponseCode() != 200) {
	            System.out.println("15erreur=" + url.toString());
	            System.out.println("15erreur=" + conn.getResponseMessage());
	            System.out.println("15erreur=" + conn.getRequestMethod());
	            return null;
	        }
	        System.out.println("*******************************");
	        System.out.println(conn.getResponseMessage());
	      
	        int responseCode=conn.getResponseCode();
	        if (responseCode == HttpsURLConnection.HTTP_OK) {
	            //Read
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

	        String line = null;
	        StringBuilder sb = new StringBuilder();

	        while ((line = bufferedReader.readLine()) != null) {
	            sb.append(line);
	        }
	        System.out.println("*******************************");
	        bufferedReader.close();
	        result = sb.toString();
	        System.out.println("*************************Avant Result******");
	        System.out.println(result);
	        System.out.println("--------------------------------------------");
	       
	        return result;
	        //System.out.println(root.getAppt().get(0).getInv().get(0).getComp().get(0).getS().get(0).getD());

	        }else {
	        //    return new String("false : "+responseCode);
	        new String("false : "+responseCode);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;    
	}
	
	public static boolean acceptReservation(String json, String start,String end) throws JsonParseException, JsonMappingException, IOException, ParseException
	{
		 ObjectMapper mapper = new ObjectMapper() .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
	       	        
	        Root root= mapper.readValue(json, Root.class);
	        for (Appt item:root.getAppt())
	        {
	        	System.out.println("****RDV******");
	        	//Date rdvStart=new SimpleDateFormat("yyyyMMddThhMMss").parse(item.getInv().get(0).getComp().get(0).getS().get(0).getD());  
	        	//Date rdvEnd=new SimpleDateFormat("yyyyMMddThhMMss").parse(item.getInv().get(0).getComp().get(0).getE().get(0).getD());  
		       Long rdvStart=convertToLong(item.getInv().get(0).getComp().get(0).getS().get(0).getD());
		       Long rdvEnd=convertToLong(item.getInv().get(0).getComp().get(0).getE().get(0).getD());
		       Long rdvWhishStart=convertToLong(start);
		       Long rdvWhishEnd=convertToLong(end);	       
		       if((rdvWhishStart>=rdvStart && rdvWhishStart<rdvEnd)|| 
		    		   (rdvWhishEnd>rdvStart && rdvWhishEnd<=rdvEnd)||
		    		   (rdvWhishStart<rdvStart && rdvWhishEnd>=rdvEnd)||
		    		   (rdvWhishStart>=rdvStart && rdvWhishEnd<=rdvEnd))
		    	   return false;
	        }
			return true;
	        
	        
	}
	
	public static long convertToLong(String date) 
	{
		
		return Long.parseLong(date.replace(" ", "").replace(":", "").replace("-", "").replace("T", ""));
	}
	
	
	
	public static void main(String[] args) throws Exception {
		String json=connexionApiZimbra("cecile.bougma@etudiant.univ-rennes1.fr", "Istic.1522","2021/11/05","2021/11/06");
		//"cecile.bougma@etudiant.univ-rennes1.fr", "Istic.1522","milliseconds |2021/11/04| 11/04/2021","milliseconds |2021/11/04| 11/04/2021"
		//2021/04/11
    	Date start=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-11-05 12:00:00");  
    	Date end=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-11-05 12:30:00");  

		System.out.println(acceptReservation(json,"2021-11-05 11:00:00","2021-11-05 12:00:00"));
	}
}
