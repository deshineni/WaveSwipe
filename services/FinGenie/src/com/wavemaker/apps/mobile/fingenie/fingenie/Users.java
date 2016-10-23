/*\/**
 * Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 * This software is the confidential and proprietary information of wavemaker-com * You shall not disclose such Confidential Information and shall use it only in accordance
 * with the terms of the source code license agreement you entered into with wavemaker.com *\/*/
package com.wavemaker.apps.mobile.fingenie.fingenie;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Users generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`USERS`")
public class Users implements Serializable {

    private Integer userId;
    private String firstName;
    private String lastName;
    private Integer phoneNum;
    private String email;
    private String city;
    private Integer pincode;
    private String location;
    private Integer age;
    private Boolean gender;
    private Date dob;
    private Integer annualSalary;
    private Boolean married;
    private Date salaried;
    private Boolean ownHouse;
    private List<Session> sessions = new ArrayList<>();
    private List<OfferFeedback> offerFeedbacks = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`USER_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "`FIRST_NAME`", nullable = true, length = 255)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "`LAST_NAME`", nullable = true, length = 255)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "`PHONE_NUM`", nullable = true, scale = 0, precision = 10)
    public Integer getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Column(name = "`EMAIL`", nullable = true, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "`CITY`", nullable = true, length = 255)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "`PINCODE`", nullable = true, scale = 0, precision = 10)
    public Integer getPincode() {
        return this.pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    @Column(name = "`LOCATION`", nullable = true, length = 255)
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "`AGE`", nullable = true, scale = 0, precision = 10)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "`GENDER`", nullable = true)
    public Boolean getGender() {
        return this.gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "`DOB`", nullable = true)
    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name = "`ANNUAL SALARY`", nullable = true, scale = 0, precision = 10)
    public Integer getAnnualSalary() {
        return this.annualSalary;
    }

    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }

    @Column(name = "`MARRIED`", nullable = true)
    public Boolean getMarried() {
        return this.married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`SALARIED`", nullable = false)
    public Date getSalaried() {
        return this.salaried;
    }

    public void setSalaried(Date salaried) {
        this.salaried = salaried;
    }

    @Column(name = "`OWN_HOUSE`", nullable = true)
    public Boolean getOwnHouse() {
        return this.ownHouse;
    }

    public void setOwnHouse(Boolean ownHouse) {
        this.ownHouse = ownHouse;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<Session> getSessions() {
        return this.sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "users")
    public List<OfferFeedback> getOfferFeedbacks() {
        return this.offerFeedbacks;
    }

    public void setOfferFeedbacks(List<OfferFeedback> offerFeedbacks) {
        this.offerFeedbacks = offerFeedbacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        final Users users = (Users) o;
        return Objects.equals(getUserId(), users.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}

