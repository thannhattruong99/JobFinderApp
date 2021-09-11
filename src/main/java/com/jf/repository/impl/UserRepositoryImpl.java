/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.User;
import com.jf.request.GetUsersRequest;
import static java.lang.StrictMath.log;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class UserRepositoryImpl {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private static final int PAGING = 2;

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

    public List<User> getUsers(GetUsersRequest request) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);

        query = query.select(root).orderBy(builder.desc(root.get("lastUpdated")));

//      Condition 1: search value
        Predicate p1 = null;
        if (!request.getFullname().isEmpty()) {
            p1 = builder.like(root.get("fullname").as(String.class),
                    String.format("%%%s%%", request.getFullname()));
        }

//      Condition 2: status
        Predicate p2 = null;
        if (request.getStatusMode().equals(User.ACTIVE)) {
            p2 = builder.equal(root.get("active").as(boolean.class), true);
//            query.where(p2);
        } else if (request.getStatusMode().equals(User.INACTIVE)) {
            p2 = builder.equal(root.get("active").as(boolean.class), false);

        }

//      Combine 2 conditions
        if (p1 != null && p2 != null) {
            Predicate p3 = builder.and(p1, p2);
            query.where(p3);
        } else if (p1 != null && p2 == null) {
            query.where(p1);
        } else if (p1 == null && p2 != null) {
            query.where(p2);
        }

        Query q = session.createQuery(query);

        q.setMaxResults(PAGING);

        q.setFirstResult((request.getPageNum() - 1) * PAGING);

        return q.getResultList();
    }

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

    public boolean add(User user) throws Exception {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        session.save(user);
        return true;

    }

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

    public User getUserDetailById(String id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        User user = session.get(User.class, id);

        return user;
    }

    public User getUserDetailByUsername(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);

        Predicate p1 = null;
        if (!username.isEmpty()) {
            p1 = builder.equal(root.get("username").as(String.class),
                    String.format("%s", username));
            query.where(p1);
        }

        Query q = session.createQuery(query);

        return (User) q.getSingleResult();
    }

    public User getRecruimentNewsSetByUser(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);

        Predicate p1 = null;
        if (!username.isEmpty()) {
            p1 = builder.equal(root.get("username").as(String.class),
                    String.format("%s", username));
            query.where(p1);
        }

        Query q = session.createQuery(query);

        User result = (User) q.getSingleResult();
        result.getRecruimentNewsSet().size();
        return result;
    }
}
