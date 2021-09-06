/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author truongtn
 */
@Controller
public class HomeController {
    
     @RequestMapping("/")
     public String index(Model model, @RequestParam(required = false) Map<String, String> params) {
        //if tiles.xml has baselayout -> return page
        //if tiles.xml has no baselayout -> find in InternalResourceViewResolver -> return page
        return "index";
    }
}
