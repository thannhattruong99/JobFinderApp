/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository;

import com.jf.pojos.User;
import com.jf.request.GetUsersRequest;
import java.util.List;

/**
 *
 * @author truongtn
 */
public interface UserRepository {
    List<User> getUsers(String username, boolean isActive);
    List<User> getUsers(GetUsersRequest request);
    int count(String fullname, String statusMode);
    boolean add(User user) throws Exception;
    boolean changeStatus(String id, boolean status);
    User getUserDetailById(String id);
    User getUserDetailByUsername(String username);
}
