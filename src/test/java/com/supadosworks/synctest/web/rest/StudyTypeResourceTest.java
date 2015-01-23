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
import com.supadosworks.synctest.domain.StudyType;
import com.supadosworks.synctest.repository.StudyTypeRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StudyTypeResource REST controller.
 *
 * @see StudyTypeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StudyTypeResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";

    @Inject
    private StudyTypeRepository studyTypeRepository;

    private MockMvc restStudyTypeMockMvc;

    private StudyType studyType;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StudyTypeResource studyTypeResource = new StudyTypeResource();
        ReflectionTestUtils.setField(studyTypeResource, "studyTypeRepository", studyTypeRepository);
        this.restStudyTypeMockMvc = MockMvcBuilders.standaloneSetup(studyTypeResource).build();
    }

    @Before
    public void initTest() {
        studyType = new StudyType();
        studyType.setName(DEFAULT_NAME);
        studyType.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createStudyType() throws Exception {
        // Validate the database is empty
        assertThat(studyTypeRepository.findAll()).hasSize(0);

        // Create the StudyType
        restStudyTypeMockMvc.perform(post("/api/studyTypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(studyType)))
                .andExpect(status().isOk());

        // Validate the StudyType in the database
        List<StudyType> studyTypes = studyTypeRepository.findAll();
        assertThat(studyTypes).hasSize(1);
        StudyType testStudyType = studyTypes.iterator().next();
        assertThat(testStudyType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudyType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllStudyTypes() throws Exception {
        // Initialize the database
        studyTypeRepository.saveAndFlush(studyType);

        // Get all the studyTypes
        restStudyTypeMockMvc.perform(get("/api/studyTypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(studyType.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getStudyType() throws Exception {
        // Initialize the database
        studyTypeRepository.saveAndFlush(studyType);

        // Get the studyType
        restStudyTypeMockMvc.perform(get("/api/studyTypes/{id}", studyType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(studyType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStudyType() throws Exception {
        // Get the studyType
        restStudyTypeMockMvc.perform(get("/api/studyTypes/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudyType() throws Exception {
        // Initialize the database
        studyTypeRepository.saveAndFlush(studyType);

        // Update the studyType
        studyType.setName(UPDATED_NAME);
        studyType.setDescription(UPDATED_DESCRIPTION);
        restStudyTypeMockMvc.perform(post("/api/studyTypes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(studyType)))
                .andExpect(status().isOk());

        // Validate the StudyType in the database
        List<StudyType> studyTypes = studyTypeRepository.findAll();
        assertThat(studyTypes).hasSize(1);
        StudyType testStudyType = studyTypes.iterator().next();
        assertThat(testStudyType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudyType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteStudyType() throws Exception {
        // Initialize the database
        studyTypeRepository.saveAndFlush(studyType);

        // Get the studyType
        restStudyTypeMockMvc.perform(delete("/api/studyTypes/{id}", studyType.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<StudyType> studyTypes = studyTypeRepository.findAll();
        assertThat(studyTypes).hasSize(0);
    }
}
