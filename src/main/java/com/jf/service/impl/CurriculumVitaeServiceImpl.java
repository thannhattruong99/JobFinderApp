/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.jf.pojos.CurriculumVitae;
import com.jf.repository.impl.CurriculumVitaeRepositoryImpl;
import com.jf.repository.impl.UserRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service
public class CurriculumVitaeServiceImpl {

    @Autowired
    private CurriculumVitaeRepositoryImpl curriculumVitaeRepository;
    @Autowired
    private UserRepositoryImpl userRepository;

    public boolean add(CurriculumVitae cv) {
        return curriculumVitaeRepository.add(cv);
    }

    public boolean update(CurriculumVitae cv) {
        return curriculumVitaeRepository.update(cv);
    }

    public boolean delete(String id) {
        return curriculumVitaeRepository.deleteCV(id);
    }

    public List<CurriculumVitae> getCVByUserName(String username) {
        return new ArrayList<>(userRepository.getUserDetailByUsername(username).getCurriculumVitaes());
    }
}
