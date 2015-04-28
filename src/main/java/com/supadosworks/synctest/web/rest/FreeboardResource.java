package com.supadosworks.synctest.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.oc.model.freebard.StudyBaseOnGender;

@RestController
@RequestMapping("/api")
public class FreeboardResource {
	@RequestMapping(value = "/oc/freeboard/studyBaseOnGender", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<StudyBaseOnGender> getByOwnerId() {
		StudyBaseOnGender r = new StudyBaseOnGender();
		r.setFemaleValue(10);
		r.setMaleValue(30);
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

}
