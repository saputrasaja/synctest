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
import com.supadosworks.synctest.domain.PhoneBook;
import com.supadosworks.synctest.repository.PhoneBookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PhoneBookResource REST controller.
 *
 * @see PhoneBookResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PhoneBookResourceTest {

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_PHONE_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_PHONE_NUMBER = "UPDATED_TEXT";
    private static final String DEFAULT_ADDRESS = "SAMPLE_TEXT";
    private static final String UPDATED_ADDRESS = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";

    @Inject
    private PhoneBookRepository phoneBookRepository;

    private MockMvc restPhoneBookMockMvc;

    private PhoneBook phoneBook;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PhoneBookResource phoneBookResource = new PhoneBookResource();
        ReflectionTestUtils.setField(phoneBookResource, "phoneBookRepository", phoneBookRepository);
        this.restPhoneBookMockMvc = MockMvcBuilders.standaloneSetup(phoneBookResource).build();
    }

    @Before
    public void initTest() {
        phoneBook = new PhoneBook();
        phoneBook.setName(DEFAULT_NAME);
        phoneBook.setPhone_number(DEFAULT_PHONE_NUMBER);
        phoneBook.setAddress(DEFAULT_ADDRESS);
        phoneBook.setEmail(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createPhoneBook() throws Exception {
        // Validate the database is empty
        assertThat(phoneBookRepository.findAll()).hasSize(0);

        // Create the PhoneBook
        restPhoneBookMockMvc.perform(post("/api/phoneBooks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(phoneBook)))
                .andExpect(status().isOk());

        // Validate the PhoneBook in the database
        List<PhoneBook> phoneBooks = phoneBookRepository.findAll();
        assertThat(phoneBooks).hasSize(1);
        PhoneBook testPhoneBook = phoneBooks.iterator().next();
        assertThat(testPhoneBook.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPhoneBook.getPhone_number()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testPhoneBook.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPhoneBook.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPhoneBooks() throws Exception {
        // Initialize the database
        phoneBookRepository.saveAndFlush(phoneBook);

        // Get all the phoneBooks
        restPhoneBookMockMvc.perform(get("/api/phoneBooks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(phoneBook.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].phone_number").value(DEFAULT_PHONE_NUMBER.toString()))
                .andExpect(jsonPath("$.[0].address").value(DEFAULT_ADDRESS.toString()))
                .andExpect(jsonPath("$.[0].email").value(DEFAULT_EMAIL.toString()));
    }

    @Test
    @Transactional
    public void getPhoneBook() throws Exception {
        // Initialize the database
        phoneBookRepository.saveAndFlush(phoneBook);

        // Get the phoneBook
        restPhoneBookMockMvc.perform(get("/api/phoneBooks/{id}", phoneBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(phoneBook.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.phone_number").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPhoneBook() throws Exception {
        // Get the phoneBook
        restPhoneBookMockMvc.perform(get("/api/phoneBooks/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhoneBook() throws Exception {
        // Initialize the database
        phoneBookRepository.saveAndFlush(phoneBook);

        // Update the phoneBook
        phoneBook.setName(UPDATED_NAME);
        phoneBook.setPhone_number(UPDATED_PHONE_NUMBER);
        phoneBook.setAddress(UPDATED_ADDRESS);
        phoneBook.setEmail(UPDATED_EMAIL);
        restPhoneBookMockMvc.perform(post("/api/phoneBooks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(phoneBook)))
                .andExpect(status().isOk());

        // Validate the PhoneBook in the database
        List<PhoneBook> phoneBooks = phoneBookRepository.findAll();
        assertThat(phoneBooks).hasSize(1);
        PhoneBook testPhoneBook = phoneBooks.iterator().next();
        assertThat(testPhoneBook.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPhoneBook.getPhone_number()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testPhoneBook.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPhoneBook.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void deletePhoneBook() throws Exception {
        // Initialize the database
        phoneBookRepository.saveAndFlush(phoneBook);

        // Get the phoneBook
        restPhoneBookMockMvc.perform(delete("/api/phoneBooks/{id}", phoneBook.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PhoneBook> phoneBooks = phoneBookRepository.findAll();
        assertThat(phoneBooks).hasSize(0);
    }
}
