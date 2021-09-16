/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service;

import com.jf.pojos.City;
import com.jf.pojos.Major;
import com.jf.pojos.Organization;
import com.jf.pojos.User;
import com.jf.request.GetUsersRequest;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author truongtn
 */
public interface UserService extends UserDetailsService{
    String add(User user);
    int count(String fullname, String statusMode);
    List<User> getUsers(GetUsersRequest request);
    boolean changeStatus(String id, boolean active);
    List<City> getCities();
    List<Major> getMajors();
    List<Organization> getOrganizations();
    User getUserDetailByUsername(String username);
    User getUserDetail(String sender, String receiver);
}
