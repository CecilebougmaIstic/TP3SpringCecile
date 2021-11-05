package doctolib_service.data.jpa.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import doctolib_service.data.jpa.SampleDataJpaApplication;
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
	        }else {
	        //    return new String("false : "+responseCode);
	        new String("false : "+responseCode);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;    
	}
	/*
	public static void main(String[] args) throws Exception {
		connexionApiZimbra("cecile.bougma@etudiant.univ-rennes1.fr", "Istic.1522","20211105T000000","20211105T000000");
		//"cecile.bougma@etudiant.univ-rennes1.fr", "Istic.1522","milliseconds |2021/11/04| 11/04/2021","milliseconds |2021/11/04| 11/04/2021"
		//2021/04/11
	}*/
}
