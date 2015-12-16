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
    public boolean add(Users user);
    public void update(Users user);
    public void delete(Users user);
    public Users get(long id);
    public Users get(String name);
    public List<Users> getAll();
    public boolean check(Users user);
}
