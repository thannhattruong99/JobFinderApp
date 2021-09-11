/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.CurriculumVitae;
import com.jf.pojos.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author truongtn
 */
@Repository
@Transactional
public class CurriculumVitaeRepositoryImpl {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    public boolean update(CurriculumVitae updateCV) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            CurriculumVitae cv = session.get(CurriculumVitae.class, updateCV.getId());
            cv.setName(updateCV.getName());
            cv.setDescription(updateCV.getDescription());
            cv.setUrl(updateCV.getUrl());
            session.save(cv);
            return true;
        } catch(HibernateException e){
            System.out.println("Error at CurriculumVitaeRepositoryImpl: " + e.getMessage());
        }
        return false;
    }
    
    public boolean add(CurriculumVitae cv) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            User user = session.get(User.class, cv.getCandidateId());
            cv.setUser(user);
            session.save(cv);
            return true;
        } catch(HibernateException e){
            System.out.println("Error at CurriculumVitaeRepositoryImpl: " + e.getMessage());
        }
        return false;
    }
    
    public boolean deleteCV(String id){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            CurriculumVitae cv = session.get(CurriculumVitae.class, id);
            session.delete(cv);
            return true;
        }catch(HibernateException e){
            System.out.println("Error at UserRepository: " + e.getMessage());
        }
        return false;
    }
    
}
