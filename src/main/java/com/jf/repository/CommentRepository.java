/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository;

import com.jf.pojos.Comment;

/**
 *
 * @author truongtn
 */
public interface CommentRepository {
    boolean add(Comment comment);
}
