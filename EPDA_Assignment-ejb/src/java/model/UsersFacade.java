/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SLM
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "EPDA_Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users findUser(String username){
        Query q = em.createNamedQuery("Users.findUser");
        q.setParameter("a", username);
        List<Users> result = q.getResultList();
        if (result.size()>0) {
            return result.get(0);
        }
        return null;
    }
    
    public List<Users> findAllMinistry(){
        Query q = em.createNamedQuery("Users.findAllMinistry");
        return q.getResultList();
    }
    
    public List<Users> findAllClinic(){
        Query q = em.createNamedQuery("Users.findAllClinic");
        return q.getResultList();
    }
    
    public List<Users> findAllPublicUser(){
        Query q = em.createNamedQuery("Users.findAllPublicUser");
        return q.getResultList();
    }
    
    public List<Users> findFilteredMinistry(String search){
        Query q = em.createNamedQuery("Users.findFilteredMinistry");
        q.setParameter("a", "%"+search+"%");
        return q.getResultList();
    }
    
    public List<Users> findFilteredClinic(String search){
        Query q = em.createNamedQuery("Users.findFilteredClinic");
        q.setParameter("a", "%"+search+"%");
        return q.getResultList();
    }
    
    public List<Users> findFilteredPublicUser(String search){
        Query q = em.createNamedQuery("Users.findFilteredPublicUser");
        q.setParameter("a", "%"+search+"%");
        return q.getResultList();
    }
}
