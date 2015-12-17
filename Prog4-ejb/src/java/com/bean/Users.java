/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author wenbog
 */
@Entity
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER", nullable=false)
    private Long id;
    
    @Column(name = "USERNAME", nullable=false)
    private String userName;
    
    @Column(name = "PASSWORD", nullable=false)
    private String password;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "owner", orphanRemoval=true )
    private Set<WhiteBoard> own;
    
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "subscriber" )
    private Set<WhiteBoard> subscrib;
        
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "com.bean.Users[ id=" + id + " ]";
    }

    public Set<WhiteBoard> getWb() {
        return own;
    }

    public void setWb(Set<WhiteBoard> wb) {
        this.own = wb;
    }

    public Set<WhiteBoard> getOwn() {
        return own;
    }

    public void setOwn(Set<WhiteBoard> own) {
        this.own = own;
    }

    public Set<WhiteBoard> getSubscrib() {
        return subscrib;
    }

    public void setSubscrib(Set<WhiteBoard> subscrib) {
        this.subscrib = subscrib;
    }
    
    
    
}
