/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.Comment;
import com.jf.repository.CommentRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author truongtn
 */
@Repository
@Transactional
public class CommentRepositoryImpl extends BaseRepositoryImpl implements CommentRepository{

    @Override
    public boolean add(Comment comment) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            session.save(comment);
            return true;
        } catch (Exception e) {
            System.out.println("Error at CommentRepositoryImpl: " + e.getMessage());
        }
        return false;
    }
}
