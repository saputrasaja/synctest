package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.DatasetHelper;
import com.oc.model.DatasetOC;
import com.supadosworks.synctest.domain.Dataset;
import com.supadosworks.synctest.repository.DatasetRepository;
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
 * REST controller for managing Dataset.
 */
@RestController
@RequestMapping("/api")
public class DatasetResource {
	
	@RequestMapping(value = "/oc/datasets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<DatasetOC> getStatusFromOC() {
		List<DatasetOC> r = new ArrayList<DatasetOC>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from Dataset");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(DatasetOC.getDataset(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/datasets", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody DatasetHelper i) {
		log.debug("REST request to PUT size1=" + i.getData().size()
				+ ", size2=" + i.getDataOc().size());
		for (Dataset d : i.getData()) {
			log.debug(this.toString() + " sync id=" + d.getId());
			datasetRepository.save(d);
		}
		for (DatasetOC d : i.getDataOc()) {
			log.debug(this.toString() + " oc id=" + d.getId());
			d.save();
		}
	}

    private final Logger log = LoggerFactory.getLogger(DatasetResource.class);

    @Inject
    private DatasetRepository datasetRepository;

    /**
     * POST  /datasets -> Create a new dataset.
     */
    @RequestMapping(value = "/datasets",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Dataset dataset) {
        log.debug("REST request to save Dataset : {}", dataset);
        datasetRepository.save(dataset);
    }

    /**
     * GET  /datasets -> get all the datasets.
     */
    @RequestMapping(value = "/datasets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Dataset> getAll() {
        log.debug("REST request to get all Datasets");
        return datasetRepository.findAll();
    }

    /**
     * GET  /datasets/:id -> get the "id" dataset.
     */
    @RequestMapping(value = "/datasets/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Dataset> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Dataset : {}", id);
        Dataset dataset = datasetRepository.findOne(id);
        if (dataset == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dataset, HttpStatus.OK);
    }

    /**
     * DELETE  /datasets/:id -> delete the "id" dataset.
     */
    @RequestMapping(value = "/datasets/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Dataset : {}", id);
        datasetRepository.delete(id);
    }
}
