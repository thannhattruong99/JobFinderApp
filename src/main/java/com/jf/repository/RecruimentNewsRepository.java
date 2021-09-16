/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository;

import com.jf.pojos.RecruimentNews;
import com.jf.pojos.User;
import com.jf.request.GetRecuitmentNewsRequester;
import java.util.List;

/**
 *
 * @author truongtn
 */
public interface RecruimentNewsRepository {
    List<RecruimentNews> getRecruimentNewsLst(GetRecuitmentNewsRequester request);
    int count(GetRecuitmentNewsRequester request);
    boolean add(RecruimentNews rn);
    RecruimentNews getRecruimentNewsDetail(String recruitmentId);
    List<RecruimentNews> getRecruimentNewsSetByUser(User user);
}
