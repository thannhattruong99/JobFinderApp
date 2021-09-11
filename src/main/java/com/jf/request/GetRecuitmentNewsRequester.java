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
public class GetRecuitmentNewsRequester implements Serializable {
//    private String id;
    private String title;
    private String salaryStr;
    private String jobType;
    private int salary;
    private int minExpYear;
    private int majorId;
    private int districtId;
    private int pageNum;

    public GetRecuitmentNewsRequester() {
    }

    public GetRecuitmentNewsRequester(String title, String salaryStr, String jobType,int majorId, int districtId, int pageNum) {
        this.title = title;
        this.salaryStr = salaryStr;
        this.jobType = jobType;
        this.majorId = majorId;
        this.districtId = districtId;
        this.pageNum = pageNum;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalaryStr() {
        return salaryStr;
    }

    public void setSalaryStr(String salaryStr) {
        this.salaryStr = salaryStr;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinExpYear() {
        return minExpYear;
    }

    public void setMinExpYear(int minExpYear) {
        this.minExpYear = minExpYear;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

}
