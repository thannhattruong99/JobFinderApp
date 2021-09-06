/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import com.jf.service.impl.DistrictCityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author truongtn
 */
@Controller
@ControllerAdvice
public class DistrictCityController {
    @Autowired
    private DistrictCityServiceImpl districtCityService;
    
//    @ModelAttribute
//    public void commonAttr(Model model){
//        model.addAttribute("cities", this.districtCityService.getCities());
//    }
    
}
