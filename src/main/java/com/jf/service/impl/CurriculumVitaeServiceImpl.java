/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.jf.pojos.CurriculumVitae;
import com.jf.repository.CurriculumVitaeRepository;
import com.jf.repository.UserRepository;
import com.jf.service.AWSService;
import com.jf.service.CurriculumVitaeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service
public class CurriculumVitaeServiceImpl implements CurriculumVitaeService{

    @Autowired
    private CurriculumVitaeRepository curriculumVitaeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AWSService awsService;

    @Override
    public boolean add(CurriculumVitae cv) {
        if(cv.getFile() != null){
            cv.setUrl(awsService.uploadFile(cv.getFile()));
        }
        return curriculumVitaeRepository.add(cv);
    }

    @Override
    public boolean update(CurriculumVitae cv) {
        if(cv.getFile() != null){
            cv.setUrl(awsService.uploadFile(cv.getFile()));
        }
        
        return curriculumVitaeRepository.update(cv);
    }

    @Override
    public boolean delete(String id) {
        return curriculumVitaeRepository.deleteCV(id);
    }

    @Override
    public List<CurriculumVitae> getCVByUserName(String username) {
        return new ArrayList<>(userRepository.getUserDetailByUsername(username).getCurriculumVitaes());
    }
}
