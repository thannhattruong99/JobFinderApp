/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service;

import com.jf.pojos.RecruimentNews;
import com.jf.request.GetRecuitmentNewsRequester;
import java.util.List;

/**
 *
 * @author truongtn
 */
public interface RecruimentNewsService {
    List<RecruimentNews> getRecruitmentNewsLst(GetRecuitmentNewsRequester request);
    int count(GetRecuitmentNewsRequester request);
    boolean add(RecruimentNews rn, String username);
    RecruimentNews getRecruimentNewsDetail(String recruitmentId);
    List<RecruimentNews> getRecruimentNewsLst(String username);
}
