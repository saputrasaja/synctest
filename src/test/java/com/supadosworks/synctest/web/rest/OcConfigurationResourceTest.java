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
import com.supadosworks.synctest.domain.OcConfiguration;
import com.supadosworks.synctest.repository.OcConfigurationRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OcConfigurationResource REST controller.
 *
 * @see OcConfigurationResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OcConfigurationResourceTest {

    private static final String DEFAULT_KEY = "SAMPLE_TEXT";
    private static final String UPDATED_KEY = "UPDATED_TEXT";
    private static final String DEFAULT_VALUE = "SAMPLE_TEXT";
    private static final String UPDATED_VALUE = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    private static final Integer DEFAULT_VERSION = 0;
    private static final Integer UPDATED_VERSION = 1;

    @Inject
    private OcConfigurationRepository ocConfigurationRepository;

    private MockMvc restOcConfigurationMockMvc;

    private OcConfiguration ocConfiguration;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OcConfigurationResource ocConfigurationResource = new OcConfigurationResource();
        ReflectionTestUtils.setField(ocConfigurationResource, "ocConfigurationRepository", ocConfigurationRepository);
        this.restOcConfigurationMockMvc = MockMvcBuilders.standaloneSetup(ocConfigurationResource).build();
    }

    @Before
    public void initTest() {
        ocConfiguration = new OcConfiguration();
        ocConfiguration.setKey(DEFAULT_KEY);
        ocConfiguration.setValue(DEFAULT_VALUE);
        ocConfiguration.setDescription(DEFAULT_DESCRIPTION);
        ocConfiguration.setVersion(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void createOcConfiguration() throws Exception {
        // Validate the database is empty
        assertThat(ocConfigurationRepository.findAll()).hasSize(0);

        // Create the OcConfiguration
        restOcConfigurationMockMvc.perform(post("/api/ocConfigurations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ocConfiguration)))
                .andExpect(status().isOk());

        // Validate the OcConfiguration in the database
        List<OcConfiguration> ocConfigurations = ocConfigurationRepository.findAll();
        assertThat(ocConfigurations).hasSize(1);
        OcConfiguration testOcConfiguration = ocConfigurations.iterator().next();
        assertThat(testOcConfiguration.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testOcConfiguration.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testOcConfiguration.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOcConfiguration.getVersion()).isEqualTo(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void getAllOcConfigurations() throws Exception {
        // Initialize the database
        ocConfigurationRepository.saveAndFlush(ocConfiguration);

        // Get all the ocConfigurations
        restOcConfigurationMockMvc.perform(get("/api/ocConfigurations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(ocConfiguration.getId().intValue()))
                .andExpect(jsonPath("$.[0].key").value(DEFAULT_KEY.toString()))
                .andExpect(jsonPath("$.[0].value").value(DEFAULT_VALUE.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].version").value(DEFAULT_VERSION));
    }

    @Test
    @Transactional
    public void getOcConfiguration() throws Exception {
        // Initialize the database
        ocConfigurationRepository.saveAndFlush(ocConfiguration);

        // Get the ocConfiguration
        restOcConfigurationMockMvc.perform(get("/api/ocConfigurations/{id}", ocConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(ocConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }

    @Test
    @Transactional
    public void getNonExistingOcConfiguration() throws Exception {
        // Get the ocConfiguration
        restOcConfigurationMockMvc.perform(get("/api/ocConfigurations/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOcConfiguration() throws Exception {
        // Initialize the database
        ocConfigurationRepository.saveAndFlush(ocConfiguration);

        // Update the ocConfiguration
        ocConfiguration.setKey(UPDATED_KEY);
        ocConfiguration.setValue(UPDATED_VALUE);
        ocConfiguration.setDescription(UPDATED_DESCRIPTION);
        ocConfiguration.setVersion(UPDATED_VERSION);
        restOcConfigurationMockMvc.perform(post("/api/ocConfigurations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ocConfiguration)))
                .andExpect(status().isOk());

        // Validate the OcConfiguration in the database
        List<OcConfiguration> ocConfigurations = ocConfigurationRepository.findAll();
        assertThat(ocConfigurations).hasSize(1);
        OcConfiguration testOcConfiguration = ocConfigurations.iterator().next();
        assertThat(testOcConfiguration.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testOcConfiguration.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testOcConfiguration.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOcConfiguration.getVersion()).isEqualTo(UPDATED_VERSION);
    }

    @Test
    @Transactional
    public void deleteOcConfiguration() throws Exception {
        // Initialize the database
        ocConfigurationRepository.saveAndFlush(ocConfiguration);

        // Get the ocConfiguration
        restOcConfigurationMockMvc.perform(delete("/api/ocConfigurations/{id}", ocConfiguration.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OcConfiguration> ocConfigurations = ocConfigurationRepository.findAll();
        assertThat(ocConfigurations).hasSize(0);
    }
}
