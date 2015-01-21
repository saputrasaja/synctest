package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oc.connection.OcConnect;
import com.oc.model.ExportFormatHelper;
import com.oc.model.ExportFormatOC;
import com.supadosworks.synctest.domain.ExportFormat;
import com.supadosworks.synctest.repository.ExportFormatRepository;
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
 * REST controller for managing ExportFormat.
 */
@RestController
@RequestMapping("/api")
public class ExportFormatResource {
	
	@RequestMapping(value = "/oc/exportformat", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<ExportFormatOC> getExportFormatFromOpenClinica() {
		List<ExportFormatOC> r = new ArrayList<ExportFormatOC>();
		try {
			OcConnect oc = OcConnect
					.getConnection("select * from export_format");
			ResultSet rs = oc.rs;
			while (rs.next()) {
				r.add(ExportFormatOC.getEF(rs));
			}
			OcConnect.close(oc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping(value = "/exportFormats", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void saveMany(@RequestBody ExportFormatHelper efh) {
		log.debug("REST request to PUT size1=" + efh.getEf().size()
				+ ", size2=" + efh.getEfoc().size());
		for (ExportFormat ef : efh.getEf()) {
			log.debug("ExportFormat id=" + ef.getId());
			exportFormatRepository.save(ef);
		}
		for (ExportFormatOC ef : efh.getEfoc()) {
			log.debug("ExportFormatOC id=" + ef.getId());
			ef.save();
		}
	}

	private final Logger log = LoggerFactory
			.getLogger(ExportFormatResource.class);

	@Inject
	private ExportFormatRepository exportFormatRepository;

	/**
	 * POST /exportFormats -> Create a new exportFormat.
	 */
	@RequestMapping(value = "/exportFormats", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void create(@RequestBody ExportFormat exportFormat) {
		log.debug("REST request to save ExportFormat : {}", exportFormat);
		exportFormatRepository.save(exportFormat);
	}

	/**
	 * GET /exportFormats -> get all the exportFormats.
	 */
	@RequestMapping(value = "/exportFormats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<ExportFormat> getAll() {
		log.debug("REST request to get all ExportFormats");
		return exportFormatRepository.findAll();
	}

	/**
	 * GET /exportFormats/:id -> get the "id" exportFormat.
	 */
	@RequestMapping(value = "/exportFormats/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<ExportFormat> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get ExportFormat : {}", id);
		ExportFormat exportFormat = exportFormatRepository.findOne(id);
		if (exportFormat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(exportFormat, HttpStatus.OK);
	}

	/**
	 * DELETE /exportFormats/:id -> delete the "id" exportFormat.
	 */
	@RequestMapping(value = "/exportFormats/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete ExportFormat : {}", id);
		exportFormatRepository.delete(id);
	}
}
