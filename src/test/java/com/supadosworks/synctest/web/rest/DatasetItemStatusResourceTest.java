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
import java.util.List;

import com.supadosworks.synctest.Application;
import com.supadosworks.synctest.domain.DatasetItemStatus;
import com.supadosworks.synctest.repository.DatasetItemStatusRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DatasetItemStatusResource REST controller.
 *
 * @see DatasetItemStatusResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DatasetItemStatusResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    @Inject
    private DatasetItemStatusRepository datasetItemStatusRepository;

    private MockMvc restDatasetItemStatusMockMvc;

    private DatasetItemStatus datasetItemStatus;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DatasetItemStatusResource datasetItemStatusResource = new DatasetItemStatusResource();
        ReflectionTestUtils.setField(datasetItemStatusResource, "datasetItemStatusRepository", datasetItemStatusRepository);
        this.restDatasetItemStatusMockMvc = MockMvcBuilders.standaloneSetup(datasetItemStatusResource).build();
    }

    @Before
    public void initTest() {
        datasetItemStatus = new DatasetItemStatus();
        datasetItemStatus.setName(DEFAULT_NAME);
        datasetItemStatus.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDatasetItemStatus() throws Exception {
        // Validate the database is empty
        assertThat(datasetItemStatusRepository.findAll()).hasSize(0);

        // Create the DatasetItemStatus
        restDatasetItemStatusMockMvc.perform(post("/api/datasetItemStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(datasetItemStatus)))
                .andExpect(status().isOk());

        // Validate the DatasetItemStatus in the database
        List<DatasetItemStatus> datasetItemStatuss = datasetItemStatusRepository.findAll();
        assertThat(datasetItemStatuss).hasSize(1);
        DatasetItemStatus testDatasetItemStatus = datasetItemStatuss.iterator().next();
        assertThat(testDatasetItemStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDatasetItemStatus.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDatasetItemStatuss() throws Exception {
        // Initialize the database
        datasetItemStatusRepository.saveAndFlush(datasetItemStatus);

        // Get all the datasetItemStatuss
        restDatasetItemStatusMockMvc.perform(get("/api/datasetItemStatuss"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(datasetItemStatus.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getDatasetItemStatus() throws Exception {
        // Initialize the database
        datasetItemStatusRepository.saveAndFlush(datasetItemStatus);

        // Get the datasetItemStatus
        restDatasetItemStatusMockMvc.perform(get("/api/datasetItemStatuss/{id}", datasetItemStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(datasetItemStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDatasetItemStatus() throws Exception {
        // Get the datasetItemStatus
        restDatasetItemStatusMockMvc.perform(get("/api/datasetItemStatuss/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDatasetItemStatus() throws Exception {
        // Initialize the database
        datasetItemStatusRepository.saveAndFlush(datasetItemStatus);

        // Update the datasetItemStatus
        datasetItemStatus.setName(UPDATED_NAME);
        datasetItemStatus.setDescription(UPDATED_DESCRIPTION);
        restDatasetItemStatusMockMvc.perform(post("/api/datasetItemStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(datasetItemStatus)))
                .andExpect(status().isOk());

        // Validate the DatasetItemStatus in the database
        List<DatasetItemStatus> datasetItemStatuss = datasetItemStatusRepository.findAll();
        assertThat(datasetItemStatuss).hasSize(1);
        DatasetItemStatus testDatasetItemStatus = datasetItemStatuss.iterator().next();
        assertThat(testDatasetItemStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDatasetItemStatus.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteDatasetItemStatus() throws Exception {
        // Initialize the database
        datasetItemStatusRepository.saveAndFlush(datasetItemStatus);

        // Get the datasetItemStatus
        restDatasetItemStatusMockMvc.perform(delete("/api/datasetItemStatuss/{id}", datasetItemStatus.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DatasetItemStatus> datasetItemStatuss = datasetItemStatusRepository.findAll();
        assertThat(datasetItemStatuss).hasSize(0);
    }
}
