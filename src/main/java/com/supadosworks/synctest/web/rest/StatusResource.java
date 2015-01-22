package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.StatusHelper;
import com.oc.model.StatusOC;
import com.supadosworks.synctest.domain.Status;
import com.supadosworks.synctest.repository.StatusRepository;
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
 * REST controller for managing Status.
 */
@RestController
@RequestMapping("/api")
public class StatusResource {

	@RequestMapping(value = "/oc/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<StatusOC> getStatusOC() {
		List<StatusOC> r = new ArrayList<StatusOC>();
		try {
			OcConnect oc = OcConnect.getConnection("select * from status");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(StatusOC.getStatus(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/statuss", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody StatusHelper sh) {
		log.debug("REST request to PUT size1=" + sh.getS().size() + ", size2="
				+ sh.getsOc().size());
		for (Status s : sh.getS()) {
			log.debug("Status id=" + s.getId());
			statusRepository.save(s);
		}
		for (StatusOC s : sh.getsOc()){
			log.debug("StatusOC id=" + s.getId());
			s.save();
		}
	}

	private final Logger log = LoggerFactory.getLogger(StatusResource.class);

	@Inject
	private StatusRepository statusRepository;

	/**
	 * POST /statuss -> Create a new status.
	 */
	@RequestMapping(value = "/statuss", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void create(@RequestBody Status status) {
		log.debug("REST request to save Status : {}", status);
		statusRepository.save(status);
	}

	/**
	 * GET /statuss -> get all the statuss.
	 */
	@RequestMapping(value = "/statuss", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<Status> getAll() {
		log.debug("REST request to get all Statuss");
		return statusRepository.findAll();
	}

	/**
	 * GET /statuss/:id -> get the "id" status.
	 */
	@RequestMapping(value = "/statuss/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Status> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get Status : {}", id);
		Status status = statusRepository.findOne(id);
		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	/**
	 * DELETE /statuss/:id -> delete the "id" status.
	 */
	@RequestMapping(value = "/statuss/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete Status : {}", id);
		statusRepository.delete(id);
	}
}
