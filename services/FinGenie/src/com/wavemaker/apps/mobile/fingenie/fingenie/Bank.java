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
 * Bank generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`BANK`")
public class Bank implements Serializable {

    private Integer bankId;
    private String bankName;
    private Long phoneNum;
    private String email;
    private Date lastUpdated;
    private String iconurl;
    private List<Offer> offers = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`BANK_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getBankId() {
        return this.bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    @Column(name = "`BANK_NAME`", nullable = false, length = 255)
    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "`PHONE_NUM`", nullable = true, scale = 0, precision = 20)
    public Long getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Column(name = "`EMAIL`", nullable = true, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`LAST_UPDATED`", nullable = false)
    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Column(name = "`ICONURL`", nullable = true, length = 255)
    public String getIconurl() {
        return this.iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "bank")
    public List<Offer> getOffers() {
        return this.offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank)) return false;
        final Bank bank = (Bank) o;
        return Objects.equals(getBankId(), bank.getBankId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBankId());
    }
}

