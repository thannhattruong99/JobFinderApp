/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.City;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author truongtn
 */
@Repository
@Transactional
public class DistrictRepositoryImpl {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    public List<City> getCities() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<City> query = builder.createQuery(City.class);
        Root root = query.from(City.class);
        Query q = session.createQuery(query);
        
        return q.getResultList();
    }

}
