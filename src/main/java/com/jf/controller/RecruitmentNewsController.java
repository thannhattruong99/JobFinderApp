/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import com.jf.request.GetRecuitmentNewsRequester;
import com.jf.service.impl.CurriculumVitaeServiceImpl;
import com.jf.service.impl.RecruimentNewsServiceImpl;
import com.jf.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author truongtn
 */
@Controller
public class RecruitmentNewsController {

    @Autowired
    private RecruimentNewsServiceImpl recruimentNewsService;
    @Autowired
    private UserServiceImpl userDetailService;
    @Autowired
    private CurriculumVitaeServiceImpl curriculumVitaeService;

    public String getRecruitmentNewsLst(Model model, @ModelAttribute("recruimentNews") GetRecuitmentNewsRequester request) {

        return "index";
    }

//    @RequestMapping("/")
//     public String index(Model model, @Validated GetRecuitmentNewsRequester searchValue, BindingResult result) {
//        //if tiles.xml has baselayout -> return page
//        //if tiles.xml has no baselayout -> find in InternalResourceViewResolver -> return page
//        
////        String title = params.getOrDefault("title", "");
////        int minSalary = Integer.parseInt(params.getOrDefault("minSalary", "0").);
////        int maxSalary = Integer.parseInt(params.getOrDefault("maxSalary", "0"));
////        int minExpYear = Integer.parseInt(params.getOrDefault("minExpYear", "0"));
////        int districtId = Integer.parseInt(params.getOrDefault("districtId", "0"));
////        int majorId = Integer.parseInt(params.getOrDefault("majorId", "0"));
////        int pageNum = Integer.parseInt(params.getOrDefault("pageNum", "1"));
//        
////        GetRecuitmentNewsRequester request = 
////                new GetRecuitmentNewsRequester(title, minSalary, maxSalary, 
////                        minExpYear, majorId, districtId, pageNum);
////        
////        model.addAttribute("recruitmentNewsLst", this.recruimentNewsService.getRecruitmentNewsLst(request));
//        model.addAttribute("cities", this.userDetailService.getCities());
//        model.addAttribute("majors", this.userDetailService.getMajors());
////        model.addAttribute("totalRecord", this.recruimentNewsService.count(request));
//        model.addAttribute("searchValue", new GetRecuitmentNewsRequester());
//
//        if(result.hasErrors()){
//            System.out.println("CO LOI ROI NE");
//            List<String> errors = responseErrorResult(result);
//            model.addAttribute("errors", errors);
//        }
//        
//        return "index";
//    }
    @RequestMapping("/")
    public String index(Model model) {
        //if tiles.xml has baselayout -> return page
        //if tiles.xml has no baselayout -> find in InternalResourceViewResolver -> return page

        GetRecuitmentNewsRequester request
                = new GetRecuitmentNewsRequester("", "", "", 0, 0, 1);

        model.addAttribute("recruitmentNewsLst", this.recruimentNewsService.getRecruitmentNewsLst(request));
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("totalRecord", this.recruimentNewsService.count(request));
        model.addAttribute("searchValue", request);

        return "index";
    }

    @PostMapping("/")
    public String search(Model model,
            @ModelAttribute("searchValue") @Valid GetRecuitmentNewsRequester request,
            BindingResult result) {
        //if tiles.xml has baselayout -> return page
        //if tiles.xml has no baselayout -> find in InternalResourceViewResolver -> return page

        model.addAttribute("recruitmentNewsLst", this.recruimentNewsService.getRecruitmentNewsLst(request));
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("totalRecord", this.recruimentNewsService.count(request));
        model.addAttribute("searchValue", request);

        return "index";
    }
    
    @RequestMapping("/recruitments")
    public String getRecruitmentByUser(Model model, @RequestParam(name = "username") String username) {
        //if tiles.xml has baselayout -> return page
        //if tiles.xml has no baselayout -> find in InternalResourceViewResolver -> return page

        model.addAttribute("recruitmentNewsLst", this.userDetailService.getRecruimentNewsLst(username));
       

        return "recruitment-news";
    }

    public List<String> responseErrorResult(BindingResult result) {
        List<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            for (Object object : result.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    errors.add(fieldError.getDefaultMessage());
                }
            }

        }
        return errors;
    }
}
