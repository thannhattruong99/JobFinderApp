/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.jf.pojos.RNewsCV;
import com.jf.repository.impl.RNewsCVRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service
public class RNewsCVServiceImpl {
    @Autowired
    private RNewsCVRepositoryImpl rNewsCVRepository;
    
    public boolean add(String cvId, String rnId){
        RNewsCV rncv = new RNewsCV(cvId, rnId);
        return rNewsCVRepository.add(rncv);
    }
}
