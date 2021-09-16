/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author truongtn
 */
public interface AWSService {
    String uploadFile(MultipartFile multipartFile);
}
