/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository;

import com.jf.pojos.Organization;
import java.util.List;

/**
 *
 * @author truongtn
 */
public interface OrganizationRepository {
    List<Organization> getOrganizations();
}
