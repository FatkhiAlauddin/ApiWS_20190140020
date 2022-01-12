/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Webservice.bananastore;

import Webservice.bananastore.exceptions.NonexistentEntityException;
import Webservice.bananastore.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author GF63-9RCX-622ID
 */
public class PisangstoreJpaController implements Serializable {

    public PisangstoreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Webservice_bananastore_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PisangstoreJpaController() {
    }

    public void create(Pisangstore pisangstore) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pisangstore);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPisangstore(pisangstore.getId()) != null) {
                throw new PreexistingEntityException("Pisangstore " + pisangstore + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pisangstore pisangstore) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pisangstore = em.merge(pisangstore);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pisangstore.getId();
                if (findPisangstore(id) == null) {
                    throw new NonexistentEntityException("The pisangstore with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pisangstore pisangstore;
            try {
                pisangstore = em.getReference(Pisangstore.class, id);
                pisangstore.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pisangstore with id " + id + " no longer exists.", enfe);
            }
            em.remove(pisangstore);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pisangstore> findPisangstoreEntities() {
        return findPisangstoreEntities(true, -1, -1);
    }

    public List<Pisangstore> findPisangstoreEntities(int maxResults, int firstResult) {
        return findPisangstoreEntities(false, maxResults, firstResult);
    }

    private List<Pisangstore> findPisangstoreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pisangstore.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pisangstore findPisangstore(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pisangstore.class, id);
        } finally {
            em.close();
        }
    }

    public int getPisangstoreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pisangstore> rt = cq.from(Pisangstore.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
