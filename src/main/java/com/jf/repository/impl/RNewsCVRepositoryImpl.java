/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.CurriculumVitae;
import com.jf.pojos.RNewsCV;
import com.jf.pojos.RecruimentNews;
import com.jf.repository.RNewsCVRepository;
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
public class RNewsCVRepositoryImpl extends BaseRepositoryImpl implements RNewsCVRepository{

    @Override
    public boolean add(RNewsCV rNewsCV) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            RecruimentNews rn = session.get(RecruimentNews.class, rNewsCV.getRnId());
            CurriculumVitae cv = session.get(CurriculumVitae.class, rNewsCV.getCvId());
            rNewsCV.setRecruitmentNews(rn);
            rNewsCV.setCurriculumVitae(cv);

            session.save(rNewsCV);
            return true;
        } catch (HibernateException e) {
            System.out.println("Error at UserRepository: " + e.getMessage());
        }
        return false;
    }
    
}
