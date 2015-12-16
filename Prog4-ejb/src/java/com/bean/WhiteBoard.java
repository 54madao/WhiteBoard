/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author munehiro
 */
@Entity
    @Table(name = "WhiteBoard")
    public class WhiteBoard implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_WHITEBOARD", nullable=false)
	private Long id;
    
	@Column(name="DESCRIPTION", nullable=false)
	    private String description;
        
        @Column(name="NAME", nullable=false)
            private String name;
        
        @Column(name="OWNER", nullable=false)
            private Long owner;
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
        if (!(object instanceof WhiteBoard)) {
            return false;
        }
        WhiteBoard other = (WhiteBoard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
        return "com.bean.WhiteBoard[ id=" + id + " ]";
    }
    
	public String getDescription( ) {
	    return description;
	}
    
	public void setDescription( String description ) {
	    this.description = description;
	}

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }
        
    }
