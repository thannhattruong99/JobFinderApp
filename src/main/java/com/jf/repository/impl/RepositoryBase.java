/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author truongtn
 */
@Component
public class RepositoryBase {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    public CriteriaBuilder getCriteriaBuilder(){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.getCriteriaBuilder();
    }
}
