/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository.impl;

import com.jf.pojos.CurriculumVitae;
import com.jf.pojos.District;
import com.jf.pojos.Major;
import com.jf.pojos.RNewsCV;
import com.jf.pojos.RecruimentNews;
import com.jf.pojos.User;
import com.jf.repository.RecruimentNewsRepository;
import com.jf.request.GetRecuitmentNewsRequester;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author truongtn
 */
@Repository
@Transactional
public class RecruimentNewsRepositoryImpl extends BaseRepositoryImpl implements RecruimentNewsRepository{

    @Override
    public List<RecruimentNews> getRecruimentNewsLst(GetRecuitmentNewsRequester request) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RecruimentNews> query = builder.createQuery(RecruimentNews.class);
        Root root = query.from(RecruimentNews.class);

        query = query.select(root).orderBy(builder.desc(root.get("lastUpdated")));

        List<Predicate> predicates = new ArrayList<>();

//        Condition 1: search value
//        default value is empty 
        Predicate p1 = builder.like(root.get("title").as(String.class),
                String.format("%%%s%%", request.getTitle()));
        predicates.add(p1);

//        Condition 2: min salary
//        defalut value = 0
        //       Condition 3: max salary
//      default value = 0
        if (request.getSalary() != 0) {
            Predicate p2 = builder.lessThanOrEqualTo(root.get("minSalary").as(int.class), request.getSalary());
            predicates.add(p2);

            Predicate p3 = builder.greaterThanOrEqualTo(root.get("maxSalary").as(int.class), request.getSalary());
            predicates.add(p3);
        }

//        Condition 4: min experience year
//        default value = 0
//        Predicate p4 = null;    
//        if(request.getMinExpYear() != 0){
//            p4 = builder.greaterThan(root.get("minExperienceYear").as(int.class), request.getMinExpYear());
//            predicates.add(p4);
//        }
//        Condition 5: district id
        if (request.getDistrictId() != 0) {
            District district = session.get(District.class, request.getDistrictId());
            Predicate p5 = builder.equal(root.get("district").as(District.class), district);
            predicates.add(p5);
        }

//      Condiontion 6: major id
        if (request.getMajorId() != 0) {
            Major major = session.get(Major.class, request.getMajorId());
            Predicate p6 = builder.equal(root.get("major").as(Major.class), major);
            predicates.add(p6);
        }

//      Condition 7: Job type  
        if (!StringUtils.isEmpty(request.getJobType())) {
            Predicate p7 = builder.equal(root.get("jobType").as(String.class), request.getJobType().toUpperCase().trim());
            predicates.add(p7);
        }

//        Combine condition
        Predicate[] predicateArr = new Predicate[predicates.size()];
        predicates.toArray(predicateArr);

        query.where(predicateArr);

        Query q = session.createQuery(query);
        q.setMaxResults(PAGING);
        q.setFirstResult((request.getPageNum() - 1) * PAGING);

        return q.getResultList();
    }

    @Override
    public int count(GetRecuitmentNewsRequester request) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RecruimentNews> query = builder.createQuery(RecruimentNews.class);
        Root root = query.from(RecruimentNews.class);

        query = query.select(root).orderBy(builder.desc(root.get("lastUpdated")));

        List<Predicate> predicates = new ArrayList<>();

//        Condition 1: search value
//        default value is empty 
        Predicate p1 = builder.like(root.get("title").as(String.class),
                String.format("%%%s%%", request.getTitle()));
        predicates.add(p1);

//        Condition 2: min salary
//        defalut value = 0
        //       Condition 3: max salary
//      default value = 0
        if (request.getSalary() != 0) {
            Predicate p2 = builder.lessThanOrEqualTo(root.get("minSalary").as(int.class), request.getSalary());
            predicates.add(p2);

            Predicate p3 = builder.greaterThanOrEqualTo(root.get("maxSalary").as(int.class), request.getSalary());
            predicates.add(p3);
        }

//        Condition 4: min experience year
//        default value = 0
//        Predicate p4 = null;    
//        if(request.getMinExpYear() != 0){
//            p4 = builder.greaterThan(root.get("minExperienceYear").as(int.class), request.getMinExpYear());
//            predicates.add(p4);
//        }
//        Condition 5: district id
        if (request.getDistrictId() != 0) {
            District district = session.get(District.class, request.getDistrictId());
            Predicate p5 = builder.equal(root.get("district").as(District.class), district);
            predicates.add(p5);
        }

//      Condiontion 6: major id
        if (request.getMajorId() != 0) {
            Major major = session.get(Major.class, request.getMajorId());
            Predicate p6 = builder.equal(root.get("major").as(Major.class), major);
            predicates.add(p6);
        }

//      Condition 7: Job type  
        if (!StringUtils.isEmpty(request.getJobType())) {
            Predicate p7 = builder.equal(root.get("jobType").as(String.class), request.getJobType().toUpperCase().trim());
            predicates.add(p7);
        }

//        Combine condition
        Predicate[] predicateArr = new Predicate[predicates.size()];
        predicates.toArray(predicateArr);

        query.where(predicateArr);

        Query q = session.createQuery(query);

        return q.getResultList().size();
    }

    @Override
    public boolean add(RecruimentNews rn) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Major major = session.get(Major.class, rn.getMajorId());
            if (major.getId() != 0) {
                rn.setMajor(major);
                session.save(rn);
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error at RecruimentNewsRepsitory: " + e.getMessage());
        }
        return false;
    }

    @Override
    public RecruimentNews getRecruimentNewsDetail(String recruitmentId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        RecruimentNews recruimentNews = session.get(RecruimentNews.class, recruitmentId);

        List<CurriculumVitae> curriculumVitaes = null;
        if (recruimentNews.getrNewsCVs().size() > 0) {
            List<RNewsCV> rNewsCVs = new ArrayList<RNewsCV>(recruimentNews.getrNewsCVs());
            if (rNewsCVs.size() > 0) {
                curriculumVitaes = new ArrayList<>();
                for (RNewsCV rNewsCV : rNewsCVs) {
                    curriculumVitaes.add(rNewsCV.getCurriculumVitae());
                }
                recruimentNews.setCurriculumVitaes(curriculumVitaes);
            }
        }

//        result include: 1 RecruitmentNews and List<CurriculumVitae> 
        return recruimentNews;
    }

    @Override
    public List<RecruimentNews> getRecruimentNewsSetByUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RecruimentNews> query = builder.createQuery(RecruimentNews.class);
        Root root = query.from(RecruimentNews.class);
        query = query.select(root).orderBy(builder.desc(root.get("lastUpdated")));

        Predicate p1 = builder.equal(root.get("user").as(User.class), user);
        query.where(p1);

        Query q = session.createQuery(query);

        return q.getResultList();
    }

}
