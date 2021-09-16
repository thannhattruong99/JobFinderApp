/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.Comment;
import com.jf.pojos.Rating;
import com.jf.pojos.User;
import com.jf.repository.UserRepository;
import com.jf.request.GetUsersRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author truongtn
 */
@Repository
@Transactional
public class UserRepositoryImpl extends BaseRepositoryImpl implements UserRepository{

    @Override
    public List<User> getUsers(String username, boolean isActive) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

        if (!username.isEmpty()) {
            Predicate p1 = builder.equal(root.get("username").as(String.class), username.trim());
            Predicate p2 = builder.equal(root.get("active").as(boolean.class), isActive);

            Predicate p3 = builder.and(p1, p2);
            query.where(p3);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<User> getUsers(GetUsersRequest request) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);

        query = query.select(root).orderBy(builder.desc(root.get("lastUpdated")));

        List<Predicate> predicates = new ArrayList<>();
        
//      Condition 1: search value
        if (!request.getFullname().isEmpty()) {
            Predicate p1 = builder.like(root.get("fullname").as(String.class),
                    String.format("%%%s%%", request.getFullname()));
            predicates.add(p1);
        }

//      Condition 2: status
        if (request.getStatusMode().equals(User.ACTIVE)) {
            Predicate p2 = builder.equal(root.get("active").as(boolean.class), true);
            predicates.add(p2);
        } else if (request.getStatusMode().equals(User.INACTIVE)) {
            Predicate p2 = builder.equal(root.get("active").as(boolean.class), false);
            predicates.add(p2);
        }

//      Combine 2 conditions
        Predicate[] predicateArr = new Predicate[predicates.size()];
        predicates.toArray(predicateArr);
        query.where(predicateArr);
        
        Query q = session.createQuery(query);

        q.setMaxResults(PAGING);

        q.setFirstResult((request.getPageNum() - 1) * PAGING);

        return q.getResultList();
    }

    @Override
    public int count(String fullname, String statusMode) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

//      Condition 1: search value
        Predicate p1 = null;
        if (!fullname.isEmpty()) {
            p1 = builder.like(root.get("fullname").as(String.class),
                    String.format("%%%s%%", fullname));
        }

//      Condition 2: status
        Predicate p2 = null;
        if (statusMode.equals(User.ACTIVE)) {
            p2 = builder.equal(root.get("active").as(boolean.class), true);
        } else if (statusMode.equals(User.INACTIVE)) {
            p2 = builder.equal(root.get("active").as(boolean.class), false);
        }

//      Combine 2 conditions
        if (p1 != null && p2 != null) {
            query.where(p1, p2);
        } else if (p1 != null && p2 == null) {
            query.where(p1);
        } else if (p1 == null && p2 != null) {
            query.where(p2);
        }

        Query q = session.createQuery(query);

        return q.getResultList().size();
    }

    @Override
    public boolean add(User user) throws Exception {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        session.save(user);
        return true;

    }

    @Override
    public boolean changeStatus(String id, boolean status) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        User user = session.get(User.class, id);
        user.setActive(status);
        try {
            session.save(user);
            return true;
        } catch (HibernateException e) {
            System.out.println("Error at UserRepository: " + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserDetailById(String id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        User user = session.get(User.class, id);

        return user;
    }

    
    @Override
    public User getUserDetailByUsername(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);

        if (!username.isEmpty()) {
            Predicate p1 = builder.equal(root.get("username").as(String.class),
                    String.format("%s", username));
            query.where(p1);
        }

        Query q = session.createQuery(query);

        User user = (User) q.getSingleResult();

        List<Comment> comments = new ArrayList<>(user.getReceivedComments());
        List<Rating> ratings = new ArrayList<>(user.getReceivedRatings());

        if (comments.size() > 0) {
            Collections.sort(comments);
            user.setComments(comments);
        }

        int sizeOfRatings;
        if ((sizeOfRatings = ratings.size()) > 0) {
            float totalOfRating = 0;
            for (Rating rating : ratings) {
                totalOfRating += rating.getScore();
            }
            user.setAverageScore((float) Math.ceil((totalOfRating / sizeOfRatings) * 10) / 10);
            user.setNumberOfVote(sizeOfRatings);
        }

        return user;
    }

}
