package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.UserTypeHelper;
import com.oc.model.UserTypeOC;
import com.supadosworks.synctest.domain.UserType;
import com.supadosworks.synctest.domain.UserType;
import com.supadosworks.synctest.repository.UserTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing UserType.
 */
@RestController
@RequestMapping("/api")
public class UserTypeResource {
	
	@RequestMapping(value = "/oc/userTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<UserTypeOC> getStatusFromOC() {
		List<UserTypeOC> r = new ArrayList<UserTypeOC>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from dataset_item_status");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(UserTypeOC.getDataset(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/userTypes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody UserTypeHelper i) {
		log.debug("REST request to PUT size1=" + i.getData().size()
				+ ", size2=" + i.getDataOc().size());
		for (UserType d : i.getData()) {
			log.debug(this.toString() + " sync id=" + d.getId());
			userTypeRepository.save(d);
		}
		for (UserTypeOC d : i.getDataOc()) {
			log.debug(this.toString() + " oc id=" + d.getId());
			d.save();
		}
	}

    private final Logger log = LoggerFactory.getLogger(UserTypeResource.class);

    @Inject
    private UserTypeRepository userTypeRepository;

    /**
     * POST  /userTypes -> Create a new userType.
     */
    @RequestMapping(value = "/userTypes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody UserType userType) {
        log.debug("REST request to save UserType : {}", userType);
        userTypeRepository.save(userType);
    }

    /**
     * GET  /userTypes -> get all the userTypes.
     */
    @RequestMapping(value = "/userTypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserType> getAll() {
        log.debug("REST request to get all UserTypes");
        return userTypeRepository.findAll();
    }

    /**
     * GET  /userTypes/:id -> get the "id" userType.
     */
    @RequestMapping(value = "/userTypes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserType> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get UserType : {}", id);
        UserType userType = userTypeRepository.findOne(id);
        if (userType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userType, HttpStatus.OK);
    }

    /**
     * DELETE  /userTypes/:id -> delete the "id" userType.
     */
    @RequestMapping(value = "/userTypes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete UserType : {}", id);
        userTypeRepository.delete(id);
    }
}
