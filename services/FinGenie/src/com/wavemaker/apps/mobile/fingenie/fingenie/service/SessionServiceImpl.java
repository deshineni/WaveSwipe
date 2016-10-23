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

import com.wavemaker.apps.mobile.fingenie.fingenie.Session;


/**
 * ServiceImpl object for domain model class Session.
 *
 * @see Session
 */
@Service("FinGenie.SessionService")
public class SessionServiceImpl implements SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionServiceImpl.class);


    @Autowired
    @Qualifier("FinGenie.SessionDao")
    private WMGenericDao<Session, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Session, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "FinGenieTransactionManager")
    @Override
	public Session create(Session session) {
        LOGGER.debug("Creating a new Session with information: {}", session);
        Session sessionCreated = this.wmGenericDao.create(session);
        return sessionCreated;
    }

	@Transactional(readOnly = true, value = "FinGenieTransactionManager")
	@Override
	public Session getById(Integer sessionIdInstance) throws EntityNotFoundException {
        LOGGER.debug("Finding Session by id: {}", sessionIdInstance);
        Session session = this.wmGenericDao.findById(sessionIdInstance);
        if (session == null){
            LOGGER.debug("No Session found with id: {}", sessionIdInstance);
            throw new EntityNotFoundException(String.valueOf(sessionIdInstance));
        }
        return session;
    }

    @Transactional(readOnly = true, value = "FinGenieTransactionManager")
	@Override
	public Session findById(Integer sessionIdInstance) {
        LOGGER.debug("Finding Session by id: {}", sessionIdInstance);
        return this.wmGenericDao.findById(sessionIdInstance);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "FinGenieTransactionManager")
	@Override
	public Session update(Session session) throws EntityNotFoundException {
        LOGGER.debug("Updating Session with information: {}", session);
        this.wmGenericDao.update(session);

        Integer sessionIdInstance = session.getSessionId();

        return this.wmGenericDao.findById(sessionIdInstance);
    }

    @Transactional(value = "FinGenieTransactionManager")
	@Override
	public Session delete(Integer sessionIdInstance) throws EntityNotFoundException {
        LOGGER.debug("Deleting Session with id: {}", sessionIdInstance);
        Session deleted = this.wmGenericDao.findById(sessionIdInstance);
        if (deleted == null) {
            LOGGER.debug("No Session found with id: {}", sessionIdInstance);
            throw new EntityNotFoundException(String.valueOf(sessionIdInstance));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "FinGenieTransactionManager")
	@Override
	public Page<Session> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Sessions");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "FinGenieTransactionManager")
    @Override
    public Page<Session> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Sessions");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "FinGenieTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service FinGenie for table Session to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "FinGenieTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}
