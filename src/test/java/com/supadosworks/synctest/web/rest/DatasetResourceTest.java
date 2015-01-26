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
import org.joda.time.LocalDate;
import java.util.List;

import com.supadosworks.synctest.Application;
import com.supadosworks.synctest.domain.Dataset;
import com.supadosworks.synctest.repository.DatasetRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DatasetResource REST controller.
 *
 * @see DatasetResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DatasetResourceTest {


    private static final Integer DEFAULT_STUDY_ID = 0;
    private static final Integer UPDATED_STUDY_ID = 1;

    private static final Integer DEFAULT_STATUS_ID = 0;
    private static final Integer UPDATED_STATUS_ID = 1;
    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";
    private static final String DEFAULT_SQL_STATEMENT = "SAMPLE_TEXT";
    private static final String UPDATED_SQL_STATEMENT = "UPDATED_TEXT";

    private static final Integer DEFAULT_NUM_RUNS = 0;
    private static final Integer UPDATED_NUM_RUNS = 1;

    private static final LocalDate DEFAULT_DATE_START = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE_START = new LocalDate();

    private static final LocalDate DEFAULT_DATE_END = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE_END = new LocalDate();

    private static final LocalDate DEFAULT_DATE_CREATED = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE_CREATED = new LocalDate();

    private static final LocalDate DEFAULT_DATE_UPDATED = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE_UPDATED = new LocalDate();

    private static final LocalDate DEFAULT_DATE_LAST_RUN = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE_LAST_RUN = new LocalDate();

    private static final Integer DEFAULT_OWNER_ID = 0;
    private static final Integer UPDATED_OWNER_ID = 1;

    private static final Integer DEFAULT_APPROVER_ID = 0;
    private static final Integer UPDATED_APPROVER_ID = 1;

    private static final Integer DEFAULT_UPDATE_ID = 0;
    private static final Integer UPDATED_UPDATE_ID = 1;

    private static final Boolean DEFAULT_SHOW_EVENT_LOCATION = false;
    private static final Boolean UPDATED_SHOW_EVENT_LOCATION = true;

    private static final Boolean DEFAULT_SHOW_EVENT_START = false;
    private static final Boolean UPDATED_SHOW_EVENT_START = true;

    private static final Boolean DEFAULT_SHOW_EVENT_END = false;
    private static final Boolean UPDATED_SHOW_EVENT_END = true;

    private static final Boolean DEFAULT_SHOW_SUBJECT_DOB = false;
    private static final Boolean UPDATED_SHOW_SUBJECT_DOB = true;

    private static final Boolean DEFAULT_SHOW_SUBJECT_GENDER = false;
    private static final Boolean UPDATED_SHOW_SUBJECT_GENDER = true;

    private static final Boolean DEFAULT_SHOW_EVENT_STATUS = false;
    private static final Boolean UPDATED_SHOW_EVENT_STATUS = true;

    private static final Boolean DEFAULT_SHOW_SUBJECT_STATUS = false;
    private static final Boolean UPDATED_SHOW_SUBJECT_STATUS = true;

    private static final Boolean DEFAULT_SHOW_SUBJECT_UNIQUE_ID = false;
    private static final Boolean UPDATED_SHOW_SUBJECT_UNIQUE_ID = true;

    private static final Boolean DEFAULT_SHOW_SUBJECT_AGE_AT_EVENT = false;
    private static final Boolean UPDATED_SHOW_SUBJECT_AGE_AT_EVENT = true;

    private static final Boolean DEFAULT_SHOW_CRF_STATUS = false;
    private static final Boolean UPDATED_SHOW_CRF_STATUS = true;

    private static final Boolean DEFAULT_SHOW_CRF_VERSION = false;
    private static final Boolean UPDATED_SHOW_CRF_VERSION = true;

    private static final Boolean DEFAULT_SHOW_CRF_INT_NAME = false;
    private static final Boolean UPDATED_SHOW_CRF_INT_NAME = true;

    private static final Boolean DEFAULT_SHOW_CRF_INT_DATE = false;
    private static final Boolean UPDATED_SHOW_CRF_INT_DATE = true;

    private static final Boolean DEFAULT_SHOW_GROUP_INFO = false;
    private static final Boolean UPDATED_SHOW_GROUP_INFO = true;

    private static final Boolean DEFAULT_SHOW_DISC_INFO = false;
    private static final Boolean UPDATED_SHOW_DISC_INFO = true;
    private static final String DEFAULT_ODM_METADATAVERSION_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_ODM_METADATAVERSION_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_ODM_METADATAVERSION_OID = "SAMPLE_TEXT";
    private static final String UPDATED_ODM_METADATAVERSION_OID = "UPDATED_TEXT";
    private static final String DEFAULT_ODM_PRIOR_STUDY_OID = "SAMPLE_TEXT";
    private static final String UPDATED_ODM_PRIOR_STUDY_OID = "UPDATED_TEXT";
    private static final String DEFAULT_ODM_PRIOR_METADATAVERSION_OID = "SAMPLE_TEXT";
    private static final String UPDATED_ODM_PRIOR_METADATAVERSION_OID = "UPDATED_TEXT";

    private static final Boolean DEFAULT_SHOW_SECONDARY_ID = false;
    private static final Boolean UPDATED_SHOW_SECONDARY_ID = true;

    private static final Integer DEFAULT_DATASET_ITEM_STATUS_ID = 0;
    private static final Integer UPDATED_DATASET_ITEM_STATUS_ID = 1;

    @Inject
    private DatasetRepository datasetRepository;

    private MockMvc restDatasetMockMvc;

    private Dataset dataset;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DatasetResource datasetResource = new DatasetResource();
        ReflectionTestUtils.setField(datasetResource, "datasetRepository", datasetRepository);
        this.restDatasetMockMvc = MockMvcBuilders.standaloneSetup(datasetResource).build();
    }

    @Before
    public void initTest() {
        dataset = new Dataset();
        dataset.setStudy_id(DEFAULT_STUDY_ID);
        dataset.setStatus_id(DEFAULT_STATUS_ID);
        dataset.setName(DEFAULT_NAME);
        dataset.setDescription(DEFAULT_DESCRIPTION);
        dataset.setSql_statement(DEFAULT_SQL_STATEMENT);
        dataset.setNum_runs(DEFAULT_NUM_RUNS);
//        dataset.setDate_start(DEFAULT_DATE_START);
//        dataset.setDate_end(DEFAULT_DATE_END);
//        dataset.setDate_created(DEFAULT_DATE_CREATED);
//        dataset.setDate_updated(DEFAULT_DATE_UPDATED);
//        dataset.setDate_last_run(DEFAULT_DATE_LAST_RUN);
        dataset.setOwner_id(DEFAULT_OWNER_ID);
        dataset.setApprover_id(DEFAULT_APPROVER_ID);
        dataset.setUpdate_id(DEFAULT_UPDATE_ID);
        dataset.setShow_event_location(DEFAULT_SHOW_EVENT_LOCATION);
        dataset.setShow_event_start(DEFAULT_SHOW_EVENT_START);
        dataset.setShow_event_end(DEFAULT_SHOW_EVENT_END);
        dataset.setShow_subject_dob(DEFAULT_SHOW_SUBJECT_DOB);
        dataset.setShow_subject_gender(DEFAULT_SHOW_SUBJECT_GENDER);
        dataset.setShow_event_status(DEFAULT_SHOW_EVENT_STATUS);
        dataset.setShow_subject_status(DEFAULT_SHOW_SUBJECT_STATUS);
        dataset.setShow_subject_unique_id(DEFAULT_SHOW_SUBJECT_UNIQUE_ID);
        dataset.setShow_subject_age_at_event(DEFAULT_SHOW_SUBJECT_AGE_AT_EVENT);
        dataset.setShow_crf_status(DEFAULT_SHOW_CRF_STATUS);
        dataset.setShow_crf_version(DEFAULT_SHOW_CRF_VERSION);
        dataset.setShow_crf_int_name(DEFAULT_SHOW_CRF_INT_NAME);
        dataset.setShow_crf_int_date(DEFAULT_SHOW_CRF_INT_DATE);
        dataset.setShow_group_info(DEFAULT_SHOW_GROUP_INFO);
        dataset.setShow_disc_info(DEFAULT_SHOW_DISC_INFO);
        dataset.setOdm_metadataversion_name(DEFAULT_ODM_METADATAVERSION_NAME);
        dataset.setOdm_metadataversion_oid(DEFAULT_ODM_METADATAVERSION_OID);
        dataset.setOdm_prior_study_oid(DEFAULT_ODM_PRIOR_STUDY_OID);
        dataset.setOdm_prior_metadataversion_oid(DEFAULT_ODM_PRIOR_METADATAVERSION_OID);
        dataset.setShow_secondary_id(DEFAULT_SHOW_SECONDARY_ID);
        dataset.setDataset_item_status_id(DEFAULT_DATASET_ITEM_STATUS_ID);
    }

    @Test
    @Transactional
    public void createDataset() throws Exception {
        // Validate the database is empty
        assertThat(datasetRepository.findAll()).hasSize(0);

        // Create the Dataset
        restDatasetMockMvc.perform(post("/api/datasets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dataset)))
                .andExpect(status().isOk());

        // Validate the Dataset in the database
        List<Dataset> datasets = datasetRepository.findAll();
        assertThat(datasets).hasSize(1);
        Dataset testDataset = datasets.iterator().next();
        assertThat(testDataset.getStudy_id()).isEqualTo(DEFAULT_STUDY_ID);
        assertThat(testDataset.getStatus_id()).isEqualTo(DEFAULT_STATUS_ID);
        assertThat(testDataset.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDataset.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDataset.getSql_statement()).isEqualTo(DEFAULT_SQL_STATEMENT);
        assertThat(testDataset.getNum_runs()).isEqualTo(DEFAULT_NUM_RUNS);
        assertThat(testDataset.getDate_start()).isEqualTo(DEFAULT_DATE_START);
        assertThat(testDataset.getDate_end()).isEqualTo(DEFAULT_DATE_END);
        assertThat(testDataset.getDate_created()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testDataset.getDate_updated()).isEqualTo(DEFAULT_DATE_UPDATED);
        assertThat(testDataset.getDate_last_run()).isEqualTo(DEFAULT_DATE_LAST_RUN);
        assertThat(testDataset.getOwner_id()).isEqualTo(DEFAULT_OWNER_ID);
        assertThat(testDataset.getApprover_id()).isEqualTo(DEFAULT_APPROVER_ID);
        assertThat(testDataset.getUpdate_id()).isEqualTo(DEFAULT_UPDATE_ID);
        assertThat(testDataset.getShow_event_location()).isEqualTo(DEFAULT_SHOW_EVENT_LOCATION);
        assertThat(testDataset.getShow_event_start()).isEqualTo(DEFAULT_SHOW_EVENT_START);
        assertThat(testDataset.getShow_event_end()).isEqualTo(DEFAULT_SHOW_EVENT_END);
        assertThat(testDataset.getShow_subject_dob()).isEqualTo(DEFAULT_SHOW_SUBJECT_DOB);
        assertThat(testDataset.getShow_subject_gender()).isEqualTo(DEFAULT_SHOW_SUBJECT_GENDER);
        assertThat(testDataset.getShow_event_status()).isEqualTo(DEFAULT_SHOW_EVENT_STATUS);
        assertThat(testDataset.getShow_subject_status()).isEqualTo(DEFAULT_SHOW_SUBJECT_STATUS);
        assertThat(testDataset.getShow_subject_unique_id()).isEqualTo(DEFAULT_SHOW_SUBJECT_UNIQUE_ID);
        assertThat(testDataset.getShow_subject_age_at_event()).isEqualTo(DEFAULT_SHOW_SUBJECT_AGE_AT_EVENT);
        assertThat(testDataset.getShow_crf_status()).isEqualTo(DEFAULT_SHOW_CRF_STATUS);
        assertThat(testDataset.getShow_crf_version()).isEqualTo(DEFAULT_SHOW_CRF_VERSION);
        assertThat(testDataset.getShow_crf_int_name()).isEqualTo(DEFAULT_SHOW_CRF_INT_NAME);
        assertThat(testDataset.getShow_crf_int_date()).isEqualTo(DEFAULT_SHOW_CRF_INT_DATE);
        assertThat(testDataset.getShow_group_info()).isEqualTo(DEFAULT_SHOW_GROUP_INFO);
        assertThat(testDataset.getShow_disc_info()).isEqualTo(DEFAULT_SHOW_DISC_INFO);
        assertThat(testDataset.getOdm_metadataversion_name()).isEqualTo(DEFAULT_ODM_METADATAVERSION_NAME);
        assertThat(testDataset.getOdm_metadataversion_oid()).isEqualTo(DEFAULT_ODM_METADATAVERSION_OID);
        assertThat(testDataset.getOdm_prior_study_oid()).isEqualTo(DEFAULT_ODM_PRIOR_STUDY_OID);
        assertThat(testDataset.getOdm_prior_metadataversion_oid()).isEqualTo(DEFAULT_ODM_PRIOR_METADATAVERSION_OID);
        assertThat(testDataset.getShow_secondary_id()).isEqualTo(DEFAULT_SHOW_SECONDARY_ID);
        assertThat(testDataset.getDataset_item_status_id()).isEqualTo(DEFAULT_DATASET_ITEM_STATUS_ID);
    }

    @Test
    @Transactional
    public void getAllDatasets() throws Exception {
        // Initialize the database
        datasetRepository.saveAndFlush(dataset);

        // Get all the datasets
        restDatasetMockMvc.perform(get("/api/datasets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(dataset.getId().intValue()))
                .andExpect(jsonPath("$.[0].study_id").value(DEFAULT_STUDY_ID))
                .andExpect(jsonPath("$.[0].status_id").value(DEFAULT_STATUS_ID))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].sql_statement").value(DEFAULT_SQL_STATEMENT.toString()))
                .andExpect(jsonPath("$.[0].num_runs").value(DEFAULT_NUM_RUNS))
                .andExpect(jsonPath("$.[0].date_start").value(DEFAULT_DATE_START.toString()))
                .andExpect(jsonPath("$.[0].date_end").value(DEFAULT_DATE_END.toString()))
                .andExpect(jsonPath("$.[0].date_created").value(DEFAULT_DATE_CREATED.toString()))
                .andExpect(jsonPath("$.[0].date_updated").value(DEFAULT_DATE_UPDATED.toString()))
                .andExpect(jsonPath("$.[0].date_last_run").value(DEFAULT_DATE_LAST_RUN.toString()))
                .andExpect(jsonPath("$.[0].owner_id").value(DEFAULT_OWNER_ID))
                .andExpect(jsonPath("$.[0].approver_id").value(DEFAULT_APPROVER_ID))
                .andExpect(jsonPath("$.[0].update_id").value(DEFAULT_UPDATE_ID))
                .andExpect(jsonPath("$.[0].show_event_location").value(DEFAULT_SHOW_EVENT_LOCATION.booleanValue()))
                .andExpect(jsonPath("$.[0].show_event_start").value(DEFAULT_SHOW_EVENT_START.booleanValue()))
                .andExpect(jsonPath("$.[0].show_event_end").value(DEFAULT_SHOW_EVENT_END.booleanValue()))
                .andExpect(jsonPath("$.[0].show_subject_dob").value(DEFAULT_SHOW_SUBJECT_DOB.booleanValue()))
                .andExpect(jsonPath("$.[0].show_subject_gender").value(DEFAULT_SHOW_SUBJECT_GENDER.booleanValue()))
                .andExpect(jsonPath("$.[0].show_event_status").value(DEFAULT_SHOW_EVENT_STATUS.booleanValue()))
                .andExpect(jsonPath("$.[0].show_subject_status").value(DEFAULT_SHOW_SUBJECT_STATUS.booleanValue()))
                .andExpect(jsonPath("$.[0].show_subject_unique_id").value(DEFAULT_SHOW_SUBJECT_UNIQUE_ID.booleanValue()))
                .andExpect(jsonPath("$.[0].show_subject_age_at_event").value(DEFAULT_SHOW_SUBJECT_AGE_AT_EVENT.booleanValue()))
                .andExpect(jsonPath("$.[0].show_crf_status").value(DEFAULT_SHOW_CRF_STATUS.booleanValue()))
                .andExpect(jsonPath("$.[0].show_crf_version").value(DEFAULT_SHOW_CRF_VERSION.booleanValue()))
                .andExpect(jsonPath("$.[0].show_crf_int_name").value(DEFAULT_SHOW_CRF_INT_NAME.booleanValue()))
                .andExpect(jsonPath("$.[0].show_crf_int_date").value(DEFAULT_SHOW_CRF_INT_DATE.booleanValue()))
                .andExpect(jsonPath("$.[0].show_group_info").value(DEFAULT_SHOW_GROUP_INFO.booleanValue()))
                .andExpect(jsonPath("$.[0].show_disc_info").value(DEFAULT_SHOW_DISC_INFO.booleanValue()))
                .andExpect(jsonPath("$.[0].odm_metadataversion_name").value(DEFAULT_ODM_METADATAVERSION_NAME.toString()))
                .andExpect(jsonPath("$.[0].odm_metadataversion_oid").value(DEFAULT_ODM_METADATAVERSION_OID.toString()))
                .andExpect(jsonPath("$.[0].odm_prior_study_oid").value(DEFAULT_ODM_PRIOR_STUDY_OID.toString()))
                .andExpect(jsonPath("$.[0].odm_prior_metadataversion_oid").value(DEFAULT_ODM_PRIOR_METADATAVERSION_OID.toString()))
                .andExpect(jsonPath("$.[0].show_secondary_id").value(DEFAULT_SHOW_SECONDARY_ID.booleanValue()))
                .andExpect(jsonPath("$.[0].dataset_item_status_id").value(DEFAULT_DATASET_ITEM_STATUS_ID));
    }

    @Test
    @Transactional
    public void getDataset() throws Exception {
        // Initialize the database
        datasetRepository.saveAndFlush(dataset);

        // Get the dataset
        restDatasetMockMvc.perform(get("/api/datasets/{id}", dataset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(dataset.getId().intValue()))
            .andExpect(jsonPath("$.study_id").value(DEFAULT_STUDY_ID))
            .andExpect(jsonPath("$.status_id").value(DEFAULT_STATUS_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.sql_statement").value(DEFAULT_SQL_STATEMENT.toString()))
            .andExpect(jsonPath("$.num_runs").value(DEFAULT_NUM_RUNS))
            .andExpect(jsonPath("$.date_start").value(DEFAULT_DATE_START.toString()))
            .andExpect(jsonPath("$.date_end").value(DEFAULT_DATE_END.toString()))
            .andExpect(jsonPath("$.date_created").value(DEFAULT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.date_updated").value(DEFAULT_DATE_UPDATED.toString()))
            .andExpect(jsonPath("$.date_last_run").value(DEFAULT_DATE_LAST_RUN.toString()))
            .andExpect(jsonPath("$.owner_id").value(DEFAULT_OWNER_ID))
            .andExpect(jsonPath("$.approver_id").value(DEFAULT_APPROVER_ID))
            .andExpect(jsonPath("$.update_id").value(DEFAULT_UPDATE_ID))
            .andExpect(jsonPath("$.show_event_location").value(DEFAULT_SHOW_EVENT_LOCATION.booleanValue()))
            .andExpect(jsonPath("$.show_event_start").value(DEFAULT_SHOW_EVENT_START.booleanValue()))
            .andExpect(jsonPath("$.show_event_end").value(DEFAULT_SHOW_EVENT_END.booleanValue()))
            .andExpect(jsonPath("$.show_subject_dob").value(DEFAULT_SHOW_SUBJECT_DOB.booleanValue()))
            .andExpect(jsonPath("$.show_subject_gender").value(DEFAULT_SHOW_SUBJECT_GENDER.booleanValue()))
            .andExpect(jsonPath("$.show_event_status").value(DEFAULT_SHOW_EVENT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.show_subject_status").value(DEFAULT_SHOW_SUBJECT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.show_subject_unique_id").value(DEFAULT_SHOW_SUBJECT_UNIQUE_ID.booleanValue()))
            .andExpect(jsonPath("$.show_subject_age_at_event").value(DEFAULT_SHOW_SUBJECT_AGE_AT_EVENT.booleanValue()))
            .andExpect(jsonPath("$.show_crf_status").value(DEFAULT_SHOW_CRF_STATUS.booleanValue()))
            .andExpect(jsonPath("$.show_crf_version").value(DEFAULT_SHOW_CRF_VERSION.booleanValue()))
            .andExpect(jsonPath("$.show_crf_int_name").value(DEFAULT_SHOW_CRF_INT_NAME.booleanValue()))
            .andExpect(jsonPath("$.show_crf_int_date").value(DEFAULT_SHOW_CRF_INT_DATE.booleanValue()))
            .andExpect(jsonPath("$.show_group_info").value(DEFAULT_SHOW_GROUP_INFO.booleanValue()))
            .andExpect(jsonPath("$.show_disc_info").value(DEFAULT_SHOW_DISC_INFO.booleanValue()))
            .andExpect(jsonPath("$.odm_metadataversion_name").value(DEFAULT_ODM_METADATAVERSION_NAME.toString()))
            .andExpect(jsonPath("$.odm_metadataversion_oid").value(DEFAULT_ODM_METADATAVERSION_OID.toString()))
            .andExpect(jsonPath("$.odm_prior_study_oid").value(DEFAULT_ODM_PRIOR_STUDY_OID.toString()))
            .andExpect(jsonPath("$.odm_prior_metadataversion_oid").value(DEFAULT_ODM_PRIOR_METADATAVERSION_OID.toString()))
            .andExpect(jsonPath("$.show_secondary_id").value(DEFAULT_SHOW_SECONDARY_ID.booleanValue()))
            .andExpect(jsonPath("$.dataset_item_status_id").value(DEFAULT_DATASET_ITEM_STATUS_ID));
    }

    @Test
    @Transactional
    public void getNonExistingDataset() throws Exception {
        // Get the dataset
        restDatasetMockMvc.perform(get("/api/datasets/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataset() throws Exception {
        // Initialize the database
        datasetRepository.saveAndFlush(dataset);

        // Update the dataset
        dataset.setStudy_id(UPDATED_STUDY_ID);
        dataset.setStatus_id(UPDATED_STATUS_ID);
        dataset.setName(UPDATED_NAME);
        dataset.setDescription(UPDATED_DESCRIPTION);
        dataset.setSql_statement(UPDATED_SQL_STATEMENT);
        dataset.setNum_runs(UPDATED_NUM_RUNS);
//        dataset.setDate_start(UPDATED_DATE_START);
//        dataset.setDate_end(UPDATED_DATE_END);
//        dataset.setDate_created(UPDATED_DATE_CREATED);
//        dataset.setDate_updated(UPDATED_DATE_UPDATED);
//        dataset.setDate_last_run(UPDATED_DATE_LAST_RUN);
        dataset.setOwner_id(UPDATED_OWNER_ID);
        dataset.setApprover_id(UPDATED_APPROVER_ID);
        dataset.setUpdate_id(UPDATED_UPDATE_ID);
        dataset.setShow_event_location(UPDATED_SHOW_EVENT_LOCATION);
        dataset.setShow_event_start(UPDATED_SHOW_EVENT_START);
        dataset.setShow_event_end(UPDATED_SHOW_EVENT_END);
        dataset.setShow_subject_dob(UPDATED_SHOW_SUBJECT_DOB);
        dataset.setShow_subject_gender(UPDATED_SHOW_SUBJECT_GENDER);
        dataset.setShow_event_status(UPDATED_SHOW_EVENT_STATUS);
        dataset.setShow_subject_status(UPDATED_SHOW_SUBJECT_STATUS);
        dataset.setShow_subject_unique_id(UPDATED_SHOW_SUBJECT_UNIQUE_ID);
        dataset.setShow_subject_age_at_event(UPDATED_SHOW_SUBJECT_AGE_AT_EVENT);
        dataset.setShow_crf_status(UPDATED_SHOW_CRF_STATUS);
        dataset.setShow_crf_version(UPDATED_SHOW_CRF_VERSION);
        dataset.setShow_crf_int_name(UPDATED_SHOW_CRF_INT_NAME);
        dataset.setShow_crf_int_date(UPDATED_SHOW_CRF_INT_DATE);
        dataset.setShow_group_info(UPDATED_SHOW_GROUP_INFO);
        dataset.setShow_disc_info(UPDATED_SHOW_DISC_INFO);
        dataset.setOdm_metadataversion_name(UPDATED_ODM_METADATAVERSION_NAME);
        dataset.setOdm_metadataversion_oid(UPDATED_ODM_METADATAVERSION_OID);
        dataset.setOdm_prior_study_oid(UPDATED_ODM_PRIOR_STUDY_OID);
        dataset.setOdm_prior_metadataversion_oid(UPDATED_ODM_PRIOR_METADATAVERSION_OID);
        dataset.setShow_secondary_id(UPDATED_SHOW_SECONDARY_ID);
        dataset.setDataset_item_status_id(UPDATED_DATASET_ITEM_STATUS_ID);
        restDatasetMockMvc.perform(post("/api/datasets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dataset)))
                .andExpect(status().isOk());

        // Validate the Dataset in the database
        List<Dataset> datasets = datasetRepository.findAll();
        assertThat(datasets).hasSize(1);
        Dataset testDataset = datasets.iterator().next();
        assertThat(testDataset.getStudy_id()).isEqualTo(UPDATED_STUDY_ID);
        assertThat(testDataset.getStatus_id()).isEqualTo(UPDATED_STATUS_ID);
        assertThat(testDataset.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDataset.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDataset.getSql_statement()).isEqualTo(UPDATED_SQL_STATEMENT);
        assertThat(testDataset.getNum_runs()).isEqualTo(UPDATED_NUM_RUNS);
        assertThat(testDataset.getDate_start()).isEqualTo(UPDATED_DATE_START);
        assertThat(testDataset.getDate_end()).isEqualTo(UPDATED_DATE_END);
        assertThat(testDataset.getDate_created()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testDataset.getDate_updated()).isEqualTo(UPDATED_DATE_UPDATED);
        assertThat(testDataset.getDate_last_run()).isEqualTo(UPDATED_DATE_LAST_RUN);
        assertThat(testDataset.getOwner_id()).isEqualTo(UPDATED_OWNER_ID);
        assertThat(testDataset.getApprover_id()).isEqualTo(UPDATED_APPROVER_ID);
        assertThat(testDataset.getUpdate_id()).isEqualTo(UPDATED_UPDATE_ID);
        assertThat(testDataset.getShow_event_location()).isEqualTo(UPDATED_SHOW_EVENT_LOCATION);
        assertThat(testDataset.getShow_event_start()).isEqualTo(UPDATED_SHOW_EVENT_START);
        assertThat(testDataset.getShow_event_end()).isEqualTo(UPDATED_SHOW_EVENT_END);
        assertThat(testDataset.getShow_subject_dob()).isEqualTo(UPDATED_SHOW_SUBJECT_DOB);
        assertThat(testDataset.getShow_subject_gender()).isEqualTo(UPDATED_SHOW_SUBJECT_GENDER);
        assertThat(testDataset.getShow_event_status()).isEqualTo(UPDATED_SHOW_EVENT_STATUS);
        assertThat(testDataset.getShow_subject_status()).isEqualTo(UPDATED_SHOW_SUBJECT_STATUS);
        assertThat(testDataset.getShow_subject_unique_id()).isEqualTo(UPDATED_SHOW_SUBJECT_UNIQUE_ID);
        assertThat(testDataset.getShow_subject_age_at_event()).isEqualTo(UPDATED_SHOW_SUBJECT_AGE_AT_EVENT);
        assertThat(testDataset.getShow_crf_status()).isEqualTo(UPDATED_SHOW_CRF_STATUS);
        assertThat(testDataset.getShow_crf_version()).isEqualTo(UPDATED_SHOW_CRF_VERSION);
        assertThat(testDataset.getShow_crf_int_name()).isEqualTo(UPDATED_SHOW_CRF_INT_NAME);
        assertThat(testDataset.getShow_crf_int_date()).isEqualTo(UPDATED_SHOW_CRF_INT_DATE);
        assertThat(testDataset.getShow_group_info()).isEqualTo(UPDATED_SHOW_GROUP_INFO);
        assertThat(testDataset.getShow_disc_info()).isEqualTo(UPDATED_SHOW_DISC_INFO);
        assertThat(testDataset.getOdm_metadataversion_name()).isEqualTo(UPDATED_ODM_METADATAVERSION_NAME);
        assertThat(testDataset.getOdm_metadataversion_oid()).isEqualTo(UPDATED_ODM_METADATAVERSION_OID);
        assertThat(testDataset.getOdm_prior_study_oid()).isEqualTo(UPDATED_ODM_PRIOR_STUDY_OID);
        assertThat(testDataset.getOdm_prior_metadataversion_oid()).isEqualTo(UPDATED_ODM_PRIOR_METADATAVERSION_OID);
        assertThat(testDataset.getShow_secondary_id()).isEqualTo(UPDATED_SHOW_SECONDARY_ID);
        assertThat(testDataset.getDataset_item_status_id()).isEqualTo(UPDATED_DATASET_ITEM_STATUS_ID);
    }

    @Test
    @Transactional
    public void deleteDataset() throws Exception {
        // Initialize the database
        datasetRepository.saveAndFlush(dataset);

        // Get the dataset
        restDatasetMockMvc.perform(delete("/api/datasets/{id}", dataset.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Dataset> datasets = datasetRepository.findAll();
        assertThat(datasets).hasSize(0);
    }
}
