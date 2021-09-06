/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author truongtn
 */
@Controller
public class OrganizationController {
    
    @GetMapping("/add-organization")
    public String add(Model model) {
        
        return "add-organization";
    }
    
}
