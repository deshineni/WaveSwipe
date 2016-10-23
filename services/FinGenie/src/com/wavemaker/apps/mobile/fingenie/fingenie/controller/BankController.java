/*\/**
 * Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 * This software is the confidential and proprietary information of wavemaker-com * You shall not disclose such Confidential Information and shall use it only in accordance
 * with the terms of the source code license agreement you entered into with wavemaker.com *\/*/
package com.wavemaker.apps.mobile.fingenie.fingenie.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.apps.mobile.fingenie.fingenie.Bank;
import com.wavemaker.apps.mobile.fingenie.fingenie.Offer;
import com.wavemaker.apps.mobile.fingenie.fingenie.service.BankService;

/**
 * Controller object for domain model class Bank.
 * @see Bank
 */
@RestController("FinGenie.BankController")
@Api(value = "BankController", description = "Exposes APIs to work with Bank resource.")
@RequestMapping("/FinGenie/Bank")
public class BankController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankController.class);

    @Autowired
    @Qualifier("FinGenie.BankService")
    private BankService bankService;

    @ApiOperation(value = "Creates a new Bank instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Bank createBank(@RequestBody Bank bank) {
        LOGGER.debug("Create Bank with information: {}", bank);
        bank = bankService.create(bank);
        LOGGER.debug("Created Bank with information: {}", bank);
        return bank;
    }

    @ApiOperation(value = "Returns the Bank instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Bank getBank(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Bank with id: {}", id);
        Bank foundBank = bankService.getById(id);
        LOGGER.debug("Bank details with id: {}", foundBank);
        return foundBank;
    }

    @ApiOperation(value = "Updates the Bank instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Bank editBank(@PathVariable("id") Integer id, @RequestBody Bank bank) throws EntityNotFoundException {
        LOGGER.debug("Editing Bank with id: {}", bank.getBankId());
        bank.setBankId(id);
        bank = bankService.update(bank);
        LOGGER.debug("Bank details with id: {}", bank);
        return bank;
    }

    @ApiOperation(value = "Deletes the Bank instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteBank(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Bank with id: {}", id);
        Bank deletedBank = bankService.delete(id);
        return deletedBank != null;
    }

    /**
     * @deprecated Use {@link #findBanks(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Bank instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Bank> findBanks(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Banks list");
        return bankService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Bank instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Bank> findBanks(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Banks list");
        return bankService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportBanks(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return bankService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Bank instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countBanks(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Banks");
        return bankService.count(query);
    }

    @RequestMapping(value = "/{id}/offers", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the offers instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Offer> findAssociatedOffers(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated offers");
        return bankService.findAssociatedOffers(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BankService instance
	 */
    protected void setBankService(BankService service) {
        this.bankService = service;
    }
}