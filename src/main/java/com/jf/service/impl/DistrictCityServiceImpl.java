/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.jf.pojos.City;
import com.jf.repository.impl.DistrictRepositoryImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service
public class DistrictCityServiceImpl {
    @Autowired
    private DistrictRepositoryImpl districtRepository;
    
    
    public List<City> getCities(){
        return districtRepository.getCities();
    }
    
}
