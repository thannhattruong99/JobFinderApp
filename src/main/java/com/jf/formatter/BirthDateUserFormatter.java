/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.formatter;

import com.jf.pojos.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author truongtn
 */
public class BirthDateUserFormatter implements Formatter<User> {

    @Override
    public String print(User t, Locale locale) {
        return String.valueOf(t.getBirthDate());
    }

    @Override
    public User parse(String string, Locale locale) throws ParseException {
         User user = new User();
        try {
  
        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(string);
        user.setBirthDate(birthDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

}
