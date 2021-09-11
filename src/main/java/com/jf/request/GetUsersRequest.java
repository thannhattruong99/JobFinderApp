/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.request;

import java.io.Serializable;

/**
 *
 * @author truongtn
 */
public class GetUsersRequest implements Serializable {

    private String fullname;
    private int pageNum;
    private String statusMode;

    public GetUsersRequest() {
    }

    public GetUsersRequest(String fullname, int pageNum, String statusMode) {
        this.fullname = fullname;
        this.pageNum = pageNum;
        this.statusMode = statusMode;
    }
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getStatusMode() {
        return statusMode;
    }

    public void setStatusMode(String statusMode) {
        this.statusMode = statusMode;
    }

}
