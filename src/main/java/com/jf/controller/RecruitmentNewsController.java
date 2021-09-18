/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import com.jf.pojos.RecruimentNews;
import com.jf.request.GetRecuitmentNewsRequester;
import com.jf.service.RecruimentNewsService;
import com.jf.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
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
    private RecruimentNewsService recruimentNewsService;
    @Autowired
    private UserService userDetailService;

    public String getRecruitmentNewsLst(Model model, @ModelAttribute("recruimentNews") GetRecuitmentNewsRequester request) {

        return "index";
    }

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

        model.addAttribute("recruitmentNewsLst", this.recruimentNewsService.getRecruimentNewsLst(username));

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

    @GetMapping("/create-recruitment")
    public String add(Model model) {
        model.addAttribute("rn", new RecruimentNews());
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());

        return "create-recruitment-news";
    }

    @PostMapping("/create-recruitment")
    public String add(Model model,
            @ModelAttribute(name = "rn") @Valid RecruimentNews rn,
            @RequestParam("username") String username,
            BindingResult result) {
        model.addAttribute("rn", new RecruimentNews());

        if (!result.hasErrors()) {
            if (recruimentNewsService.add(rn, username)) {
                return "redirect:/recruitments?username=" + username;
            }
        }

        model.addAttribute("errMsg", "Create recruitment failed. Please try again!! ");
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());

        return "create-recruitment-news";
    }

    @GetMapping("/view-detail")
    public String viewDetail(Model model, @RequestParam(name = "rnId") String rnId) {

        model.addAttribute("rn", recruimentNewsService.getRecruimentNewsDetail(rnId));

        return "recruitment-news-detail";
    }
}
