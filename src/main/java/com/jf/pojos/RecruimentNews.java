/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author truongtn
 */
@Entity
@Table(name = "RecruitmentNews")
public class RecruimentNews implements Serializable,Comparable<RecruimentNews> {

    public static final String FULL_TIME = "FULL_TIME";
    public static final String PART_TIME = "PART_TIME";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @NotEmpty(message = "{err.title.empty}")
    private String title;
    private String description;
    @Column(name = "min_salary")
    @Min(value = 1, message = "{err.salary.negative}")
    private int minSalary;
    @Min(value = 1, message = "{err.salary.negative}")
    @Column(name = "max_salary")
    private int maxSalary;
    @Column(name = "min_experience_year")
    private int minExperienceYear;
    private String address;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;
    @Column(name = "job_type")
    private String jobType;
    private boolean active;
    @Transient
    private String employerId;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
    @Transient
    private int districtId;
    @Min(value = 1, message = "{err.majorid.empty}")
    @Transient
    private int majorId;
    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
    private String image;
    @Transient
    private MultipartFile file;
    @OneToMany(mappedBy = "recruitmentNews", fetch = FetchType.EAGER)
    private Set<RNewsCV> rNewsCVs;
    @Transient
    private List<CurriculumVitae> curriculumVitaes;

    public List<CurriculumVitae> getCurriculumVitaes() {
        return curriculumVitaes;
    }

    public void setCurriculumVitaes(List<CurriculumVitae> curriculumVitaes) {
        this.curriculumVitaes = curriculumVitaes;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Set<RNewsCV> getrNewsCVs() {
        return rNewsCVs;
    }

    public void setrNewsCVs(Set<RNewsCV> rNewsCVs) {
        this.rNewsCVs = rNewsCVs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getMinExperienceYear() {
        return minExperienceYear;
    }

    public void setMinExperienceYear(int minExperienceYear) {
        this.minExperienceYear = minExperienceYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public int compareTo(RecruimentNews o) {
        return lastUpdated.compareTo(o.getLastUpdated());
    }

}
