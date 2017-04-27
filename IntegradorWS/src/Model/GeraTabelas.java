package Model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabelas {
	
	public GeraTabelas(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("IntegradorWS");
		
	}
	
	public static void main(String[] args) {
	
		new GeraTabelas();
		
	}

}
