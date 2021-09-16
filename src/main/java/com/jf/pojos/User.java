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
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author truongtn
 */
@Entity
@Table(name = "User")
public class User implements Serializable {

    public static final String ALL = "0";
    public static final String ACTIVE = "1";
    public static final String INACTIVE = "2";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String CANDIDATE = "ROLE_CANDIDATE";
    public static final String EMPLOYER = "ROLE_EMPLOYER";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @NotEmpty(message = "{err.username.empty}")
    private String username;
    @NotEmpty(message = "{err.password.empty}")
    private String password;
    @NotEmpty(message = "{err.fullname.empty}")
    private String fullname;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String role;
    private String avatar;
    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @Transient
    private String organizationId;
    @Column(name = "district_id")
    private int districtId;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
    @Transient
    private int majorId;
    @Transient
    private String confirmPassword;
    @Transient
//    @NotNull(message = "{err.file.empty}")
    private MultipartFile file;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<CurriculumVitae> curriculumVitaes;
    @OneToMany(mappedBy = "user")
    private Set<RecruimentNews> recruimentNewsSet;
    @OneToMany(mappedBy = "employer", fetch = FetchType.EAGER)
    private Set<Comment> receivedComments;
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
    private Set<Comment> sentComments;
    @Transient
    private List<Comment> comments;
    @OneToMany(mappedBy = "employer", fetch = FetchType.EAGER)
    private Set<Rating> receivedRatings;
    @OneToMany(mappedBy = "candidate", fetch = FetchType.EAGER)
    private Set<Rating> sentRatings;
    @Transient
    private float averageScore;
    @Transient
    private int numberOfVote;
    @Transient
    private int senderScore;
    @Transient
    private List<RecruimentNews> recruimentNewsLst;

    public List<RecruimentNews> getRecruimentNewsLst() {
        return recruimentNewsLst;
    }

    public void setRecruimentNewsLst(List<RecruimentNews> recruimentNewsLst) {
        this.recruimentNewsLst = recruimentNewsLst;
    }

    public int getNumberOfVote() {
        return numberOfVote;
    }

    public void setNumberOfVote(int numberOfVote) {
        this.numberOfVote = numberOfVote;
    }

    public int getSenderScore() {
        return senderScore;
    }

    public void setSenderScore(int senderScore) {
        this.senderScore = senderScore;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public Set<Rating> getReceivedRatings() {
        return receivedRatings;
    }

    public void setReceivedRatings(Set<Rating> receivedRatings) {
        this.receivedRatings = receivedRatings;
    }

    public Set<Rating> getSentRatings() {
        return sentRatings;
    }

    public void setSentRatings(Set<Rating> sentRatings) {
        this.sentRatings = sentRatings;
    }

    public Set<Comment> getReceivedComments() {
        return receivedComments;
    }

    public void setReceivedComments(Set<Comment> receivedComments) {
        this.receivedComments = receivedComments;
    }

    public Set<Comment> getSentComments() {
        return sentComments;
    }

    public void setSentComments(Set<Comment> sentComments) {
        this.sentComments = sentComments;
    }

    public Set<RecruimentNews> getRecruimentNewsSet() {
        return recruimentNewsSet;
    }

    public void setRecruimentNewsSet(Set<RecruimentNews> recruimentNewsSet) {
        this.recruimentNewsSet = recruimentNewsSet;
    }

    public Set<CurriculumVitae> getCurriculumVitaes() {
        return curriculumVitaes;
    }

    public void setCurriculumVitaes(Set<CurriculumVitae> curriculumVitaes) {
        this.curriculumVitaes = curriculumVitaes;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
