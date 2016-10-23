/*\/**
 * Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 * This software is the confidential and proprietary information of wavemaker-com * You shall not disclose such Confidential Information and shall use it only in accordance
 * with the terms of the source code license agreement you entered into with wavemaker.com *\/*/
package com.wavemaker.apps.mobile.fingenie.fingenie.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.wavemaker.apps.mobile.fingenie.fingenie.Bank;
import com.wavemaker.apps.mobile.fingenie.fingenie.Offer;


/**
 * ServiceImpl object for domain model class Bank.
 *
 * @see Bank
 */
@Service("FinGenie.BankService")
public class BankServiceImpl implements BankService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
	@Qualifier("FinGenie.OfferService")
	private OfferService offerService;

    @Autowired
    @Qualifier("FinGenie.BankDao")
    private WMGenericDao<Bank, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Bank, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "FinGenieTransactionManager")
    @Override
	public Bank create(Bank bank) {
        LOGGER.debug("Creating a new Bank with information: {}", bank);
        Bank bankCreated = this.wmGenericDao.create(bank);
        if(bankCreated.getOffers() != null) {
            for(Offer offer : bankCreated.getOffers()) {
                offer.setBank(bankCreated);
                LOGGER.debug("Creating a new child Offer with information: {}", offer);
                offerService.create(offer);
            }
        }
        return bankCreated;
    }

	@Transactional(readOnly = true, value = "FinGenieTransactionManager")
	@Override
	public Bank getById(Integer bankIdInstance) throws EntityNotFoundException {
        LOGGER.debug("Finding Bank by id: {}", bankIdInstance);
        Bank bank = this.wmGenericDao.findById(bankIdInstance);
        if (bank == null){
            LOGGER.debug("No Bank found with id: {}", bankIdInstance);
            throw new EntityNotFoundException(String.valueOf(bankIdInstance));
        }
        return bank;
    }

    @Transactional(readOnly = true, value = "FinGenieTransactionManager")
	@Override
	public Bank findById(Integer bankIdInstance) {
        LOGGER.debug("Finding Bank by id: {}", bankIdInstance);
        return this.wmGenericDao.findById(bankIdInstance);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "FinGenieTransactionManager")
	@Override
	public Bank update(Bank bank) throws EntityNotFoundException {
        LOGGER.debug("Updating Bank with information: {}", bank);
        this.wmGenericDao.update(bank);

        Integer bankIdInstance = bank.getBankId();

        return this.wmGenericDao.findById(bankIdInstance);
    }

    @Transactional(value = "FinGenieTransactionManager")
	@Override
	public Bank delete(Integer bankIdInstance) throws EntityNotFoundException {
        LOGGER.debug("Deleting Bank with id: {}", bankIdInstance);
        Bank deleted = this.wmGenericDao.findById(bankIdInstance);
        if (deleted == null) {
            LOGGER.debug("No Bank found with id: {}", bankIdInstance);
            throw new EntityNotFoundException(String.valueOf(bankIdInstance));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "FinGenieTransactionManager")
	@Override
	public Page<Bank> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Banks");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "FinGenieTransactionManager")
    @Override
    public Page<Bank> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Banks");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "FinGenieTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service FinGenie for table Bank to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "FinGenieTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "FinGenieTransactionManager")
    @Override
    public Page<Offer> findAssociatedOffers(Integer bankId, Pageable pageable) {
        LOGGER.debug("Fetching all associated offers");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("bank.bankId = '" + bankId + "'");

        return offerService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service OfferService instance
	 */
	protected void setOfferService(OfferService service) {
        this.offerService = service;
    }

}
