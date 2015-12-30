/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.service;

import com.bean.Users;
import com.bean.WhiteBoard;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author wenbog
 */
@Local
public interface WhiteBoardBeanLocal {
    
    /**
    * add a white board to the database
    * @param wb the WhiteBoard entity bean
    */
    public void add(WhiteBoard wb);
    
    /**
    * update a white board
    * @param wb the WhiteBoard entity bean
    */
    public void update(WhiteBoard wb);
    
    /**
    * delete a white board from the database
    * @param wb the WhiteBoard entity bean
    */
    public void delete(WhiteBoard wb);
    
    /**
    * get a white board by id
    * @param id the white board id
    */
    public WhiteBoard get(long id);
    
    /**
    * get all white boards
    */
    public List<WhiteBoard> getAll();
    //public void subscribe(WhiteBoard wb, Users user);
}
