/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import com.jf.pojos.Comment;
import com.jf.pojos.CurriculumVitae;
import com.jf.pojos.Rating;
import com.jf.pojos.User;
import com.jf.request.GetUsersRequest;
import com.jf.service.impl.UserServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
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

/**
 *
 * @author truongtn
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userDetailService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registry(Model model,
            @ModelAttribute(value = "user") @Valid User user,
            BindingResult result) {
        String errMsg = "";
        if (!result.hasErrors()) {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                errMsg = this.userDetailService.add(user);
                if (StringUtils.isEmpty(errMsg)) {
                    return "redirect:login";
                } else {
                    model.addAttribute("errMsg", errMsg);
                }
            } else {
                errMsg = "Re-type password is not correct!";
                model.addAttribute("errMsg", errMsg);
            }

        }
        user.setConfirmPassword("");
        user.setPassword("");
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("organizations", this.userDetailService.getOrganizations());
        return "register";
    }

    @GetMapping("/register")
    public String registry(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("organizations", this.userDetailService.getOrganizations());

        return "register";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model, @RequestParam(value = "username") String username) {

        model.addAttribute("user", this.userDetailService.getUserDetailByUsername(username));
        model.addAttribute("cities", this.userDetailService.getCities());
        model.addAttribute("majors", this.userDetailService.getMajors());
        model.addAttribute("organizations", this.userDetailService.getOrganizations());
        model.addAttribute("curriculum", new CurriculumVitae());

        return "profile";
    }

    @PostMapping("/update-profile")
    public String editProfile(Model model,
            @ModelAttribute(value = "user") User user) {
        return "redirect:/profile?username=" + user.getUsername();
    }

    @GetMapping("/admin/users")
    public String list(Model model, @RequestParam Map<String, String> params) {
        String fullname = params.getOrDefault("fullname", "");
        int pageNum = Integer.parseInt(params.getOrDefault("pageNum", "1"));
        String statusModeStr = params.getOrDefault("statusMode", "0");

        GetUsersRequest request = new GetUsersRequest(fullname, pageNum, statusModeStr);
        List<User> users = userDetailService.getUsers(request);
        int totalPage = userDetailService.count(request.getFullname(), request.getStatusMode());

        model.addAttribute("users", users);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchValue", request);

        return "users";
    }

    @PostMapping("/admin/user/status")
    public String changeUserStatus(Model model, @RequestParam Map<String, String> params) {
        String userId = params.get("id");
        boolean active = Boolean.parseBoolean(params.get("active"));

        userDetailService.changeStatus(userId, !active);

        return "redirect:/admin/users";
    }

    @GetMapping("/user")
    public String getUser(Model model, 
            @RequestParam(value = "sender") String sender,
            @RequestParam(value = "receiver") String receiver) {

        model.addAttribute("user", userDetailService.getUserDetail(sender, receiver));
        model.addAttribute("comment", new Comment());
        model.addAttribute("rating", new Rating());

        return "user";
    }
}
