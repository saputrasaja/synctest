package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.StudyEventHelper;
import com.oc.model.StudyEventOC;
import com.supadosworks.synctest.domain.StudyEvent;
import com.supadosworks.synctest.repository.StudyEventRepository;
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
 * REST controller for managing StudyEvent.
 */
@RestController
@RequestMapping("/api")
public class StudyEventResource {
	
	@RequestMapping(value = "/oc/studyEvents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<StudyEventOC> getDataFromOC() {
		List<StudyEventOC> r = new ArrayList<StudyEventOC>();
		StringBuilder sb = new StringBuilder();
		sb.append(" select");
		sb.append("	se.study_event_id as id,");
		sb.append(" sed.name as name,");
		sb.append(" ss.label as label,");
		sb.append(" ses.name as event_status,");
		sb.append(" s.name as status,");
		sb.append(" se.date_start,");
		sb.append(" se.date_end,");
		sb.append(" se.owner_id,");
		sb.append(" se.start_time_flag,");
		sb.append(" se.end_time_flag");
		sb.append(" from study_event se");
		sb.append(" left join subject_event_status ses on se.subject_event_status_id = ses.subject_event_status_id");
		sb.append(" left join study_event_definition sed on se.study_event_definition_id = sed.study_event_definition_id");
		sb.append(" left join study_subject ss on se.study_subject_id = ss.study_subject_id");
		sb.append(" left join status s on se.status_id = s.status_id;");
		try {
			OcConnect oc = OcConnect
					.getConnection(sb.toString());
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(StudyEventOC.getDataset(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@RequestMapping(value = "/studyEvents", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody StudyEventHelper i) {
		log.debug("REST request to PUT size1=" + i.getData().size()
				+ ", size2=" + i.getDataOc().size());
		for (StudyEvent d : i.getData()) {
			log.debug(this.toString() + " sync id=" + d.getId());
			studyEventRepository.save(d);
		}
		for (StudyEventOC d : i.getDataOc()) {
			log.debug(this.toString() + " oc id=" + d.getId());
			d.save();
		}
	}

    private final Logger log = LoggerFactory.getLogger(StudyEventResource.class);

    @Inject
    private StudyEventRepository studyEventRepository;

    /**
     * POST  /studyEvents -> Create a new studyEvent.
     */
    @RequestMapping(value = "/studyEvents",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody StudyEvent studyEvent) {
        log.debug("REST request to save StudyEvent : {}", studyEvent);
        studyEventRepository.save(studyEvent);
    }

    /**
     * GET  /studyEvents -> get all the studyEvents.
     */
    @RequestMapping(value = "/studyEvents",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<StudyEvent> getAll() {
        log.debug("REST request to get all StudyEvents");
        return studyEventRepository.findAll();
    }

    /**
     * GET  /studyEvents/:id -> get the "id" studyEvent.
     */
    @RequestMapping(value = "/studyEvents/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StudyEvent> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get StudyEvent : {}", id);
        StudyEvent studyEvent = studyEventRepository.findOne(id);
        if (studyEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studyEvent, HttpStatus.OK);
    }

    /**
     * DELETE  /studyEvents/:id -> delete the "id" studyEvent.
     */
    @RequestMapping(value = "/studyEvents/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete StudyEvent : {}", id);
        studyEventRepository.delete(id);
    }
}
