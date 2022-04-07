/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
    @NamedQuery(name = "Users.findUser",
            query = "SELECT x FROM Users x WHERE x.username = :a"), 
    
    @NamedQuery(name = "Users.findAllMinistry",
            query = "SELECT x FROM Users x WHERE x.userType = 0"), 
    
    @NamedQuery(name = "Users.findAllClinic",
            query = "SELECT x FROM Users x WHERE x.userType = 1"), 
    
    @NamedQuery(name = "Users.findAllPublicUser",
            query = "SELECT x FROM Users x WHERE x.userType = 2"), 
    
    @NamedQuery(name = "Users.findFilteredMinistry",
            query = "SELECT x FROM Users x WHERE (x.userType = 0) AND (x.username LIKE :a OR x.name LIKE :a OR x.phone LIKE :a OR x.ic LIKE :a OR x.email LIKE :a OR x.address LIKE :a)"), 
    
    @NamedQuery(name = "Users.findFilteredClinic",
            query = "SELECT x FROM Users x WHERE (x.userType = 1) AND (x.username LIKE :a OR x.name LIKE :a OR x.phone LIKE :a OR x.email LIKE :a OR x.address LIKE :a)"), 
    
    @NamedQuery(name = "Users.findFilteredPublicUser",
            query = "SELECT x FROM Users x WHERE (x.userType = 2) AND (x.username LIKE :a OR x.name LIKE :a OR x.phone LIKE :a OR x.ic LIKE :a OR x.email LIKE :a OR x.address LIKE :a)")
})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // userType
    // Ministry = 0, Clinic = 1, Public User = 2
    private int userType;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phone;
    private String ic;
    private String email;
    private String address;
    private boolean approved;

    public Users(int userType, String username, String password, String name, String gender, String ic, String phone, String email, String address) {
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.ic = ic;
        this.email = email;
        this.address = address;
        if (userType == 0) {
            this.approved = true;
        } else {
            this.approved = false;
        }
    }

    public Users(int userType, String username, String password, String name, String phone, String email, String address) {
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.approved = false;
    }
    
    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Users[ id=" + id + " ]";
    }
    
}
