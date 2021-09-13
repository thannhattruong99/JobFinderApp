package com.jf.controller;

import com.jf.pojos.CurriculumVitae;
import com.jf.request.GetRecuitmentNewsRequester;
import com.jf.service.impl.CurriculumVitaeServiceImpl;
import com.jf.service.impl.RNewsCVServiceImpl;
import com.jf.service.impl.RecruimentNewsServiceImpl;
import com.jf.service.impl.UserServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author truongtn
 */
@Controller
public class CurriculumVitaeController {

    @Autowired
    private CurriculumVitaeServiceImpl curriculumVitaeService;
    @Autowired
    private RecruimentNewsServiceImpl recruimentNewsService;
    @Autowired
    private UserServiceImpl userDetailService;
    @Autowired
    private RNewsCVServiceImpl rNewsCVService;
    
     @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @PostMapping("/add-cv")
    public String add(Model model, @ModelAttribute("curriculum")
            @Valid CurriculumVitae cv, BindingResult result,
            @RequestParam(value = "username") String username) {

        if (!result.hasErrors()) {
            if (curriculumVitaeService.add(cv)) {
                return "redirect:/profile?username=" + username;
            } else {
                model.addAttribute("errMsg", "Something wrong");
            }
        }

        model.addAttribute("user", this.userDetailService.getUserDetailByUsername(username));
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("organizations", this.userDetailService.getOrganizations());
        model.addAttribute("curriculum", cv);
        
        return "profile";

    }

    @PostMapping("/update-cv")
    public String update(Model model, @ModelAttribute("curriculum")
            @Valid CurriculumVitae cv, BindingResult result,
            @RequestParam(value = "username") String username) {

        if (!result.hasErrors()) {
            if (curriculumVitaeService.update(cv)) {
                return "redirect:/profile?username=" + username;
            } else {
                model.addAttribute("errMsg", "Something wrong");
            }
        }

        model.addAttribute("user", this.userDetailService.getUserDetailByUsername(username));
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("organizations", this.userDetailService.getOrganizations());

        return "profile";

    }

    @GetMapping("/remove-cv")
    public String remove(Model model,
            @RequestParam(value = "id") String id,
            @RequestParam(value = "username") String username) {
        
        if (curriculumVitaeService.delete(id)) {
            return "redirect:/profile?username=" + username;
        } else {
            model.addAttribute("errMsg", "Something wrong");
        }
        
        model.addAttribute("user", this.userDetailService.getUserDetailByUsername(username));
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("organizations", this.userDetailService.getOrganizations());

        return "profile";
    }
    
    @GetMapping("/cvs")
    public String getCVList(Model model, 
            @RequestParam(name = "username") String username,
            @RequestParam(name = "rnId") String rnId){
        
        model.addAttribute("cvs", this.curriculumVitaeService.getCVByUserName(username));
        model.addAttribute("rnId", rnId);
        
        return "cvs";
    }
    
    @GetMapping("/send-cv")
    public String sendCV(Model model, @RequestParam(name = "cvId") String cvId,
            @RequestParam(name = "rnId") String rnId){
        
        if(rNewsCVService.add(cvId, rnId)){
            model.addAttribute("sucessMsg", "Submit curriculum vitae sucessfully!!");
        }else{
            model.addAttribute("errMsg", "Occured error. Please try again!");
        }
        
        GetRecuitmentNewsRequester request
                = new GetRecuitmentNewsRequester("", "", "", 0, 0, 1);

        model.addAttribute("recruitmentNewsLst", this.recruimentNewsService.getRecruitmentNewsLst(request));
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("totalRecord", this.recruimentNewsService.count(request));
        model.addAttribute("searchValue", request);
        
        return "index";
    }
}
