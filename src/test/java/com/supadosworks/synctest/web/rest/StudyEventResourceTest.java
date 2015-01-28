package com.supadosworks.synctest.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;

import com.supadosworks.synctest.Application;
import com.supadosworks.synctest.domain.StudyEvent;
import com.supadosworks.synctest.repository.StudyEventRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StudyEventResource REST controller.
 *
 * @see StudyEventResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StudyEventResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_LABEL = "SAMPLE_TEXT";
    private static final String UPDATED_LABEL = "UPDATED_TEXT";
    private static final String DEFAULT_EVENT_STATUS = "SAMPLE_TEXT";
    private static final String UPDATED_EVENT_STATUS = "UPDATED_TEXT";
    private static final String DEFAULT_STATUS = "SAMPLE_TEXT";
    private static final String UPDATED_STATUS = "UPDATED_TEXT";

    private static final DateTime DEFAULT_DATE_START = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_START = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_START_STR = dateTimeFormatter.print(DEFAULT_DATE_START);

    private static final DateTime DEFAULT_DATE_END = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_END = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_END_STR = dateTimeFormatter.print(DEFAULT_DATE_END);

    private static final Integer DEFAULT_OWNER_ID = 0;
    private static final Integer UPDATED_OWNER_ID = 1;

    @Inject
    private StudyEventRepository studyEventRepository;

    private MockMvc restStudyEventMockMvc;

    private StudyEvent studyEvent;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StudyEventResource studyEventResource = new StudyEventResource();
        ReflectionTestUtils.setField(studyEventResource, "studyEventRepository", studyEventRepository);
        this.restStudyEventMockMvc = MockMvcBuilders.standaloneSetup(studyEventResource).build();
    }

    @Before
    public void initTest() {
        studyEvent = new StudyEvent();
        studyEvent.setName(DEFAULT_NAME);
        studyEvent.setLabel(DEFAULT_LABEL);
        studyEvent.setEvent_status(DEFAULT_EVENT_STATUS);
        studyEvent.setStatus(DEFAULT_STATUS);
//        studyEvent.setDate_start(DEFAULT_DATE_START);
//        studyEvent.setDate_end(DEFAULT_DATE_END);
        studyEvent.setOwner_id(DEFAULT_OWNER_ID);
    }

    @Test
    @Transactional
    public void createStudyEvent() throws Exception {
        // Validate the database is empty
        assertThat(studyEventRepository.findAll()).hasSize(0);

        // Create the StudyEvent
        restStudyEventMockMvc.perform(post("/api/studyEvents")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(studyEvent)))
                .andExpect(status().isOk());

        // Validate the StudyEvent in the database
        List<StudyEvent> studyEvents = studyEventRepository.findAll();
        assertThat(studyEvents).hasSize(1);
        StudyEvent testStudyEvent = studyEvents.iterator().next();
        assertThat(testStudyEvent.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudyEvent.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testStudyEvent.getEvent_status()).isEqualTo(DEFAULT_EVENT_STATUS);
        assertThat(testStudyEvent.getStatus()).isEqualTo(DEFAULT_STATUS);
//        assertThat(testStudyEvent.getDate_start().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_START);
//        assertThat(testStudyEvent.getDate_end().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_END);
        assertThat(testStudyEvent.getOwner_id()).isEqualTo(DEFAULT_OWNER_ID);
    }

    @Test
    @Transactional
    public void getAllStudyEvents() throws Exception {
        // Initialize the database
        studyEventRepository.saveAndFlush(studyEvent);

        // Get all the studyEvents
        restStudyEventMockMvc.perform(get("/api/studyEvents"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(studyEvent.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].label").value(DEFAULT_LABEL.toString()))
                .andExpect(jsonPath("$.[0].event_status").value(DEFAULT_EVENT_STATUS.toString()))
                .andExpect(jsonPath("$.[0].status").value(DEFAULT_STATUS.toString()))
                .andExpect(jsonPath("$.[0].date_start").value(DEFAULT_DATE_START_STR))
                .andExpect(jsonPath("$.[0].date_end").value(DEFAULT_DATE_END_STR))
                .andExpect(jsonPath("$.[0].owner_id").value(DEFAULT_OWNER_ID));
    }

    @Test
    @Transactional
    public void getStudyEvent() throws Exception {
        // Initialize the database
        studyEventRepository.saveAndFlush(studyEvent);

        // Get the studyEvent
        restStudyEventMockMvc.perform(get("/api/studyEvents/{id}", studyEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(studyEvent.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL.toString()))
            .andExpect(jsonPath("$.event_status").value(DEFAULT_EVENT_STATUS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.date_start").value(DEFAULT_DATE_START_STR))
            .andExpect(jsonPath("$.date_end").value(DEFAULT_DATE_END_STR))
            .andExpect(jsonPath("$.owner_id").value(DEFAULT_OWNER_ID));
    }

    @Test
    @Transactional
    public void getNonExistingStudyEvent() throws Exception {
        // Get the studyEvent
        restStudyEventMockMvc.perform(get("/api/studyEvents/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudyEvent() throws Exception {
        // Initialize the database
        studyEventRepository.saveAndFlush(studyEvent);

        // Update the studyEvent
        studyEvent.setName(UPDATED_NAME);
        studyEvent.setLabel(UPDATED_LABEL);
        studyEvent.setEvent_status(UPDATED_EVENT_STATUS);
        studyEvent.setStatus(UPDATED_STATUS);
//        studyEvent.setDate_start(UPDATED_DATE_START);
//        studyEvent.setDate_end(UPDATED_DATE_END);
        studyEvent.setOwner_id(UPDATED_OWNER_ID);
        restStudyEventMockMvc.perform(post("/api/studyEvents")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(studyEvent)))
                .andExpect(status().isOk());

        // Validate the StudyEvent in the database
        List<StudyEvent> studyEvents = studyEventRepository.findAll();
        assertThat(studyEvents).hasSize(1);
        StudyEvent testStudyEvent = studyEvents.iterator().next();
        assertThat(testStudyEvent.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudyEvent.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testStudyEvent.getEvent_status()).isEqualTo(UPDATED_EVENT_STATUS);
        assertThat(testStudyEvent.getStatus()).isEqualTo(UPDATED_STATUS);
//        assertThat(testStudyEvent.getDate_start().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_START);
//        assertThat(testStudyEvent.getDate_end().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_END);
        assertThat(testStudyEvent.getOwner_id()).isEqualTo(UPDATED_OWNER_ID);
    }

    @Test
    @Transactional
    public void deleteStudyEvent() throws Exception {
        // Initialize the database
        studyEventRepository.saveAndFlush(studyEvent);

        // Get the studyEvent
        restStudyEventMockMvc.perform(delete("/api/studyEvents/{id}", studyEvent.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<StudyEvent> studyEvents = studyEventRepository.findAll();
        assertThat(studyEvents).hasSize(0);
    }
}
