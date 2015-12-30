/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.service;

import com.bean.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wenbog
 */
@Local
public interface UserBeanLocal {
    
    /**
    * add a user to the database
    * @param user the Users entity bean
    */
    public boolean add(Users user);
    
    /**
    * update a user
    * @param user the Users entity bean
    */
    public void update(Users user);
    
    /**
    * delete a user from database
    * @param user the Users entity bean
    */
    public void delete(Users user);
    
    /**
    * get a user by id
    * @param id the user id
    */
    public Users get(long id);
    
    /**
    * get a user by name
    * @param name the user name
    */
    public Users get(String name);
    
    /**
    * get all users
    */
    public List<Users> getAll();
    
    /**
    * validate the user
    * @param user the Users entity bean
    */
    public boolean check(Users user);
}
