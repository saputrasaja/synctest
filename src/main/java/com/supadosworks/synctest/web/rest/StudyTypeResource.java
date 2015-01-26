package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.StudyTypeHelper;
import com.oc.model.StudyTypeOC;
import com.supadosworks.synctest.domain.StudyType;
import com.supadosworks.synctest.repository.StudyTypeRepository;
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
 * REST controller for managing StudyType.
 */
@RestController
@RequestMapping("/api")
public class StudyTypeResource {
	
	@RequestMapping(value = "/oc/studyTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<StudyTypeOC> getStatusFromOC() {
		List<StudyTypeOC> r = new ArrayList<StudyTypeOC>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from study_type");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(StudyTypeOC.getDataset(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/studyTypes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody StudyTypeHelper i) {
		log.debug("REST request to PUT size1=" + i.getData().size()
				+ ", size2=" + i.getDataOc().size());
		for (StudyType d : i.getData()) {
			log.debug(this.toString() + " sync id=" + d.getId());
			studyTypeRepository.save(d);
		}
		for (StudyTypeOC d : i.getDataOc()) {
			log.debug(this.toString() + " oc id=" + d.getId());
			d.save();
		}
	}

    private final Logger log = LoggerFactory.getLogger(StudyTypeResource.class);

    @Inject
    private StudyTypeRepository studyTypeRepository;

    /**
     * POST  /studyTypes -> Create a new studyType.
     */
    @RequestMapping(value = "/studyTypes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody StudyType studyType) {
        log.debug("REST request to save StudyType : {}", studyType);
        studyTypeRepository.save(studyType);
    }

    /**
     * GET  /studyTypes -> get all the studyTypes.
     */
    @RequestMapping(value = "/studyTypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<StudyType> getAll() {
        log.debug("REST request to get all StudyTypes");
        return studyTypeRepository.findAll();
    }

    /**
     * GET  /studyTypes/:id -> get the "id" studyType.
     */
    @RequestMapping(value = "/studyTypes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StudyType> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get StudyType : {}", id);
        StudyType studyType = studyTypeRepository.findOne(id);
        if (studyType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studyType, HttpStatus.OK);
    }

    /**
     * DELETE  /studyTypes/:id -> delete the "id" studyType.
     */
    @RequestMapping(value = "/studyTypes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete StudyType : {}", id);
        studyTypeRepository.delete(id);
    }
}
