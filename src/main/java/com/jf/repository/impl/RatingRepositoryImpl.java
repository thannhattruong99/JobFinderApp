/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.Rating;
import com.jf.pojos.User;
import com.jf.repository.RatingRepository;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author truongtn
 */
@Repository
@Transactional
public class RatingRepositoryImpl extends BaseRepositoryImpl implements RatingRepository{

    @Override
    public Rating rating(User sender, User receiver) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Rating> query = builder.createQuery(Rating.class);
        Root root = query.from(Rating.class);

//        Condition 1: 
        Predicate p1 = builder.equal(root.get("candidate").as(User.class), sender);

//        Condition 2: 
        Predicate p2 = builder.equal(root.get("employer").as(User.class), receiver);

        query.where(p1, p2);
        Query q = session.createQuery(query);

        try {
            return (Rating) q.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean addOrUpdate(Rating rating) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.saveOrUpdate(rating);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

}
