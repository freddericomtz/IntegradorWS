package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Model.Denuncia;

public class DAODenuncia extends DAOEntity<Denuncia> {
	public void salvar(Denuncia denuncia){
		save(denuncia);
	}
	
	public void update(Denuncia denuncia){
		save(denuncia);
	}
	
	public List<Denuncia> findByUsuario(int id) {
		Query q = entityManager.createNativeQuery("SELECT * from denuncia WHERE usuario_id = "+id,Denuncia.class);
		return q.getResultList();
	}
}
