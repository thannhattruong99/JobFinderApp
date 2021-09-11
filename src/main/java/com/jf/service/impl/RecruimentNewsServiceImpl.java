/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.jf.pojos.RecruimentNews;
import com.jf.repository.impl.RecruimentNewsRepositoryImpl;
import com.jf.request.GetRecuitmentNewsRequester;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service
public class RecruimentNewsServiceImpl {

    @Autowired
    private RecruimentNewsRepositoryImpl recruimentNewsRepository;

    public List<RecruimentNews> getRecruitmentNewsLst(GetRecuitmentNewsRequester request) {
        try {
            request.setSalary(Integer.parseInt(request.getSalaryStr()));
        } catch (Exception e) {
            request.setSalary(0);
        }
        return recruimentNewsRepository.getRecruimentNewsLst(request);
    }

    public int count(GetRecuitmentNewsRequester request) {
        try {
            request.setSalary(Integer.parseInt(request.getSalaryStr()));
        } catch (Exception e) {
            request.setSalary(0);
        }
        return recruimentNewsRepository.count(request);

    }
}
