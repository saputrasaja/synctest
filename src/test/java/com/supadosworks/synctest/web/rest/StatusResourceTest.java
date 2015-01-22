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
import com.supadosworks.synctest.domain.Status;
import com.supadosworks.synctest.repository.StatusRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StatusResource REST controller.
 *
 * @see StatusResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StatusResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    @Inject
    private StatusRepository statusRepository;

    private MockMvc restStatusMockMvc;

    private Status status;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StatusResource statusResource = new StatusResource();
        ReflectionTestUtils.setField(statusResource, "statusRepository", statusRepository);
        this.restStatusMockMvc = MockMvcBuilders.standaloneSetup(statusResource).build();
    }

    @Before
    public void initTest() {
        status = new Status();
        status.setName(DEFAULT_NAME);
        status.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createStatus() throws Exception {
        // Validate the database is empty
        assertThat(statusRepository.findAll()).hasSize(0);

        // Create the Status
        restStatusMockMvc.perform(post("/api/statuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(status)))
                .andExpect(status().isOk());

        // Validate the Status in the database
        List<Status> statuss = statusRepository.findAll();
        assertThat(statuss).hasSize(1);
        Status testStatus = statuss.iterator().next();
        assertThat(testStatus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStatus.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllStatuss() throws Exception {
        // Initialize the database
        statusRepository.saveAndFlush(status);

        // Get all the statuss
        restStatusMockMvc.perform(get("/api/statuss"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(status.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getStatus() throws Exception {
        // Initialize the database
        statusRepository.saveAndFlush(status);

        // Get the status
        restStatusMockMvc.perform(get("/api/statuss/{id}", status.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(status.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStatus() throws Exception {
        // Get the status
        restStatusMockMvc.perform(get("/api/statuss/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatus() throws Exception {
        // Initialize the database
        statusRepository.saveAndFlush(status);

        // Update the status
        status.setName(UPDATED_NAME);
        status.setDescription(UPDATED_DESCRIPTION);
        restStatusMockMvc.perform(post("/api/statuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(status)))
                .andExpect(status().isOk());

        // Validate the Status in the database
        List<Status> statuss = statusRepository.findAll();
        assertThat(statuss).hasSize(1);
        Status testStatus = statuss.iterator().next();
        assertThat(testStatus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStatus.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteStatus() throws Exception {
        // Initialize the database
        statusRepository.saveAndFlush(status);

        // Get the status
        restStatusMockMvc.perform(delete("/api/statuss/{id}", status.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Status> statuss = statusRepository.findAll();
        assertThat(statuss).hasSize(0);
    }
}
