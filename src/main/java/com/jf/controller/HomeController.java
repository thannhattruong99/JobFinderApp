/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import com.jf.service.impl.RecruimentNewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author truongtn
 */
@Controller
public class HomeController {
    @Autowired
    private RecruimentNewsServiceImpl recruimentNewsService;
    
     
}
