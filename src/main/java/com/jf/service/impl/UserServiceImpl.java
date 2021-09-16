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
import com.jf.pojos.RecruimentNews;
import com.jf.pojos.User;
import com.jf.repository.impl.DistrictRepositoryImpl;
import com.jf.repository.impl.MajorRepositoryImpl;
import com.jf.repository.impl.OrganizationRepositoryImpl;
import com.jf.repository.impl.RatingRepositoryImpl;
import com.jf.repository.impl.UserRepositoryImpl;
import com.jf.request.GetUsersRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private OrganizationRepositoryImpl organizationRepository;
    @Autowired
    private DistrictRepositoryImpl districtRepository;
    @Autowired
    private MajorRepositoryImpl majorRepository;
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private RatingRepositoryImpl ratingRepository;
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

    public int count(String fullname, String statusMode) {
        return userRepository.count(fullname, statusMode);
    }

    public List<User> getUsers(GetUsersRequest request) {
        return userRepository.getUsers(request);
    }

    public boolean changeStatus(String id, boolean active) {
        return userRepository.changeStatus(id, active);
    }

    public List<City> getCities() {
        return districtRepository.getCities();
    }

    public List<Major> getMajors() {
        return majorRepository.getMajors();
    }

    public List<Organization> getOrganizations() {
        return organizationRepository.getOrganizations();
    }

    public User getUserDetailByUsername(String username) {
        return userRepository.getUserDetailByUsername(username);
    }

    public User getUserDetail(String sender, String receiver) {
        User reciver = userRepository.getUserDetailByUsername(receiver);

        if (!StringUtils.isEmpty(sender)) {
            User senderInfor = userRepository.getUserDetailByUsername(sender);

            if (senderInfor.getRole().equals(User.CANDIDATE) && reciver.getReceivedRatings().size() > 0) {
                if(ratingRepository.rating(senderInfor, reciver) != null){
                    reciver.setSenderScore(ratingRepository.rating(senderInfor, reciver).getScore());
                }
            }
        }

        return reciver;
    }

    public List<RecruimentNews> getRecruimentNewsLst(String username) {
        return new ArrayList<RecruimentNews>(this.userRepository.getRecruimentNewsSetByUser(username)
                .getRecruimentNewsSet());
    }
}
