package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.UserRoleHelper;
import com.oc.model.UserRoleOC;
import com.supadosworks.synctest.domain.UserRole;
import com.supadosworks.synctest.domain.UserRole;
import com.supadosworks.synctest.repository.UserRoleRepository;
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
 * REST controller for managing UserRole.
 */
@RestController
@RequestMapping("/api")
public class UserRoleResource {
	@RequestMapping(value = "/oc/userRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<UserRoleOC> getDataFromOC() {
		List<UserRoleOC> r = new ArrayList<UserRoleOC>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from user_role");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(UserRoleOC.getDataset(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/userRoles", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody UserRoleHelper i) {
		log.debug("REST request to PUT size1=" + i.getData().size()
				+ ", size2=" + i.getDataOc().size());
		for (UserRole d : i.getData()) {
			log.debug(this.toString() + " sync id=" + d.getId());
			userRoleRepository.save(d);
		}
		for (UserRoleOC d : i.getDataOc()) {
			log.debug(this.toString() + " oc id=" + d.getId());
			d.save();
		}
	}

    private final Logger log = LoggerFactory.getLogger(UserRoleResource.class);

    @Inject
    private UserRoleRepository userRoleRepository;

    /**
     * POST  /userRoles -> Create a new userRole.
     */
    @RequestMapping(value = "/userRoles",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody UserRole userRole) {
        log.debug("REST request to save UserRole : {}", userRole);
        userRoleRepository.save(userRole);
    }

    /**
     * GET  /userRoles -> get all the userRoles.
     */
    @RequestMapping(value = "/userRoles",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserRole> getAll() {
        log.debug("REST request to get all UserRoles");
        return userRoleRepository.findAll();
    }

    /**
     * GET  /userRoles/:id -> get the "id" userRole.
     */
    @RequestMapping(value = "/userRoles/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserRole> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get UserRole : {}", id);
        UserRole userRole = userRoleRepository.findOne(id);
        if (userRole == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRole, HttpStatus.OK);
    }

    /**
     * DELETE  /userRoles/:id -> delete the "id" userRole.
     */
    @RequestMapping(value = "/userRoles/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete UserRole : {}", id);
        userRoleRepository.delete(id);
    }
}
