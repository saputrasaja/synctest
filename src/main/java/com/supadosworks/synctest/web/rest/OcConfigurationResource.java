package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.Configuration;
import com.supadosworks.synctest.domain.OcConfiguration;
import com.supadosworks.synctest.repository.OcConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing OcConfiguration.
 */
@RestController
@RequestMapping("/api")
public class OcConfigurationResource {

	@RequestMapping(value = "/oc/configurations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<Configuration> getOcConf() {
		log.debug(" START ");
		List<Configuration> results = new ArrayList<Configuration>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from configuration");
			ResultSet rs = oc.rs;
			while (oc.rs.next()) {
				Configuration c = new Configuration();
				c.setId(rs.getLong(1));
				c.setKey(rs.getString(2));
				c.setValue(rs.getString(3));
				c.setDescription(rs.getString(4));
				c.setVersion(rs.getInt(5));
				results.add(c);
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	private final Logger log = LoggerFactory
			.getLogger(OcConfigurationResource.class);

	@Inject
	private OcConfigurationRepository ocConfigurationRepository;

	@RequestMapping(value = "/ocConfigurations", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void put(@RequestBody List<OcConfiguration> ocConfigurations) {
		log.debug("REST request to PUT OcConfiguration : SIZE=",
				ocConfigurations.size());
		for (OcConfiguration conf : ocConfigurations) {
			ocConfigurationRepository.save(conf);
		}
	}

	/**
	 * POST /ocConfigurations -> Create a new ocConfiguration.
	 */
	@RequestMapping(value = "/ocConfigurations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void create(@RequestBody OcConfiguration ocConfiguration) {
		log.debug("REST request to save OcConfiguration : {}", ocConfiguration);
		ocConfigurationRepository.save(ocConfiguration);
		testConnection();
	}

	/**
	 * GET /ocConfigurations -> get all the ocConfigurations.
	 */
	@RequestMapping(value = "/ocConfigurations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<OcConfiguration> getAll() {
		log.debug("REST request to get all OcConfigurations");
		return ocConfigurationRepository.findAll();
	}

	/**
	 * GET /ocConfigurations/:id -> get the "id" ocConfiguration.
	 */
	@RequestMapping(value = "/ocConfigurations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<OcConfiguration> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get OcConfiguration : {}", id);
		OcConfiguration ocConfiguration = ocConfigurationRepository.findOne(id);
		if (ocConfiguration == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ocConfiguration, HttpStatus.OK);
	}

	/**
	 * DELETE /ocConfigurations/:id -> delete the "id" ocConfiguration.
	 */
	@RequestMapping(value = "/ocConfigurations/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete OcConfiguration : {}", id);
		ocConfigurationRepository.delete(id);
	}
}
