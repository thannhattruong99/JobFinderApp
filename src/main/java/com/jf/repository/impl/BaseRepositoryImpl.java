/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import javax.persistence.criteria.Predicate;
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
public class BaseRepositoryImpl {
    @Autowired
    protected LocalSessionFactoryBean sessionFactory;

    protected static final int PAGING = 20;
    
    protected Predicate[] addPredidateElement(int size, Predicate[] arrPredicates, Predicate p) {
        Predicate[] result = new Predicate[size + 1];
        for (int i = 0; i < size; i++) {
            result[i] = arrPredicates[i];
        }
        result[size] = p;

        return result;
    }
}
