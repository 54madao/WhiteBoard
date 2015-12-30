/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.service;

import com.bean.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wenbog
 */
@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "testGuo")
    private EntityManager em;
    
    private List<Users> users;
    @PostConstruct
    private void init( ) {
        //users = new ArrayList<>( );
    }
         
    @Override
    public boolean add(Users user) {

        //users.add(user);
        if(get(user.getUserName()) == null){
            em.persist(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void update(Users user) {
      
        //facade
        Users us = get(user.getId());  
        us.setUserName(user.getUserName());
        us.setPassword(user.getPassword());
        em.merge(us);
        
        //int index = this.users.indexOf(us);
        //this.users.set(index, us);
    }

    @Override
    public void delete(Users user) {
        
        //facade
        Users us = get(user.getId());
        
        //this.users.remove(us);
        em.remove(em.merge(us));
    }

    @Override
    public Users get(long id) {
        
//        for(Users us: this.users){
//            if(us.getId() == id){
//                return us;
//            }
//        }
//        return null;
        String sql = "from Users s where s.id=:uId";
	Query q= em.createQuery(sql);
	q.setParameter("uId", id);
	return (Users)q.getSingleResult();
    }

    @Override
    public List<Users> getAll() {
        
        String sql = "from Users s";
		
	Query q = em.createQuery(sql);
        this.users = q.getResultList();
        return this.users;
    }

    @Override
    public boolean check(Users user) {
        String sql = "from Users s where s.userName=:uName and s.password=:uPsd";
        Query q = em.createQuery(sql);
        q.setParameter("uName", user.getUserName());
        q.setParameter("uPsd", user.getPassword());
        try{
            Users tmp = (Users)q.getSingleResult();
            return true;
        } catch(NoResultException e){
            return false;
        }
 
    }

    @Override
    public Users get(String name) {
        String sql = "from Users s where s.userName=:uName";
        Query q = em.createQuery(sql);
        q.setParameter("uName", name);
        try{
            return (Users)q.getSingleResult();
        } catch(NoResultException e){       
            return null;
        }  
    }
    
}
