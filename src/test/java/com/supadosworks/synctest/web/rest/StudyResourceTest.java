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
import com.supadosworks.synctest.domain.Study;
import com.supadosworks.synctest.repository.StudyRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StudyResource REST controller.
 *
 * @see StudyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StudyResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");


    private static final Integer DEFAULT_PARENT_STUDY_ID = 0;
    private static final Integer UPDATED_PARENT_STUDY_ID = 1;
    private static final String DEFAULT_UNIQUE_IDENTIFIER = "SAMPLE_TEXT";
    private static final String UPDATED_UNIQUE_IDENTIFIER = "UPDATED_TEXT";
    private static final String DEFAULT_SECONDARY_IDENTIFIER = "SAMPLE_TEXT";
    private static final String UPDATED_SECONDARY_IDENTIFIER = "UPDATED_TEXT";
    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_SUMMARY = "SAMPLE_TEXT";
    private static final String UPDATED_SUMMARY = "UPDATED_TEXT";

    private static final DateTime DEFAULT_DATE_PLANNED_START = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_PLANNED_START = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_PLANNED_START_STR = dateTimeFormatter.print(DEFAULT_DATE_PLANNED_START);

    private static final DateTime DEFAULT_DATE_PLANNED_END = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_PLANNED_END = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_PLANNED_END_STR = dateTimeFormatter.print(DEFAULT_DATE_PLANNED_END);

    private static final DateTime DEFAULT_DATE_CREATED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_CREATED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_CREATED_STR = dateTimeFormatter.print(DEFAULT_DATE_CREATED);

    private static final DateTime DEFAULT_DATE_UPDATED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_UPDATED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_UPDATED_STR = dateTimeFormatter.print(DEFAULT_DATE_UPDATED);

    private static final Integer DEFAULT_OWNER_ID = 0;
    private static final Integer UPDATED_OWNER_ID = 1;

    private static final Integer DEFAULT_UPDATE_ID = 0;
    private static final Integer UPDATED_UPDATE_ID = 1;

    private static final Integer DEFAULT_TYPE_ID = 0;
    private static final Integer UPDATED_TYPE_ID = 1;

    private static final Integer DEFAULT_STATUS_ID = 0;
    private static final Integer UPDATED_STATUS_ID = 1;
    private static final String DEFAULT_PRINCIPAL_INVESTIGATOR = "SAMPLE_TEXT";
    private static final String UPDATED_PRINCIPAL_INVESTIGATOR = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_CITY = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_CITY = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_STATE = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_STATE = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_ZIP = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_ZIP = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_COUNTRY = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_COUNTRY = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_RECRUITMENT_STATUS = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_RECRUITMENT_STATUS = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_CONTACT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_CONTACT_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_CONTACT_DEGREE = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_CONTACT_DEGREE = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_CONTACT_PHONE = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_CONTACT_PHONE = "UPDATED_TEXT";
    private static final String DEFAULT_FACILITY_CONTACT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_FACILITY_CONTACT_EMAIL = "UPDATED_TEXT";
    private static final String DEFAULT_PROTOCOL_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_PROTOCOL_TYPE = "UPDATED_TEXT";
    private static final String DEFAULT_PROTOCOL_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_PROTOCOL_DESCRIPTION = "UPDATED_TEXT";

    private static final DateTime DEFAULT_PROTOCOL_DATE_VERIFICATION = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_PROTOCOL_DATE_VERIFICATION = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_PROTOCOL_DATE_VERIFICATION_STR = dateTimeFormatter.print(DEFAULT_PROTOCOL_DATE_VERIFICATION);
    private static final String DEFAULT_PHASE = "SAMPLE_TEXT";
    private static final String UPDATED_PHASE = "UPDATED_TEXT";

    private static final Integer DEFAULT_EXPECTED_TOTAL_ENROLLMENT = 0;
    private static final Integer UPDATED_EXPECTED_TOTAL_ENROLLMENT = 1;
    private static final String DEFAULT_SPONSOR = "SAMPLE_TEXT";
    private static final String UPDATED_SPONSOR = "UPDATED_TEXT";
    private static final String DEFAULT_COLLABORATORS = "SAMPLE_TEXT";
    private static final String UPDATED_COLLABORATORS = "UPDATED_TEXT";
    private static final String DEFAULT_MEDLINE_IDENTIFIER = "SAMPLE_TEXT";
    private static final String UPDATED_MEDLINE_IDENTIFIER = "UPDATED_TEXT";
    private static final String DEFAULT_URL = "SAMPLE_TEXT";
    private static final String UPDATED_URL = "UPDATED_TEXT";
    private static final String DEFAULT_URL_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_URL_DESCRIPTION = "UPDATED_TEXT";
    private static final String DEFAULT_CONDITIONS = "SAMPLE_TEXT";
    private static final String UPDATED_CONDITIONS = "UPDATED_TEXT";
    private static final String DEFAULT_KEYWORDS = "SAMPLE_TEXT";
    private static final String UPDATED_KEYWORDS = "UPDATED_TEXT";
    private static final String DEFAULT_ELIGIBILITY = "SAMPLE_TEXT";
    private static final String UPDATED_ELIGIBILITY = "UPDATED_TEXT";
    private static final String DEFAULT_GENDER = "SAMPLE_TEXT";
    private static final String UPDATED_GENDER = "UPDATED_TEXT";
    private static final String DEFAULT_AGE_MAX = "SAMPLE_TEXT";
    private static final String UPDATED_AGE_MAX = "UPDATED_TEXT";
    private static final String DEFAULT_AGE_MIN = "SAMPLE_TEXT";
    private static final String UPDATED_AGE_MIN = "UPDATED_TEXT";
    private static final String DEFAULT_HEALTHY_VOLUNTEER_ACCEPTED = "SAMPLE_TEXT";
    private static final String UPDATED_HEALTHY_VOLUNTEER_ACCEPTED = "UPDATED_TEXT";
    private static final String DEFAULT_PURPOSE = "SAMPLE_TEXT";
    private static final String UPDATED_PURPOSE = "UPDATED_TEXT";
    private static final String DEFAULT_ALLOCATION = "SAMPLE_TEXT";
    private static final String UPDATED_ALLOCATION = "UPDATED_TEXT";
    private static final String DEFAULT_MASKING = "SAMPLE_TEXT";
    private static final String UPDATED_MASKING = "UPDATED_TEXT";
    private static final String DEFAULT_CONTROL = "SAMPLE_TEXT";
    private static final String UPDATED_CONTROL = "UPDATED_TEXT";
    private static final String DEFAULT_ASSIGNMENT = "SAMPLE_TEXT";
    private static final String UPDATED_ASSIGNMENT = "UPDATED_TEXT";
    private static final String DEFAULT_ENDPOINT = "SAMPLE_TEXT";
    private static final String UPDATED_ENDPOINT = "UPDATED_TEXT";
    private static final String DEFAULT_INTERVENTIONS = "SAMPLE_TEXT";
    private static final String UPDATED_INTERVENTIONS = "UPDATED_TEXT";
    private static final String DEFAULT_DURATION = "SAMPLE_TEXT";
    private static final String UPDATED_DURATION = "UPDATED_TEXT";
    private static final String DEFAULT_SELECTION = "SAMPLE_TEXT";
    private static final String UPDATED_SELECTION = "UPDATED_TEXT";
    private static final String DEFAULT_TIMING = "SAMPLE_TEXT";
    private static final String UPDATED_TIMING = "UPDATED_TEXT";
    private static final String DEFAULT_OFFICIAL_TITLE = "SAMPLE_TEXT";
    private static final String UPDATED_OFFICIAL_TITLE = "UPDATED_TEXT";
    private static final String DEFAULT_RESULTS_REFERENCE = "SAMPLE_TEXT";
    private static final String UPDATED_RESULTS_REFERENCE = "UPDATED_TEXT";
    private static final String DEFAULT_OC_OID = "SAMPLE_TEXT";
    private static final String UPDATED_OC_OID = "UPDATED_TEXT";

    private static final Integer DEFAULT_OLD_STATUS_ID = 0;
    private static final Integer UPDATED_OLD_STATUS_ID = 1;

    @Inject
    private StudyRepository studyRepository;

    private MockMvc restStudyMockMvc;

    private Study study;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StudyResource studyResource = new StudyResource();
        ReflectionTestUtils.setField(studyResource, "studyRepository", studyRepository);
        this.restStudyMockMvc = MockMvcBuilders.standaloneSetup(studyResource).build();
    }

    @Before
    public void initTest() {
        study = new Study();
        study.setParent_study_id(DEFAULT_PARENT_STUDY_ID);
        study.setUnique_identifier(DEFAULT_UNIQUE_IDENTIFIER);
        study.setSecondary_identifier(DEFAULT_SECONDARY_IDENTIFIER);
        study.setName(DEFAULT_NAME);
        study.setSummary(DEFAULT_SUMMARY);
//        study.setDate_planned_start(DEFAULT_DATE_PLANNED_START);
//        study.setDate_planned_end(DEFAULT_DATE_PLANNED_END);
//        study.setDate_created(DEFAULT_DATE_CREATED);
//        study.setDate_updated(DEFAULT_DATE_UPDATED);
        study.setOwner_id(DEFAULT_OWNER_ID);
        study.setUpdate_id(DEFAULT_UPDATE_ID);
        study.setType_id(DEFAULT_TYPE_ID);
        study.setStatus_id(DEFAULT_STATUS_ID);
        study.setPrincipal_investigator(DEFAULT_PRINCIPAL_INVESTIGATOR);
        study.setFacility_name(DEFAULT_FACILITY_NAME);
        study.setFacility_city(DEFAULT_FACILITY_CITY);
        study.setFacility_state(DEFAULT_FACILITY_STATE);
        study.setFacility_zip(DEFAULT_FACILITY_ZIP);
        study.setFacility_country(DEFAULT_FACILITY_COUNTRY);
        study.setFacility_recruitment_status(DEFAULT_FACILITY_RECRUITMENT_STATUS);
        study.setFacility_contact_name(DEFAULT_FACILITY_CONTACT_NAME);
        study.setFacility_contact_degree(DEFAULT_FACILITY_CONTACT_DEGREE);
        study.setFacility_contact_phone(DEFAULT_FACILITY_CONTACT_PHONE);
        study.setFacility_contact_email(DEFAULT_FACILITY_CONTACT_EMAIL);
        study.setProtocol_type(DEFAULT_PROTOCOL_TYPE);
        study.setProtocol_description(DEFAULT_PROTOCOL_DESCRIPTION);
//        study.setProtocol_date_verification(DEFAULT_PROTOCOL_DATE_VERIFICATION);
        study.setPhase(DEFAULT_PHASE);
        study.setExpected_total_enrollment(DEFAULT_EXPECTED_TOTAL_ENROLLMENT);
        study.setSponsor(DEFAULT_SPONSOR);
        study.setCollaborators(DEFAULT_COLLABORATORS);
        study.setMedline_identifier(DEFAULT_MEDLINE_IDENTIFIER);
        study.setUrl(DEFAULT_URL);
        study.setUrl_description(DEFAULT_URL_DESCRIPTION);
        study.setConditions(DEFAULT_CONDITIONS);
        study.setKeywords(DEFAULT_KEYWORDS);
        study.setEligibility(DEFAULT_ELIGIBILITY);
        study.setGender(DEFAULT_GENDER);
        study.setAge_max(DEFAULT_AGE_MAX);
        study.setAge_min(DEFAULT_AGE_MIN);
//        study.setHealthy_volunteer_accepted(DEFAULT_HEALTHY_VOLUNTEER_ACCEPTED);
        study.setPurpose(DEFAULT_PURPOSE);
        study.setAllocation(DEFAULT_ALLOCATION);
        study.setMasking(DEFAULT_MASKING);
        study.setControl(DEFAULT_CONTROL);
        study.setAssignment(DEFAULT_ASSIGNMENT);
        study.setEndpoint(DEFAULT_ENDPOINT);
        study.setInterventions(DEFAULT_INTERVENTIONS);
        study.setDuration(DEFAULT_DURATION);
        study.setSelection(DEFAULT_SELECTION);
        study.setTiming(DEFAULT_TIMING);
        study.setOfficial_title(DEFAULT_OFFICIAL_TITLE);
//        study.setResults_reference(DEFAULT_RESULTS_REFERENCE);
        study.setOc_oid(DEFAULT_OC_OID);
        study.setOld_status_id(DEFAULT_OLD_STATUS_ID);
    }

    @Test
    @Transactional
    public void createStudy() throws Exception {
        // Validate the database is empty
        assertThat(studyRepository.findAll()).hasSize(0);

        // Create the Study
        restStudyMockMvc.perform(post("/api/studys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(study)))
                .andExpect(status().isOk());

        // Validate the Study in the database
        List<Study> studys = studyRepository.findAll();
        assertThat(studys).hasSize(1);
        Study testStudy = studys.iterator().next();
        assertThat(testStudy.getParent_study_id()).isEqualTo(DEFAULT_PARENT_STUDY_ID);
        assertThat(testStudy.getUnique_identifier()).isEqualTo(DEFAULT_UNIQUE_IDENTIFIER);
        assertThat(testStudy.getSecondary_identifier()).isEqualTo(DEFAULT_SECONDARY_IDENTIFIER);
        assertThat(testStudy.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudy.getSummary()).isEqualTo(DEFAULT_SUMMARY);
//        assertThat(testStudy.getDate_planned_start().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_PLANNED_START);
//        assertThat(testStudy.getDate_planned_end().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_PLANNED_END);
//        assertThat(testStudy.getDate_created().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_CREATED);
//        assertThat(testStudy.getDate_updated().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_UPDATED);
        assertThat(testStudy.getOwner_id()).isEqualTo(DEFAULT_OWNER_ID);
        assertThat(testStudy.getUpdate_id()).isEqualTo(DEFAULT_UPDATE_ID);
        assertThat(testStudy.getType_id()).isEqualTo(DEFAULT_TYPE_ID);
        assertThat(testStudy.getStatus_id()).isEqualTo(DEFAULT_STATUS_ID);
        assertThat(testStudy.getPrincipal_investigator()).isEqualTo(DEFAULT_PRINCIPAL_INVESTIGATOR);
        assertThat(testStudy.getFacility_name()).isEqualTo(DEFAULT_FACILITY_NAME);
        assertThat(testStudy.getFacility_city()).isEqualTo(DEFAULT_FACILITY_CITY);
        assertThat(testStudy.getFacility_state()).isEqualTo(DEFAULT_FACILITY_STATE);
        assertThat(testStudy.getFacility_zip()).isEqualTo(DEFAULT_FACILITY_ZIP);
        assertThat(testStudy.getFacility_country()).isEqualTo(DEFAULT_FACILITY_COUNTRY);
        assertThat(testStudy.getFacility_recruitment_status()).isEqualTo(DEFAULT_FACILITY_RECRUITMENT_STATUS);
        assertThat(testStudy.getFacility_contact_name()).isEqualTo(DEFAULT_FACILITY_CONTACT_NAME);
        assertThat(testStudy.getFacility_contact_degree()).isEqualTo(DEFAULT_FACILITY_CONTACT_DEGREE);
        assertThat(testStudy.getFacility_contact_phone()).isEqualTo(DEFAULT_FACILITY_CONTACT_PHONE);
        assertThat(testStudy.getFacility_contact_email()).isEqualTo(DEFAULT_FACILITY_CONTACT_EMAIL);
        assertThat(testStudy.getProtocol_type()).isEqualTo(DEFAULT_PROTOCOL_TYPE);
        assertThat(testStudy.getProtocol_description()).isEqualTo(DEFAULT_PROTOCOL_DESCRIPTION);
//        assertThat(testStudy.getProtocol_date_verification().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_PROTOCOL_DATE_VERIFICATION);
        assertThat(testStudy.getPhase()).isEqualTo(DEFAULT_PHASE);
        assertThat(testStudy.getExpected_total_enrollment()).isEqualTo(DEFAULT_EXPECTED_TOTAL_ENROLLMENT);
        assertThat(testStudy.getSponsor()).isEqualTo(DEFAULT_SPONSOR);
        assertThat(testStudy.getCollaborators()).isEqualTo(DEFAULT_COLLABORATORS);
        assertThat(testStudy.getMedline_identifier()).isEqualTo(DEFAULT_MEDLINE_IDENTIFIER);
        assertThat(testStudy.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testStudy.getUrl_description()).isEqualTo(DEFAULT_URL_DESCRIPTION);
        assertThat(testStudy.getConditions()).isEqualTo(DEFAULT_CONDITIONS);
        assertThat(testStudy.getKeywords()).isEqualTo(DEFAULT_KEYWORDS);
        assertThat(testStudy.getEligibility()).isEqualTo(DEFAULT_ELIGIBILITY);
        assertThat(testStudy.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testStudy.getAge_max()).isEqualTo(DEFAULT_AGE_MAX);
        assertThat(testStudy.getAge_min()).isEqualTo(DEFAULT_AGE_MIN);
        assertThat(testStudy.getHealthy_volunteer_accepted()).isEqualTo(DEFAULT_HEALTHY_VOLUNTEER_ACCEPTED);
        assertThat(testStudy.getPurpose()).isEqualTo(DEFAULT_PURPOSE);
        assertThat(testStudy.getAllocation()).isEqualTo(DEFAULT_ALLOCATION);
        assertThat(testStudy.getMasking()).isEqualTo(DEFAULT_MASKING);
        assertThat(testStudy.getControl()).isEqualTo(DEFAULT_CONTROL);
        assertThat(testStudy.getAssignment()).isEqualTo(DEFAULT_ASSIGNMENT);
        assertThat(testStudy.getEndpoint()).isEqualTo(DEFAULT_ENDPOINT);
        assertThat(testStudy.getInterventions()).isEqualTo(DEFAULT_INTERVENTIONS);
        assertThat(testStudy.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testStudy.getSelection()).isEqualTo(DEFAULT_SELECTION);
        assertThat(testStudy.getTiming()).isEqualTo(DEFAULT_TIMING);
        assertThat(testStudy.getOfficial_title()).isEqualTo(DEFAULT_OFFICIAL_TITLE);
        assertThat(testStudy.getResults_reference()).isEqualTo(DEFAULT_RESULTS_REFERENCE);
        assertThat(testStudy.getOc_oid()).isEqualTo(DEFAULT_OC_OID);
        assertThat(testStudy.getOld_status_id()).isEqualTo(DEFAULT_OLD_STATUS_ID);
    }

    @Test
    @Transactional
    public void getAllStudys() throws Exception {
        // Initialize the database
        studyRepository.saveAndFlush(study);

        // Get all the studys
        restStudyMockMvc.perform(get("/api/studys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(study.getId().intValue()))
                .andExpect(jsonPath("$.[0].parent_study_id").value(DEFAULT_PARENT_STUDY_ID))
                .andExpect(jsonPath("$.[0].unique_identifier").value(DEFAULT_UNIQUE_IDENTIFIER.toString()))
                .andExpect(jsonPath("$.[0].secondary_identifier").value(DEFAULT_SECONDARY_IDENTIFIER.toString()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].summary").value(DEFAULT_SUMMARY.toString()))
                .andExpect(jsonPath("$.[0].date_planned_start").value(DEFAULT_DATE_PLANNED_START_STR))
                .andExpect(jsonPath("$.[0].date_planned_end").value(DEFAULT_DATE_PLANNED_END_STR))
                .andExpect(jsonPath("$.[0].date_created").value(DEFAULT_DATE_CREATED_STR))
                .andExpect(jsonPath("$.[0].date_updated").value(DEFAULT_DATE_UPDATED_STR))
                .andExpect(jsonPath("$.[0].owner_id").value(DEFAULT_OWNER_ID))
                .andExpect(jsonPath("$.[0].update_id").value(DEFAULT_UPDATE_ID))
                .andExpect(jsonPath("$.[0].type_id").value(DEFAULT_TYPE_ID))
                .andExpect(jsonPath("$.[0].status_id").value(DEFAULT_STATUS_ID))
                .andExpect(jsonPath("$.[0].principal_investigator").value(DEFAULT_PRINCIPAL_INVESTIGATOR.toString()))
                .andExpect(jsonPath("$.[0].facility_name").value(DEFAULT_FACILITY_NAME.toString()))
                .andExpect(jsonPath("$.[0].facility_city").value(DEFAULT_FACILITY_CITY.toString()))
                .andExpect(jsonPath("$.[0].facility_state").value(DEFAULT_FACILITY_STATE.toString()))
                .andExpect(jsonPath("$.[0].facility_zip").value(DEFAULT_FACILITY_ZIP.toString()))
                .andExpect(jsonPath("$.[0].facility_country").value(DEFAULT_FACILITY_COUNTRY.toString()))
                .andExpect(jsonPath("$.[0].facility_recruitment_status").value(DEFAULT_FACILITY_RECRUITMENT_STATUS.toString()))
                .andExpect(jsonPath("$.[0].facility_contact_name").value(DEFAULT_FACILITY_CONTACT_NAME.toString()))
                .andExpect(jsonPath("$.[0].facility_contact_degree").value(DEFAULT_FACILITY_CONTACT_DEGREE.toString()))
                .andExpect(jsonPath("$.[0].facility_contact_phone").value(DEFAULT_FACILITY_CONTACT_PHONE.toString()))
                .andExpect(jsonPath("$.[0].facility_contact_email").value(DEFAULT_FACILITY_CONTACT_EMAIL.toString()))
                .andExpect(jsonPath("$.[0].protocol_type").value(DEFAULT_PROTOCOL_TYPE.toString()))
                .andExpect(jsonPath("$.[0].protocol_description").value(DEFAULT_PROTOCOL_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].protocol_date_verification").value(DEFAULT_PROTOCOL_DATE_VERIFICATION_STR))
                .andExpect(jsonPath("$.[0].phase").value(DEFAULT_PHASE.toString()))
                .andExpect(jsonPath("$.[0].expected_total_enrollment").value(DEFAULT_EXPECTED_TOTAL_ENROLLMENT))
                .andExpect(jsonPath("$.[0].sponsor").value(DEFAULT_SPONSOR.toString()))
                .andExpect(jsonPath("$.[0].collaborators").value(DEFAULT_COLLABORATORS.toString()))
                .andExpect(jsonPath("$.[0].medline_identifier").value(DEFAULT_MEDLINE_IDENTIFIER.toString()))
                .andExpect(jsonPath("$.[0].url").value(DEFAULT_URL.toString()))
                .andExpect(jsonPath("$.[0].url_description").value(DEFAULT_URL_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].conditions").value(DEFAULT_CONDITIONS.toString()))
                .andExpect(jsonPath("$.[0].keywords").value(DEFAULT_KEYWORDS.toString()))
                .andExpect(jsonPath("$.[0].eligibility").value(DEFAULT_ELIGIBILITY.toString()))
                .andExpect(jsonPath("$.[0].gender").value(DEFAULT_GENDER.toString()))
                .andExpect(jsonPath("$.[0].age_max").value(DEFAULT_AGE_MAX.toString()))
                .andExpect(jsonPath("$.[0].age_min").value(DEFAULT_AGE_MIN.toString()))
                .andExpect(jsonPath("$.[0].healthy_volunteer_accepted").value(DEFAULT_HEALTHY_VOLUNTEER_ACCEPTED.toString()))
                .andExpect(jsonPath("$.[0].purpose").value(DEFAULT_PURPOSE.toString()))
                .andExpect(jsonPath("$.[0].allocation").value(DEFAULT_ALLOCATION.toString()))
                .andExpect(jsonPath("$.[0].masking").value(DEFAULT_MASKING.toString()))
                .andExpect(jsonPath("$.[0].control").value(DEFAULT_CONTROL.toString()))
                .andExpect(jsonPath("$.[0].assignment").value(DEFAULT_ASSIGNMENT.toString()))
                .andExpect(jsonPath("$.[0].endpoint").value(DEFAULT_ENDPOINT.toString()))
                .andExpect(jsonPath("$.[0].interventions").value(DEFAULT_INTERVENTIONS.toString()))
                .andExpect(jsonPath("$.[0].duration").value(DEFAULT_DURATION.toString()))
                .andExpect(jsonPath("$.[0].selection").value(DEFAULT_SELECTION.toString()))
                .andExpect(jsonPath("$.[0].timing").value(DEFAULT_TIMING.toString()))
                .andExpect(jsonPath("$.[0].official_title").value(DEFAULT_OFFICIAL_TITLE.toString()))
                .andExpect(jsonPath("$.[0].results_reference").value(DEFAULT_RESULTS_REFERENCE.toString()))
                .andExpect(jsonPath("$.[0].oc_oid").value(DEFAULT_OC_OID.toString()))
                .andExpect(jsonPath("$.[0].old_status_id").value(DEFAULT_OLD_STATUS_ID));
    }

    @Test
    @Transactional
    public void getStudy() throws Exception {
        // Initialize the database
        studyRepository.saveAndFlush(study);

        // Get the study
        restStudyMockMvc.perform(get("/api/studys/{id}", study.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(study.getId().intValue()))
            .andExpect(jsonPath("$.parent_study_id").value(DEFAULT_PARENT_STUDY_ID))
            .andExpect(jsonPath("$.unique_identifier").value(DEFAULT_UNIQUE_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.secondary_identifier").value(DEFAULT_SECONDARY_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.summary").value(DEFAULT_SUMMARY.toString()))
            .andExpect(jsonPath("$.date_planned_start").value(DEFAULT_DATE_PLANNED_START_STR))
            .andExpect(jsonPath("$.date_planned_end").value(DEFAULT_DATE_PLANNED_END_STR))
            .andExpect(jsonPath("$.date_created").value(DEFAULT_DATE_CREATED_STR))
            .andExpect(jsonPath("$.date_updated").value(DEFAULT_DATE_UPDATED_STR))
            .andExpect(jsonPath("$.owner_id").value(DEFAULT_OWNER_ID))
            .andExpect(jsonPath("$.update_id").value(DEFAULT_UPDATE_ID))
            .andExpect(jsonPath("$.type_id").value(DEFAULT_TYPE_ID))
            .andExpect(jsonPath("$.status_id").value(DEFAULT_STATUS_ID))
            .andExpect(jsonPath("$.principal_investigator").value(DEFAULT_PRINCIPAL_INVESTIGATOR.toString()))
            .andExpect(jsonPath("$.facility_name").value(DEFAULT_FACILITY_NAME.toString()))
            .andExpect(jsonPath("$.facility_city").value(DEFAULT_FACILITY_CITY.toString()))
            .andExpect(jsonPath("$.facility_state").value(DEFAULT_FACILITY_STATE.toString()))
            .andExpect(jsonPath("$.facility_zip").value(DEFAULT_FACILITY_ZIP.toString()))
            .andExpect(jsonPath("$.facility_country").value(DEFAULT_FACILITY_COUNTRY.toString()))
            .andExpect(jsonPath("$.facility_recruitment_status").value(DEFAULT_FACILITY_RECRUITMENT_STATUS.toString()))
            .andExpect(jsonPath("$.facility_contact_name").value(DEFAULT_FACILITY_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.facility_contact_degree").value(DEFAULT_FACILITY_CONTACT_DEGREE.toString()))
            .andExpect(jsonPath("$.facility_contact_phone").value(DEFAULT_FACILITY_CONTACT_PHONE.toString()))
            .andExpect(jsonPath("$.facility_contact_email").value(DEFAULT_FACILITY_CONTACT_EMAIL.toString()))
            .andExpect(jsonPath("$.protocol_type").value(DEFAULT_PROTOCOL_TYPE.toString()))
            .andExpect(jsonPath("$.protocol_description").value(DEFAULT_PROTOCOL_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.protocol_date_verification").value(DEFAULT_PROTOCOL_DATE_VERIFICATION_STR))
            .andExpect(jsonPath("$.phase").value(DEFAULT_PHASE.toString()))
            .andExpect(jsonPath("$.expected_total_enrollment").value(DEFAULT_EXPECTED_TOTAL_ENROLLMENT))
            .andExpect(jsonPath("$.sponsor").value(DEFAULT_SPONSOR.toString()))
            .andExpect(jsonPath("$.collaborators").value(DEFAULT_COLLABORATORS.toString()))
            .andExpect(jsonPath("$.medline_identifier").value(DEFAULT_MEDLINE_IDENTIFIER.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.url_description").value(DEFAULT_URL_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.conditions").value(DEFAULT_CONDITIONS.toString()))
            .andExpect(jsonPath("$.keywords").value(DEFAULT_KEYWORDS.toString()))
            .andExpect(jsonPath("$.eligibility").value(DEFAULT_ELIGIBILITY.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.age_max").value(DEFAULT_AGE_MAX.toString()))
            .andExpect(jsonPath("$.age_min").value(DEFAULT_AGE_MIN.toString()))
            .andExpect(jsonPath("$.healthy_volunteer_accepted").value(DEFAULT_HEALTHY_VOLUNTEER_ACCEPTED.toString()))
            .andExpect(jsonPath("$.purpose").value(DEFAULT_PURPOSE.toString()))
            .andExpect(jsonPath("$.allocation").value(DEFAULT_ALLOCATION.toString()))
            .andExpect(jsonPath("$.masking").value(DEFAULT_MASKING.toString()))
            .andExpect(jsonPath("$.control").value(DEFAULT_CONTROL.toString()))
            .andExpect(jsonPath("$.assignment").value(DEFAULT_ASSIGNMENT.toString()))
            .andExpect(jsonPath("$.endpoint").value(DEFAULT_ENDPOINT.toString()))
            .andExpect(jsonPath("$.interventions").value(DEFAULT_INTERVENTIONS.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()))
            .andExpect(jsonPath("$.selection").value(DEFAULT_SELECTION.toString()))
            .andExpect(jsonPath("$.timing").value(DEFAULT_TIMING.toString()))
            .andExpect(jsonPath("$.official_title").value(DEFAULT_OFFICIAL_TITLE.toString()))
            .andExpect(jsonPath("$.results_reference").value(DEFAULT_RESULTS_REFERENCE.toString()))
            .andExpect(jsonPath("$.oc_oid").value(DEFAULT_OC_OID.toString()))
            .andExpect(jsonPath("$.old_status_id").value(DEFAULT_OLD_STATUS_ID));
    }

    @Test
    @Transactional
    public void getNonExistingStudy() throws Exception {
        // Get the study
        restStudyMockMvc.perform(get("/api/studys/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudy() throws Exception {
        // Initialize the database
        studyRepository.saveAndFlush(study);

        // Update the study
        study.setParent_study_id(UPDATED_PARENT_STUDY_ID);
        study.setUnique_identifier(UPDATED_UNIQUE_IDENTIFIER);
        study.setSecondary_identifier(UPDATED_SECONDARY_IDENTIFIER);
        study.setName(UPDATED_NAME);
        study.setSummary(UPDATED_SUMMARY);
//        study.setDate_planned_start(UPDATED_DATE_PLANNED_START);
//        study.setDate_planned_end(UPDATED_DATE_PLANNED_END);
//        study.setDate_created(UPDATED_DATE_CREATED);
//        study.setDate_updated(UPDATED_DATE_UPDATED);
        study.setOwner_id(UPDATED_OWNER_ID);
        study.setUpdate_id(UPDATED_UPDATE_ID);
        study.setType_id(UPDATED_TYPE_ID);
        study.setStatus_id(UPDATED_STATUS_ID);
        study.setPrincipal_investigator(UPDATED_PRINCIPAL_INVESTIGATOR);
        study.setFacility_name(UPDATED_FACILITY_NAME);
        study.setFacility_city(UPDATED_FACILITY_CITY);
        study.setFacility_state(UPDATED_FACILITY_STATE);
        study.setFacility_zip(UPDATED_FACILITY_ZIP);
        study.setFacility_country(UPDATED_FACILITY_COUNTRY);
        study.setFacility_recruitment_status(UPDATED_FACILITY_RECRUITMENT_STATUS);
        study.setFacility_contact_name(UPDATED_FACILITY_CONTACT_NAME);
        study.setFacility_contact_degree(UPDATED_FACILITY_CONTACT_DEGREE);
        study.setFacility_contact_phone(UPDATED_FACILITY_CONTACT_PHONE);
        study.setFacility_contact_email(UPDATED_FACILITY_CONTACT_EMAIL);
        study.setProtocol_type(UPDATED_PROTOCOL_TYPE);
        study.setProtocol_description(UPDATED_PROTOCOL_DESCRIPTION);
//        study.setProtocol_date_verification(UPDATED_PROTOCOL_DATE_VERIFICATION);
        study.setPhase(UPDATED_PHASE);
        study.setExpected_total_enrollment(UPDATED_EXPECTED_TOTAL_ENROLLMENT);
        study.setSponsor(UPDATED_SPONSOR);
        study.setCollaborators(UPDATED_COLLABORATORS);
        study.setMedline_identifier(UPDATED_MEDLINE_IDENTIFIER);
        study.setUrl(UPDATED_URL);
        study.setUrl_description(UPDATED_URL_DESCRIPTION);
        study.setConditions(UPDATED_CONDITIONS);
        study.setKeywords(UPDATED_KEYWORDS);
        study.setEligibility(UPDATED_ELIGIBILITY);
        study.setGender(UPDATED_GENDER);
        study.setAge_max(UPDATED_AGE_MAX);
        study.setAge_min(UPDATED_AGE_MIN);
//        study.setHealthy_volunteer_accepted(UPDATED_HEALTHY_VOLUNTEER_ACCEPTED);
        study.setPurpose(UPDATED_PURPOSE);
        study.setAllocation(UPDATED_ALLOCATION);
        study.setMasking(UPDATED_MASKING);
        study.setControl(UPDATED_CONTROL);
        study.setAssignment(UPDATED_ASSIGNMENT);
        study.setEndpoint(UPDATED_ENDPOINT);
        study.setInterventions(UPDATED_INTERVENTIONS);
        study.setDuration(UPDATED_DURATION);
        study.setSelection(UPDATED_SELECTION);
        study.setTiming(UPDATED_TIMING);
        study.setOfficial_title(UPDATED_OFFICIAL_TITLE);
//        study.setResults_reference(UPDATED_RESULTS_REFERENCE);
        study.setOc_oid(UPDATED_OC_OID);
        study.setOld_status_id(UPDATED_OLD_STATUS_ID);
        restStudyMockMvc.perform(post("/api/studys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(study)))
                .andExpect(status().isOk());

        // Validate the Study in the database
        List<Study> studys = studyRepository.findAll();
        assertThat(studys).hasSize(1);
        Study testStudy = studys.iterator().next();
        assertThat(testStudy.getParent_study_id()).isEqualTo(UPDATED_PARENT_STUDY_ID);
        assertThat(testStudy.getUnique_identifier()).isEqualTo(UPDATED_UNIQUE_IDENTIFIER);
        assertThat(testStudy.getSecondary_identifier()).isEqualTo(UPDATED_SECONDARY_IDENTIFIER);
        assertThat(testStudy.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudy.getSummary()).isEqualTo(UPDATED_SUMMARY);
//        assertThat(testStudy.getDate_planned_start().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_PLANNED_START);
//        assertThat(testStudy.getDate_planned_end().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_PLANNED_END);
//        assertThat(testStudy.getDate_created().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_CREATED);
//        assertThat(testStudy.getDate_updated().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_UPDATED);
        assertThat(testStudy.getOwner_id()).isEqualTo(UPDATED_OWNER_ID);
        assertThat(testStudy.getUpdate_id()).isEqualTo(UPDATED_UPDATE_ID);
        assertThat(testStudy.getType_id()).isEqualTo(UPDATED_TYPE_ID);
        assertThat(testStudy.getStatus_id()).isEqualTo(UPDATED_STATUS_ID);
        assertThat(testStudy.getPrincipal_investigator()).isEqualTo(UPDATED_PRINCIPAL_INVESTIGATOR);
        assertThat(testStudy.getFacility_name()).isEqualTo(UPDATED_FACILITY_NAME);
        assertThat(testStudy.getFacility_city()).isEqualTo(UPDATED_FACILITY_CITY);
        assertThat(testStudy.getFacility_state()).isEqualTo(UPDATED_FACILITY_STATE);
        assertThat(testStudy.getFacility_zip()).isEqualTo(UPDATED_FACILITY_ZIP);
        assertThat(testStudy.getFacility_country()).isEqualTo(UPDATED_FACILITY_COUNTRY);
        assertThat(testStudy.getFacility_recruitment_status()).isEqualTo(UPDATED_FACILITY_RECRUITMENT_STATUS);
        assertThat(testStudy.getFacility_contact_name()).isEqualTo(UPDATED_FACILITY_CONTACT_NAME);
        assertThat(testStudy.getFacility_contact_degree()).isEqualTo(UPDATED_FACILITY_CONTACT_DEGREE);
        assertThat(testStudy.getFacility_contact_phone()).isEqualTo(UPDATED_FACILITY_CONTACT_PHONE);
        assertThat(testStudy.getFacility_contact_email()).isEqualTo(UPDATED_FACILITY_CONTACT_EMAIL);
        assertThat(testStudy.getProtocol_type()).isEqualTo(UPDATED_PROTOCOL_TYPE);
        assertThat(testStudy.getProtocol_description()).isEqualTo(UPDATED_PROTOCOL_DESCRIPTION);
//        assertThat(testStudy.getProtocol_date_verification().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_PROTOCOL_DATE_VERIFICATION);
        assertThat(testStudy.getPhase()).isEqualTo(UPDATED_PHASE);
        assertThat(testStudy.getExpected_total_enrollment()).isEqualTo(UPDATED_EXPECTED_TOTAL_ENROLLMENT);
        assertThat(testStudy.getSponsor()).isEqualTo(UPDATED_SPONSOR);
        assertThat(testStudy.getCollaborators()).isEqualTo(UPDATED_COLLABORATORS);
        assertThat(testStudy.getMedline_identifier()).isEqualTo(UPDATED_MEDLINE_IDENTIFIER);
        assertThat(testStudy.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testStudy.getUrl_description()).isEqualTo(UPDATED_URL_DESCRIPTION);
        assertThat(testStudy.getConditions()).isEqualTo(UPDATED_CONDITIONS);
        assertThat(testStudy.getKeywords()).isEqualTo(UPDATED_KEYWORDS);
        assertThat(testStudy.getEligibility()).isEqualTo(UPDATED_ELIGIBILITY);
        assertThat(testStudy.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testStudy.getAge_max()).isEqualTo(UPDATED_AGE_MAX);
        assertThat(testStudy.getAge_min()).isEqualTo(UPDATED_AGE_MIN);
        assertThat(testStudy.getHealthy_volunteer_accepted()).isEqualTo(UPDATED_HEALTHY_VOLUNTEER_ACCEPTED);
        assertThat(testStudy.getPurpose()).isEqualTo(UPDATED_PURPOSE);
        assertThat(testStudy.getAllocation()).isEqualTo(UPDATED_ALLOCATION);
        assertThat(testStudy.getMasking()).isEqualTo(UPDATED_MASKING);
        assertThat(testStudy.getControl()).isEqualTo(UPDATED_CONTROL);
        assertThat(testStudy.getAssignment()).isEqualTo(UPDATED_ASSIGNMENT);
        assertThat(testStudy.getEndpoint()).isEqualTo(UPDATED_ENDPOINT);
        assertThat(testStudy.getInterventions()).isEqualTo(UPDATED_INTERVENTIONS);
        assertThat(testStudy.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testStudy.getSelection()).isEqualTo(UPDATED_SELECTION);
        assertThat(testStudy.getTiming()).isEqualTo(UPDATED_TIMING);
        assertThat(testStudy.getOfficial_title()).isEqualTo(UPDATED_OFFICIAL_TITLE);
        assertThat(testStudy.getResults_reference()).isEqualTo(UPDATED_RESULTS_REFERENCE);
        assertThat(testStudy.getOc_oid()).isEqualTo(UPDATED_OC_OID);
        assertThat(testStudy.getOld_status_id()).isEqualTo(UPDATED_OLD_STATUS_ID);
    }

    @Test
    @Transactional
    public void deleteStudy() throws Exception {
        // Initialize the database
        studyRepository.saveAndFlush(study);

        // Get the study
        restStudyMockMvc.perform(delete("/api/studys/{id}", study.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Study> studys = studyRepository.findAll();
        assertThat(studys).hasSize(0);
    }
}
