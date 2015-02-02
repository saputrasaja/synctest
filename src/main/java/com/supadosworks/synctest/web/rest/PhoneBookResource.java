package com.supadosworks.synctest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.supadosworks.synctest.domain.PhoneBook;
import com.supadosworks.synctest.repository.PhoneBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing PhoneBook.
 */
@RestController
@RequestMapping("/api")
public class PhoneBookResource {

    private final Logger log = LoggerFactory.getLogger(PhoneBookResource.class);

    @Inject
    private PhoneBookRepository phoneBookRepository;

    /**
     * POST  /phoneBooks -> Create a new phoneBook.
     */
    @RequestMapping(value = "/phoneBooks",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody PhoneBook phoneBook) {
        log.debug("REST request to save PhoneBook : {}", phoneBook);
        phoneBookRepository.save(phoneBook);
    }

    /**
     * GET  /phoneBooks -> get all the phoneBooks.
     */
    @RequestMapping(value = "/phoneBooks",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PhoneBook> getAll() {
        log.debug("REST request to get all PhoneBooks");
        return phoneBookRepository.findAll();
    }

    /**
     * GET  /phoneBooks/:id -> get the "id" phoneBook.
     */
    @RequestMapping(value = "/phoneBooks/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PhoneBook> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get PhoneBook : {}", id);
        PhoneBook phoneBook = phoneBookRepository.findOne(id);
        if (phoneBook == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phoneBook, HttpStatus.OK);
    }

    /**
     * DELETE  /phoneBooks/:id -> delete the "id" phoneBook.
     */
    @RequestMapping(value = "/phoneBooks/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete PhoneBook : {}", id);
        phoneBookRepository.delete(id);
    }
}
