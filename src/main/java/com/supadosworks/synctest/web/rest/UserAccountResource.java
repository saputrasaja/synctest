package com.supadosworks.synctest.web.rest;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.UserAccountHelper;
import com.oc.model.UserAccountOC;
import com.supadosworks.synctest.domain.UserAccount;
import com.supadosworks.synctest.repository.UserAccountRepository;

/**
 * REST controller for managing UserAccount.
 */
@RestController
@RequestMapping("/api")
public class UserAccountResource {
	
	@RequestMapping(value = "/oc/userAccounts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<UserAccountOC> getStatusFromOC() {
		List<UserAccountOC> r = new ArrayList<UserAccountOC>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from user_account");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(UserAccountOC.getDataset(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/userAccounts", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody UserAccountHelper i) {
		log.debug("REST request to PUT size1=" + i.getData().size()
				+ ", size2=" + i.getDataOc().size());
		for (UserAccount d : i.getData()) {
			log.debug(this.toString() + " sync id=" + d.getId());
			userAccountRepository.save(d);
		}
		for (UserAccountOC d : i.getDataOc()) {
			log.debug(this.toString() + " oc id=" + d.getId());
			d.save();
		}
	}

    private final Logger log = LoggerFactory.getLogger(UserAccountResource.class);

    @Inject
    private UserAccountRepository userAccountRepository;

    /**
     * POST  /userAccounts -> Create a new userAccount.
     */
    @RequestMapping(value = "/userAccounts",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody UserAccount userAccount) {
        log.debug("REST request to save UserAccount : {}", userAccount);
        userAccountRepository.save(userAccount);
    }

    /**
     * GET  /userAccounts -> get all the userAccounts.
     */
    @RequestMapping(value = "/userAccounts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserAccount> getAll() {
        log.debug("REST request to get all UserAccounts");
        return userAccountRepository.findAll();
    }

    /**
     * GET  /userAccounts/:id -> get the "id" userAccount.
     */
    @RequestMapping(value = "/userAccounts/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserAccount> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get UserAccount : {}", id);
        UserAccount userAccount = userAccountRepository.findOne(id);
        if (userAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }

    /**
     * DELETE  /userAccounts/:id -> delete the "id" userAccount.
     */
    @RequestMapping(value = "/userAccounts/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete UserAccount : {}", id);
        userAccountRepository.delete(id);
    }
}
