/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jf.pojos.City;
import com.jf.pojos.Major;
import com.jf.pojos.Organization;
import com.jf.pojos.User;
import com.jf.repository.DistrictRepository;
import com.jf.repository.MajorRepository;
import com.jf.repository.OrganizationRepository;
import com.jf.repository.RatingRepository;
import com.jf.repository.UserRepository;
import com.jf.request.GetUsersRequest;
import com.jf.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        List<User> users = this.userRepository.getUsers(string, true);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist !!!");
        }
        User user = users.get(0);
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), auth);
    }

    @Override
    public String add(User user) {
        String errMsg = "";
        try {
            if (!user.getFile().isEmpty()) {
                Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar((String) r.get("secure_url"));
            }

            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            if (userRepository.add(user)) {
                return errMsg;
            }
        } catch (IOException e) {
            System.out.println("Error at UserService: " + e.getMessage());
            errMsg = "Upload avartar fail. Please try agian!";
        } catch (Exception ex) {
            System.out.println("Error at UserService: " + ex.getMessage());
            errMsg = "Create failed. Please try again!";
        }
        return errMsg;
    }

    @Override
    public int count(String fullname, String statusMode) {
        return userRepository.count(fullname, statusMode);
    }

    @Override
    public List<User> getUsers(GetUsersRequest request) {
        return userRepository.getUsers(request);
    }

    @Override
    public boolean changeStatus(String id, boolean active) {
        return userRepository.changeStatus(id, active);
    }

    @Override
    public List<City> getCities() {
        return districtRepository.getCities();
    }

    @Override
    public List<Major> getMajors() {
        return majorRepository.getMajors();
    }

    @Override
    public List<Organization> getOrganizations() {
        return organizationRepository.getOrganizations();
    }

    @Override
    public User getUserDetailByUsername(String username) {
        return userRepository.getUserDetailByUsername(username);
    }

    @Override
    public User getUserDetail(String sender, String receiver) {
        User result = userRepository.getUserDetailByUsername(receiver);

        if (!StringUtils.isEmpty(sender)) {
            User senderInfor = userRepository.getUserDetailByUsername(sender);

            if (senderInfor.getRole().equals(User.CANDIDATE) && result.getReceivedRatings().size() > 0) {
                if(ratingRepository.rating(senderInfor, result) != null){
                    result.setSenderScore(ratingRepository.rating(senderInfor, result).getScore());
                }
            }
        }

        return result;
    }
}
