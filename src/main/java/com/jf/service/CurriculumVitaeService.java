/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service;

import com.jf.pojos.CurriculumVitae;
import java.util.List;

/**
 *
 * @author truongtn
 */
public interface CurriculumVitaeService {
    boolean add(CurriculumVitae cv);
    boolean update(CurriculumVitae cv);
    boolean delete(String id);
    List<CurriculumVitae> getCVByUserName(String username);
}
