/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.City;
import com.jf.repository.DistrictRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author truongtn
 */
@Repository
public class DistrictRepositoryImpl extends BaseRepositoryImpl implements DistrictRepository {

    @Override
    public List<City> getCities() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<City> query = builder.createQuery(City.class);
        query.from(City.class);
        Query q = session.createQuery(query);

        return q.getResultList();
    }

}
