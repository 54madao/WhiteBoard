/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.local.service;

import com.bean.WhiteBoard;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        wb = new ArrayList<>( );
    }
        
    
    
    @Override
    public void add(WhiteBoard wb) {
        em.persist(wb);
        this.wb.add(wb);
    }

    @Override
    public void update(WhiteBoard wb) {
        //em.merge(wb);
        
        //facade
        WhiteBoard whiteboard = get(wb.getId());
        int index = this.wb.indexOf(whiteboard);
        this.wb.set(index, wb);
    }

    @Override
    public void delete(WhiteBoard wb) {
        //em.remove(em.merge(user));
        
        //facade
        WhiteBoard whiteboard = get(wb.getId());
        
        this.wb.remove(whiteboard);
    }

    @Override
    public WhiteBoard get(long id) {
        /*String jpql = "from TestUserInfo s where s.userId=:uId";
	Query query = em.createQuery(jpql);
	query.setParameter("uId", userId);
	return (TestUserInfo)query.getSingleResult();*/
        for(WhiteBoard wb: this.wb){
            if(wb.getId() == id){
                return wb;
            }
        }
        return null;
    }

    @Override
    public List<WhiteBoard> getAll() {
        /*String jpql = "from TestUserInfo s";
		
	Query query = em.createQuery(jpql);	
	List<TestUserInfo> list = query.getResultList();
	return list;*/
        return this.wb;
    }


    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
