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
import com.oc.model.DatasetItemStatusHelper;
import com.oc.model.DatasetItemStatusOC;
import com.supadosworks.synctest.domain.DatasetItemStatus;
import com.supadosworks.synctest.repository.DatasetItemStatusRepository;

/**
 * REST controller for managing DatasetItemStatus.
 */
@RestController
@RequestMapping("/api")
public class DatasetItemStatusResource {

	@RequestMapping(value = "/oc/datasetItemStatuss", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<DatasetItemStatusOC> getStatusFromOC() {
		List<DatasetItemStatusOC> r = new ArrayList<DatasetItemStatusOC>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from dataset_item_status");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(DatasetItemStatusOC.getDataset(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/datasetItemStatuss", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody DatasetItemStatusHelper i) {
		log.debug("REST request to PUT size1=" + i.getData().size()
				+ ", size2=" + i.getDataOc().size());
		for (DatasetItemStatus d : i.getData()) {
			log.debug(this.toString() + " sync id=" + d.getId());
			datasetItemStatusRepository.save(d);
		}
		for (DatasetItemStatusOC d : i.getDataOc()) {
			log.debug(this.toString() + " oc id=" + d.getId());
			d.save();
		}
	}

	private final Logger log = LoggerFactory
			.getLogger(DatasetItemStatusResource.class);

	@Inject
	private DatasetItemStatusRepository datasetItemStatusRepository;

	/**
	 * POST /datasetItemStatuss -> Create a new datasetItemStatus.
	 */
	@RequestMapping(value = "/datasetItemStatuss", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void create(@RequestBody DatasetItemStatus datasetItemStatus) {
		log.debug("REST request to save DatasetItemStatus : {}",
				datasetItemStatus);
		datasetItemStatusRepository.save(datasetItemStatus);
	}

	/**
	 * GET /datasetItemStatuss -> get all the datasetItemStatuss.
	 */
	@RequestMapping(value = "/datasetItemStatuss", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<DatasetItemStatus> getAll() {
		log.debug("REST request to get all DatasetItemStatuss");
		return datasetItemStatusRepository.findAll();
	}

	/**
	 * GET /datasetItemStatuss/:id -> get the "id" datasetItemStatus.
	 */
	@RequestMapping(value = "/datasetItemStatuss/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<DatasetItemStatus> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get DatasetItemStatus : {}", id);
		DatasetItemStatus datasetItemStatus = datasetItemStatusRepository
				.findOne(id);
		if (datasetItemStatus == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(datasetItemStatus, HttpStatus.OK);
	}

	/**
	 * DELETE /datasetItemStatuss/:id -> delete the "id" datasetItemStatus.
	 */
	@RequestMapping(value = "/datasetItemStatuss/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete DatasetItemStatus : {}", id);
		datasetItemStatusRepository.delete(id);
	}
}
