package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.StudyHelper;
import com.oc.model.StudyOC;
import com.supadosworks.synctest.domain.Study;
import com.supadosworks.synctest.repository.StudyRepository;
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
 * REST controller for managing Study.
 */
@RestController
@RequestMapping("/api")
public class StudyResource {

	@RequestMapping(value = "/oc/studys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<StudyOC> getStatusFromOC() {
		List<StudyOC> r = new ArrayList<StudyOC>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from study");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(StudyOC.getDataset(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/studys", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody StudyHelper i) {
		log.debug("REST request to PUT size1=" + i.getData().size()
				+ ", size2=" + i.getDataOc().size());
		for (Study d : i.getData()) {
			log.debug(this.toString() + " sync id=" + d.getId());
			studyRepository.save(d);
		}
		for (StudyOC d : i.getDataOc()) {
			log.debug(this.toString() + " oc id=" + d.getId());
			d.save();
		}
	}
    private final Logger log = LoggerFactory.getLogger(StudyResource.class);

    @Inject
    private StudyRepository studyRepository;

    /**
     * POST  /studys -> Create a new study.
     */
    @RequestMapping(value = "/studys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Study study) {
        log.debug("REST request to save Study : {}", study);
        studyRepository.save(study);
    }

    /**
     * GET  /studys -> get all the studys.
     */
    @RequestMapping(value = "/studys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Study> getAll() {
        log.debug("REST request to get all Studys");
        return studyRepository.findAll();
    }

    /**
     * GET  /studys/:id -> get the "id" study.
     */
    @RequestMapping(value = "/studys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Study> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Study : {}", id);
        Study study = studyRepository.findOne(id);
        if (study == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(study, HttpStatus.OK);
    }

    /**
     * DELETE  /studys/:id -> delete the "id" study.
     */
    @RequestMapping(value = "/studys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Study : {}", id);
        studyRepository.delete(id);
    }
}
