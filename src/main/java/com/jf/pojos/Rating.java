/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.pojos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author truongtn
 */
@Entity
@Table(name = "Rating")
public class Rating implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Min(value = 1, message = "{err.score.value}")
    @Max(value = 5, message = "{err.score.value}")
    private int score;
    @Transient
    private String empoyerId;
    @Transient
    private String candidateId;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User employer;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private User candidate;
    @Transient
    @NotEmpty(message = "{err.sender.empty}")
    private String sender;
    @Transient
    @NotEmpty(message = "{err.receiver.empty}")
    private String receiver;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    

    public String getEmpoyerId() {
        return empoyerId;
    }

    public void setEmpoyerId(String empoyerId) {
        this.empoyerId = empoyerId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

}
