/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jf.pojos.RecruimentNews;
import com.jf.pojos.User;
import com.jf.repository.impl.RecruimentNewsRepositoryImpl;
import com.jf.repository.impl.UserRepositoryImpl;
import com.jf.request.GetRecuitmentNewsRequester;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private Cloudinary cloudinary;

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

    public boolean add(RecruimentNews rn, String username) {
        User user = userRepository.getUserDetailByUsername(username);
        rn.setUser(user);

        try {
            if (!rn.getFile().isEmpty()) {
                Map r = this.cloudinary.uploader().upload(rn.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                rn.setImage((String) r.get("secure_url"));
            }
        } catch (IOException ex) {
            System.out.println("Error at RecruimentNewsServiceImpl: " + ex.getMessage());
            return false;
        }

        return recruimentNewsRepository.add(rn);
    }

    public RecruimentNews getRecruimentNewsDetail(String recruitmentId) {
        return recruimentNewsRepository.getRecruimentNewsDetail(recruitmentId);
    }
}
