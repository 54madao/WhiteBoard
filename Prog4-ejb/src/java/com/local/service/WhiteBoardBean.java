/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.service;

import com.bean.Users;
import com.bean.WhiteBoard;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wenbog
 */
@Stateless
public class WhiteBoardBean implements WhiteBoardBeanLocal {
        
    @PersistenceContext(unitName = "testGuo")
    private EntityManager em;
    
    private List<WhiteBoard> wb;
    @PostConstruct
	private void init( ) {
        //wb = new ArrayList<>( );
    }
        
    
    
    @Override
    public void add(WhiteBoard wb) {
        em.persist(wb);
        //this.wb.add(wb);
    }

    @Override
    public void update(WhiteBoard wb) {
        //em.merge(wb);
        
        //facade
        WhiteBoard whiteboard = get(wb.getId());
        whiteboard.setName(wb.getName());
        whiteboard.setDescription(wb.getDescription());
        //int index = this.wb.indexOf(whiteboard);
        //this.wb.set(index, wb);
        em.merge(whiteboard);
    }

    @Override
    public void delete(WhiteBoard wb) {
        //em.remove(em.merge(user));
        
        //facade
        WhiteBoard whiteboard = get(wb.getId());
        
        //this.wb.remove(whiteboard);
        
        em.remove(em.merge(whiteboard));
    }

    @Override
    public WhiteBoard get(long id) {
        /*String jpql = "from TestUserInfo s where s.userId=:uId";
	Query query = em.createQuery(jpql);
	query.setParameter("uId", userId);
	return (TestUserInfo)query.getSingleResult();*/
//        for(WhiteBoard wb: this.wb){
//            if(wb.getId() == id){
//                return wb;
//            }
//        }
//        return null;
        String sql = "from WhiteBoard s where s.id=:wbId";
	Query q = em.createQuery(sql);
	q.setParameter("wbId", id);
	return (WhiteBoard)q.getSingleResult();
    }

    @Override
    public List<WhiteBoard> getAll() {
        /*String jpql = "from TestUserInfo s";
		
	Query query = em.createQuery(jpql);	
	List<TestUserInfo> list = query.getResultList();
	return list;*/
        //return this.wb;
        String sql = "from WhiteBoard s";	
	Query q = em.createQuery(sql);	 
        this.wb = q.getResultList(); 
        return this.wb;
    }


    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

//    @Override
//    public void subscribe(WhiteBoard wb, Users user) {
//        WhiteBoard whiteboard = get(wb.getId());
//        whiteboard.getSubscriber().add(user);
//        em.merge(whiteboard);
//        WhiteBoard tmp = get(wb.getId());
//        System.out.println("Size:" + tmp.getSubscriber().size());
//    }
    
}
