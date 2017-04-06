package Services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
 
public class Conexao {
	private static EntityManagerFactory emf;
 
	public static EntityManager getEntityManager() {
		 if (emf == null){
			emf = Persistence.createEntityManagerFactory("IntegradorWS");
		 }
		 return emf.createEntityManager();
	}
}
