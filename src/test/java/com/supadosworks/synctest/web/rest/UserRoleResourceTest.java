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
import com.supadosworks.synctest.domain.UserRole;
import com.supadosworks.synctest.repository.UserRoleRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserRoleResource REST controller.
 *
 * @see UserRoleResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UserRoleResourceTest {

    private static final String DEFAULT_ROLE_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_ROLE_NAME = "UPDATED_TEXT";

    private static final Integer DEFAULT_PARENT_ID = 0;
    private static final Integer UPDATED_PARENT_ID = 1;
    private static final String DEFAULT_ROLE_DESC = "SAMPLE_TEXT";
    private static final String UPDATED_ROLE_DESC = "UPDATED_TEXT";

    @Inject
    private UserRoleRepository userRoleRepository;

    private MockMvc restUserRoleMockMvc;

    private UserRole userRole;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserRoleResource userRoleResource = new UserRoleResource();
        ReflectionTestUtils.setField(userRoleResource, "userRoleRepository", userRoleRepository);
        this.restUserRoleMockMvc = MockMvcBuilders.standaloneSetup(userRoleResource).build();
    }

    @Before
    public void initTest() {
        userRole = new UserRole();
        userRole.setRole_name(DEFAULT_ROLE_NAME);
        userRole.setParent_id(DEFAULT_PARENT_ID);
        userRole.setRole_desc(DEFAULT_ROLE_DESC);
    }

    @Test
    @Transactional
    public void createUserRole() throws Exception {
        // Validate the database is empty
        assertThat(userRoleRepository.findAll()).hasSize(0);

        // Create the UserRole
        restUserRoleMockMvc.perform(post("/api/userRoles")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userRole)))
                .andExpect(status().isOk());

        // Validate the UserRole in the database
        List<UserRole> userRoles = userRoleRepository.findAll();
        assertThat(userRoles).hasSize(1);
        UserRole testUserRole = userRoles.iterator().next();
        assertThat(testUserRole.getRole_name()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testUserRole.getParent_id()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testUserRole.getRole_desc()).isEqualTo(DEFAULT_ROLE_DESC);
    }

    @Test
    @Transactional
    public void getAllUserRoles() throws Exception {
        // Initialize the database
        userRoleRepository.saveAndFlush(userRole);

        // Get all the userRoles
        restUserRoleMockMvc.perform(get("/api/userRoles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(userRole.getId().intValue()))
                .andExpect(jsonPath("$.[0].role_name").value(DEFAULT_ROLE_NAME.toString()))
                .andExpect(jsonPath("$.[0].parent_id").value(DEFAULT_PARENT_ID))
                .andExpect(jsonPath("$.[0].role_desc").value(DEFAULT_ROLE_DESC.toString()));
    }

    @Test
    @Transactional
    public void getUserRole() throws Exception {
        // Initialize the database
        userRoleRepository.saveAndFlush(userRole);

        // Get the userRole
        restUserRoleMockMvc.perform(get("/api/userRoles/{id}", userRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(userRole.getId().intValue()))
            .andExpect(jsonPath("$.role_name").value(DEFAULT_ROLE_NAME.toString()))
            .andExpect(jsonPath("$.parent_id").value(DEFAULT_PARENT_ID))
            .andExpect(jsonPath("$.role_desc").value(DEFAULT_ROLE_DESC.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserRole() throws Exception {
        // Get the userRole
        restUserRoleMockMvc.perform(get("/api/userRoles/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserRole() throws Exception {
        // Initialize the database
        userRoleRepository.saveAndFlush(userRole);

        // Update the userRole
        userRole.setRole_name(UPDATED_ROLE_NAME);
        userRole.setParent_id(UPDATED_PARENT_ID);
        userRole.setRole_desc(UPDATED_ROLE_DESC);
        restUserRoleMockMvc.perform(post("/api/userRoles")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userRole)))
                .andExpect(status().isOk());

        // Validate the UserRole in the database
        List<UserRole> userRoles = userRoleRepository.findAll();
        assertThat(userRoles).hasSize(1);
        UserRole testUserRole = userRoles.iterator().next();
        assertThat(testUserRole.getRole_name()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testUserRole.getParent_id()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testUserRole.getRole_desc()).isEqualTo(UPDATED_ROLE_DESC);
    }

    @Test
    @Transactional
    public void deleteUserRole() throws Exception {
        // Initialize the database
        userRoleRepository.saveAndFlush(userRole);

        // Get the userRole
        restUserRoleMockMvc.perform(delete("/api/userRoles/{id}", userRole.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<UserRole> userRoles = userRoleRepository.findAll();
        assertThat(userRoles).hasSize(0);
    }
}
