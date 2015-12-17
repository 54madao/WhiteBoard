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
    public void add(WhiteBoard wb);
    public void update(WhiteBoard wb);
    public void delete(WhiteBoard wb);
    public WhiteBoard get(long id);
    public List<WhiteBoard> getAll();
    //public void subscribe(WhiteBoard wb, Users user);
}
