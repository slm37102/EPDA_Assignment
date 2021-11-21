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
public class AppointmentFacade extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "EPDA_Assignment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }
    
    public List<Appointment> findAllFromClinic(Long clinicId){        
        Query q = em.createNamedQuery("Appointment.findAllClinic");
        q.setParameter("a", clinicId);
        return q.getResultList();
    }
    
    public List<Appointment> findAllFromPublicUser(Long userId){
        Query q = em.createNamedQuery("Appointment.findAllPublicUser");
        q.setParameter("a", userId);
        return q.getResultList();
    }
}
