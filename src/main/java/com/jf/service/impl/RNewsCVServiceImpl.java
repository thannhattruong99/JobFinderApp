/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.jf.pojos.RNewsCV;
import com.jf.repository.RNewsCVRepository;
import com.jf.service.RNewsCVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service
public class RNewsCVServiceImpl implements RNewsCVService{
    @Autowired
    private RNewsCVRepository rNewsCVRepository;
    
    @Override
    public boolean add(String cvId, String rnId){
        RNewsCV rncv = new RNewsCV(cvId, rnId);
        return rNewsCVRepository.add(rncv);
    }
}
