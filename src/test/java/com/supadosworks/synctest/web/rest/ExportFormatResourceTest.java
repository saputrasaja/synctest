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
import com.supadosworks.synctest.domain.ExportFormat;
import com.supadosworks.synctest.repository.ExportFormatRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ExportFormatResource REST controller.
 *
 * @see ExportFormatResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ExportFormatResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";
    private static final String DEFAULT_MIME_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_MIME_TYPE = "UPDATED_TEXT";

    @Inject
    private ExportFormatRepository exportFormatRepository;

    private MockMvc restExportFormatMockMvc;

    private ExportFormat exportFormat;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExportFormatResource exportFormatResource = new ExportFormatResource();
        ReflectionTestUtils.setField(exportFormatResource, "exportFormatRepository", exportFormatRepository);
        this.restExportFormatMockMvc = MockMvcBuilders.standaloneSetup(exportFormatResource).build();
    }

    @Before
    public void initTest() {
        exportFormat = new ExportFormat();
        exportFormat.setName(DEFAULT_NAME);
        exportFormat.setDescription(DEFAULT_DESCRIPTION);
        exportFormat.setMime_type(DEFAULT_MIME_TYPE);
    }

    @Test
    @Transactional
    public void createExportFormat() throws Exception {
        // Validate the database is empty
        assertThat(exportFormatRepository.findAll()).hasSize(0);

        // Create the ExportFormat
        restExportFormatMockMvc.perform(post("/api/exportFormats")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(exportFormat)))
                .andExpect(status().isOk());

        // Validate the ExportFormat in the database
        List<ExportFormat> exportFormats = exportFormatRepository.findAll();
        assertThat(exportFormats).hasSize(1);
        ExportFormat testExportFormat = exportFormats.iterator().next();
        assertThat(testExportFormat.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testExportFormat.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testExportFormat.getMime_type()).isEqualTo(DEFAULT_MIME_TYPE);
    }

    @Test
    @Transactional
    public void getAllExportFormats() throws Exception {
        // Initialize the database
        exportFormatRepository.saveAndFlush(exportFormat);

        // Get all the exportFormats
        restExportFormatMockMvc.perform(get("/api/exportFormats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(exportFormat.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.[0].mime_type").value(DEFAULT_MIME_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getExportFormat() throws Exception {
        // Initialize the database
        exportFormatRepository.saveAndFlush(exportFormat);

        // Get the exportFormat
        restExportFormatMockMvc.perform(get("/api/exportFormats/{id}", exportFormat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(exportFormat.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.mime_type").value(DEFAULT_MIME_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExportFormat() throws Exception {
        // Get the exportFormat
        restExportFormatMockMvc.perform(get("/api/exportFormats/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExportFormat() throws Exception {
        // Initialize the database
        exportFormatRepository.saveAndFlush(exportFormat);

        // Update the exportFormat
        exportFormat.setName(UPDATED_NAME);
        exportFormat.setDescription(UPDATED_DESCRIPTION);
        exportFormat.setMime_type(UPDATED_MIME_TYPE);
        restExportFormatMockMvc.perform(post("/api/exportFormats")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(exportFormat)))
                .andExpect(status().isOk());

        // Validate the ExportFormat in the database
        List<ExportFormat> exportFormats = exportFormatRepository.findAll();
        assertThat(exportFormats).hasSize(1);
        ExportFormat testExportFormat = exportFormats.iterator().next();
        assertThat(testExportFormat.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testExportFormat.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testExportFormat.getMime_type()).isEqualTo(UPDATED_MIME_TYPE);
    }

    @Test
    @Transactional
    public void deleteExportFormat() throws Exception {
        // Initialize the database
        exportFormatRepository.saveAndFlush(exportFormat);

        // Get the exportFormat
        restExportFormatMockMvc.perform(delete("/api/exportFormats/{id}", exportFormat.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ExportFormat> exportFormats = exportFormatRepository.findAll();
        assertThat(exportFormats).hasSize(0);
    }
}
