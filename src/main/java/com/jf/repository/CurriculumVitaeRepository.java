/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository;

import com.jf.pojos.CurriculumVitae;

/**
 *
 * @author truongtn
 */
public interface CurriculumVitaeRepository {
    boolean update(CurriculumVitae updateCV);
    boolean add(CurriculumVitae cv);
    boolean deleteCV(String id);
    
}
