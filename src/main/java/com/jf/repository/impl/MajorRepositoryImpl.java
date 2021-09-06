/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.Major;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class MajorRepositoryImpl {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    public List<Major> getMajors(){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Major> query = builder.createQuery(Major.class);
        Root root = query.from(Major.class);
        Query q = session.createQuery(query);
        
        return q.getResultList();
        
    }
}
