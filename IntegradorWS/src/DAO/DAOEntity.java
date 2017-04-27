package DAO;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


import Services.Conexao;

public class DAOEntity<T extends Serializable> {
	 @PersistenceContext(unitName = "trabalho")
	    protected final EntityManager entityManager;
	 	protected final Class<T> persistentClass;

	 
		    public DAOEntity() {
		        this.entityManager = Conexao.getEntityManager();
		        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		    }
	 
		    public EntityManager getEntityManager() {
		        return entityManager;
		    }
	 
		    public void save(T entity) {
		        EntityTransaction tx = getEntityManager().getTransaction();
	 
		        try {
		            tx.begin();
		            getEntityManager().persist(entity);
		            tx.commit();
		        } catch (Throwable t) {
		            t.printStackTrace();
		            tx.rollback();
		        } finally {
		            close();
		        }
		    }
		    
		    public void update(T entity) {
		        EntityTransaction tx = getEntityManager().getTransaction();
	 
		        try {
		            tx.begin();
		            getEntityManager().merge(entity);
		            tx.commit();
		        } catch (Throwable t) {
		            t.printStackTrace();
		            tx.rollback();
		        } finally {
		            close();
		        }
		    }
		    
		   
		    
		   public T efetuarLogin(String login,String senha){
			   Session session = (Session) getEntityManager().getDelegate();
			   return (T) session.createCriteria(persistentClass)
					   .add(Restrictions.eq("login",login).ignoreCase())
					   .add(Restrictions.eq("senha",senha).ignoreCase()).uniqueResult();
		   }
		   
		   public T findById(int id){
			   Session session = (Session) getEntityManager().getDelegate();
			   return (T) session.createCriteria(persistentClass)
					   .add(Restrictions.eq("id", id)).uniqueResult();
		   }
	 
		    private void close() {
		        if (getEntityManager().isOpen()) {
		            getEntityManager().close();
		        }
	 
		    }
	}