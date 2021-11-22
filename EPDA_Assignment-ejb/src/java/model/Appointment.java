/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author SLM
 */
@Entity
@NamedQueries({    
    @NamedQuery(name = "Appointment.findAllClinic",
            query = "SELECT x FROM Appointment x WHERE x.clinicId = :a ORDER BY x.appointDate"), 
    
    @NamedQuery(name = "Appointment.findAllPublicUser",
            query = "SELECT x FROM Appointment x WHERE x.userId = :a ORDER BY x.appointDate"), 
    
    @NamedQuery(name = "Appointment.findAllSorted",
            query = "SELECT x FROM Appointment x ORDER BY x.appointDate")
})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long clinicId;
    private Date appointDate;
    private int numDose;
    private int accepted; // pending = 0, accept = 1, reject =-1 
    private boolean finishVac;

    public Appointment(Long userId, Long clinicId, Date appointDate, int numDose) {
        this.userId = userId;
        this.clinicId = clinicId;
        this.appointDate = appointDate;
        this.numDose = numDose;
        this.accepted = 0;
        this.finishVac = false;
    }

    public Appointment() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Date getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }

    public int getNumDose() {
        return numDose;
    }

    public void setNumDose(int numDose) {
        this.numDose = numDose;
    }

    public int isAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public boolean isFinishVac() {
        return finishVac;
    }

    public void setFinishVac(boolean finishVac) {
        this.finishVac = finishVac;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Appointment[ id=" + id + " ]";
    }
    
}
