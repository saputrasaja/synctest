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
import com.supadosworks.synctest.domain.UserAccount;
import com.supadosworks.synctest.repository.UserAccountRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserAccountResource REST controller.
 *
 * @see UserAccountResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UserAccountResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final String DEFAULT_USER_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_USER_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_PASSWD = "SAMPLE_TEXT";
    private static final String UPDATED_PASSWD = "UPDATED_TEXT";
    private static final String DEFAULT_FIRST_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_FIRST_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_LAST_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_LAST_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_EMAIL = "SAMPLE_TEXT";
    private static final String UPDATED_EMAIL = "UPDATED_TEXT";

    private static final Integer DEFAULT_ACTIVE_STUDY = 0;
    private static final Integer UPDATED_ACTIVE_STUDY = 1;
    private static final String DEFAULT_INSTITUTIONAL_AFFILIATION = "SAMPLE_TEXT";
    private static final String UPDATED_INSTITUTIONAL_AFFILIATION = "UPDATED_TEXT";

    private static final Integer DEFAULT_STATUS_ID = 0;
    private static final Integer UPDATED_STATUS_ID = 1;

    private static final Integer DEFAULT_OWNER_ID = 0;
    private static final Integer UPDATED_OWNER_ID = 1;

    private static final DateTime DEFAULT_DATE_CREATED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_CREATED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_CREATED_STR = dateTimeFormatter.print(DEFAULT_DATE_CREATED);

    private static final DateTime DEFAULT_DATE_UPDATED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_UPDATED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_UPDATED_STR = dateTimeFormatter.print(DEFAULT_DATE_UPDATED);

    private static final DateTime DEFAULT_DATE_LASTVISIT = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_DATE_LASTVISIT = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_DATE_LASTVISIT_STR = dateTimeFormatter.print(DEFAULT_DATE_LASTVISIT);

    private static final DateTime DEFAULT_PASSWD_TIMESTAMP = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_PASSWD_TIMESTAMP = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_PASSWD_TIMESTAMP_STR = dateTimeFormatter.print(DEFAULT_PASSWD_TIMESTAMP);
    private static final String DEFAULT_PASSWD_CHALLENGE_QUESTION = "SAMPLE_TEXT";
    private static final String UPDATED_PASSWD_CHALLENGE_QUESTION = "UPDATED_TEXT";
    private static final String DEFAULT_PASSWD_CHALLENGE_ANSWER = "SAMPLE_TEXT";
    private static final String UPDATED_PASSWD_CHALLENGE_ANSWER = "UPDATED_TEXT";
    private static final String DEFAULT_PHONE = "SAMPLE_TEXT";
    private static final String UPDATED_PHONE = "UPDATED_TEXT";

    private static final Integer DEFAULT_USER_TYPE_ID = 0;
    private static final Integer UPDATED_USER_TYPE_ID = 1;

    private static final Integer DEFAULT_UPDATE_ID = 0;
    private static final Integer UPDATED_UPDATE_ID = 1;

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final Boolean DEFAULT_ACCOUNT_NON_LOCKED = false;
    private static final Boolean UPDATED_ACCOUNT_NON_LOCKED = true;

    private static final Integer DEFAULT_LOCK_COUNTER = 0;
    private static final Integer UPDATED_LOCK_COUNTER = 1;

    private static final Boolean DEFAULT_RUN_WEBSERVICES = false;
    private static final Boolean UPDATED_RUN_WEBSERVICES = true;

    @Inject
    private UserAccountRepository userAccountRepository;

    private MockMvc restUserAccountMockMvc;

    private UserAccount userAccount;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserAccountResource userAccountResource = new UserAccountResource();
        ReflectionTestUtils.setField(userAccountResource, "userAccountRepository", userAccountRepository);
        this.restUserAccountMockMvc = MockMvcBuilders.standaloneSetup(userAccountResource).build();
    }

    @Before
    public void initTest() {
        userAccount = new UserAccount();
        userAccount.setUser_name(DEFAULT_USER_NAME);
        userAccount.setPasswd(DEFAULT_PASSWD);
        userAccount.setFirst_name(DEFAULT_FIRST_NAME);
        userAccount.setLast_name(DEFAULT_LAST_NAME);
        userAccount.setEmail(DEFAULT_EMAIL);
        userAccount.setActive_study(DEFAULT_ACTIVE_STUDY);
        userAccount.setInstitutional_affiliation(DEFAULT_INSTITUTIONAL_AFFILIATION);
        userAccount.setStatus_id(DEFAULT_STATUS_ID);
        userAccount.setOwner_id(DEFAULT_OWNER_ID);
        userAccount.setDate_created(DEFAULT_DATE_CREATED);
        userAccount.setDate_updated(DEFAULT_DATE_UPDATED);
        userAccount.setDate_lastvisit(DEFAULT_DATE_LASTVISIT);
        userAccount.setPasswd_timestamp(DEFAULT_PASSWD_TIMESTAMP);
        userAccount.setPasswd_challenge_question(DEFAULT_PASSWD_CHALLENGE_QUESTION);
        userAccount.setPasswd_challenge_answer(DEFAULT_PASSWD_CHALLENGE_ANSWER);
        userAccount.setPhone(DEFAULT_PHONE);
        userAccount.setUser_type_id(DEFAULT_USER_TYPE_ID);
        userAccount.setUpdate_id(DEFAULT_UPDATE_ID);
        userAccount.setEnabled(DEFAULT_ENABLED);
        userAccount.setAccount_non_locked(DEFAULT_ACCOUNT_NON_LOCKED);
        userAccount.setLock_counter(DEFAULT_LOCK_COUNTER);
        userAccount.setRun_webservices(DEFAULT_RUN_WEBSERVICES);
    }

    @Test
    @Transactional
    public void createUserAccount() throws Exception {
        // Validate the database is empty
        assertThat(userAccountRepository.findAll()).hasSize(0);

        // Create the UserAccount
        restUserAccountMockMvc.perform(post("/api/userAccounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userAccount)))
                .andExpect(status().isOk());

        // Validate the UserAccount in the database
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        assertThat(userAccounts).hasSize(1);
        UserAccount testUserAccount = userAccounts.iterator().next();
        assertThat(testUserAccount.getUser_name()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserAccount.getPasswd()).isEqualTo(DEFAULT_PASSWD);
        assertThat(testUserAccount.getFirst_name()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUserAccount.getLast_name()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserAccount.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserAccount.getActive_study()).isEqualTo(DEFAULT_ACTIVE_STUDY);
        assertThat(testUserAccount.getInstitutional_affiliation()).isEqualTo(DEFAULT_INSTITUTIONAL_AFFILIATION);
        assertThat(testUserAccount.getStatus_id()).isEqualTo(DEFAULT_STATUS_ID);
        assertThat(testUserAccount.getOwner_id()).isEqualTo(DEFAULT_OWNER_ID);
        assertThat(testUserAccount.getDate_created().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testUserAccount.getDate_updated().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_UPDATED);
        assertThat(testUserAccount.getDate_lastvisit().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_DATE_LASTVISIT);
        assertThat(testUserAccount.getPasswd_timestamp().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_PASSWD_TIMESTAMP);
        assertThat(testUserAccount.getPasswd_challenge_question()).isEqualTo(DEFAULT_PASSWD_CHALLENGE_QUESTION);
        assertThat(testUserAccount.getPasswd_challenge_answer()).isEqualTo(DEFAULT_PASSWD_CHALLENGE_ANSWER);
        assertThat(testUserAccount.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserAccount.getUser_type_id()).isEqualTo(DEFAULT_USER_TYPE_ID);
        assertThat(testUserAccount.getUpdate_id()).isEqualTo(DEFAULT_UPDATE_ID);
        assertThat(testUserAccount.getEnabled()).isEqualTo(DEFAULT_ENABLED);
        assertThat(testUserAccount.getAccount_non_locked()).isEqualTo(DEFAULT_ACCOUNT_NON_LOCKED);
        assertThat(testUserAccount.getLock_counter()).isEqualTo(DEFAULT_LOCK_COUNTER);
        assertThat(testUserAccount.getRun_webservices()).isEqualTo(DEFAULT_RUN_WEBSERVICES);
    }

    @Test
    @Transactional
    public void getAllUserAccounts() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        // Get all the userAccounts
        restUserAccountMockMvc.perform(get("/api/userAccounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(userAccount.getId().intValue()))
                .andExpect(jsonPath("$.[0].user_name").value(DEFAULT_USER_NAME.toString()))
                .andExpect(jsonPath("$.[0].passwd").value(DEFAULT_PASSWD.toString()))
                .andExpect(jsonPath("$.[0].first_name").value(DEFAULT_FIRST_NAME.toString()))
                .andExpect(jsonPath("$.[0].last_name").value(DEFAULT_LAST_NAME.toString()))
                .andExpect(jsonPath("$.[0].email").value(DEFAULT_EMAIL.toString()))
                .andExpect(jsonPath("$.[0].active_study").value(DEFAULT_ACTIVE_STUDY))
                .andExpect(jsonPath("$.[0].institutional_affiliation").value(DEFAULT_INSTITUTIONAL_AFFILIATION.toString()))
                .andExpect(jsonPath("$.[0].status_id").value(DEFAULT_STATUS_ID))
                .andExpect(jsonPath("$.[0].owner_id").value(DEFAULT_OWNER_ID))
                .andExpect(jsonPath("$.[0].date_created").value(DEFAULT_DATE_CREATED_STR))
                .andExpect(jsonPath("$.[0].date_updated").value(DEFAULT_DATE_UPDATED_STR))
                .andExpect(jsonPath("$.[0].date_lastvisit").value(DEFAULT_DATE_LASTVISIT_STR))
                .andExpect(jsonPath("$.[0].passwd_timestamp").value(DEFAULT_PASSWD_TIMESTAMP_STR))
                .andExpect(jsonPath("$.[0].passwd_challenge_question").value(DEFAULT_PASSWD_CHALLENGE_QUESTION.toString()))
                .andExpect(jsonPath("$.[0].passwd_challenge_answer").value(DEFAULT_PASSWD_CHALLENGE_ANSWER.toString()))
                .andExpect(jsonPath("$.[0].phone").value(DEFAULT_PHONE.toString()))
                .andExpect(jsonPath("$.[0].user_type_id").value(DEFAULT_USER_TYPE_ID))
                .andExpect(jsonPath("$.[0].update_id").value(DEFAULT_UPDATE_ID))
                .andExpect(jsonPath("$.[0].enabled").value(DEFAULT_ENABLED.booleanValue()))
                .andExpect(jsonPath("$.[0].account_non_locked").value(DEFAULT_ACCOUNT_NON_LOCKED.booleanValue()))
                .andExpect(jsonPath("$.[0].lock_counter").value(DEFAULT_LOCK_COUNTER))
                .andExpect(jsonPath("$.[0].run_webservices").value(DEFAULT_RUN_WEBSERVICES.booleanValue()));
    }

    @Test
    @Transactional
    public void getUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        // Get the userAccount
        restUserAccountMockMvc.perform(get("/api/userAccounts/{id}", userAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(userAccount.getId().intValue()))
            .andExpect(jsonPath("$.user_name").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.passwd").value(DEFAULT_PASSWD.toString()))
            .andExpect(jsonPath("$.first_name").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.last_name").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.active_study").value(DEFAULT_ACTIVE_STUDY))
            .andExpect(jsonPath("$.institutional_affiliation").value(DEFAULT_INSTITUTIONAL_AFFILIATION.toString()))
            .andExpect(jsonPath("$.status_id").value(DEFAULT_STATUS_ID))
            .andExpect(jsonPath("$.owner_id").value(DEFAULT_OWNER_ID))
            .andExpect(jsonPath("$.date_created").value(DEFAULT_DATE_CREATED_STR))
            .andExpect(jsonPath("$.date_updated").value(DEFAULT_DATE_UPDATED_STR))
            .andExpect(jsonPath("$.date_lastvisit").value(DEFAULT_DATE_LASTVISIT_STR))
            .andExpect(jsonPath("$.passwd_timestamp").value(DEFAULT_PASSWD_TIMESTAMP_STR))
            .andExpect(jsonPath("$.passwd_challenge_question").value(DEFAULT_PASSWD_CHALLENGE_QUESTION.toString()))
            .andExpect(jsonPath("$.passwd_challenge_answer").value(DEFAULT_PASSWD_CHALLENGE_ANSWER.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.user_type_id").value(DEFAULT_USER_TYPE_ID))
            .andExpect(jsonPath("$.update_id").value(DEFAULT_UPDATE_ID))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.account_non_locked").value(DEFAULT_ACCOUNT_NON_LOCKED.booleanValue()))
            .andExpect(jsonPath("$.lock_counter").value(DEFAULT_LOCK_COUNTER))
            .andExpect(jsonPath("$.run_webservices").value(DEFAULT_RUN_WEBSERVICES.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUserAccount() throws Exception {
        // Get the userAccount
        restUserAccountMockMvc.perform(get("/api/userAccounts/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        // Update the userAccount
        userAccount.setUser_name(UPDATED_USER_NAME);
        userAccount.setPasswd(UPDATED_PASSWD);
        userAccount.setFirst_name(UPDATED_FIRST_NAME);
        userAccount.setLast_name(UPDATED_LAST_NAME);
        userAccount.setEmail(UPDATED_EMAIL);
        userAccount.setActive_study(UPDATED_ACTIVE_STUDY);
        userAccount.setInstitutional_affiliation(UPDATED_INSTITUTIONAL_AFFILIATION);
        userAccount.setStatus_id(UPDATED_STATUS_ID);
        userAccount.setOwner_id(UPDATED_OWNER_ID);
        userAccount.setDate_created(UPDATED_DATE_CREATED);
        userAccount.setDate_updated(UPDATED_DATE_UPDATED);
        userAccount.setDate_lastvisit(UPDATED_DATE_LASTVISIT);
        userAccount.setPasswd_timestamp(UPDATED_PASSWD_TIMESTAMP);
        userAccount.setPasswd_challenge_question(UPDATED_PASSWD_CHALLENGE_QUESTION);
        userAccount.setPasswd_challenge_answer(UPDATED_PASSWD_CHALLENGE_ANSWER);
        userAccount.setPhone(UPDATED_PHONE);
        userAccount.setUser_type_id(UPDATED_USER_TYPE_ID);
        userAccount.setUpdate_id(UPDATED_UPDATE_ID);
        userAccount.setEnabled(UPDATED_ENABLED);
        userAccount.setAccount_non_locked(UPDATED_ACCOUNT_NON_LOCKED);
        userAccount.setLock_counter(UPDATED_LOCK_COUNTER);
        userAccount.setRun_webservices(UPDATED_RUN_WEBSERVICES);
        restUserAccountMockMvc.perform(post("/api/userAccounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userAccount)))
                .andExpect(status().isOk());

        // Validate the UserAccount in the database
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        assertThat(userAccounts).hasSize(1);
        UserAccount testUserAccount = userAccounts.iterator().next();
        assertThat(testUserAccount.getUser_name()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserAccount.getPasswd()).isEqualTo(UPDATED_PASSWD);
        assertThat(testUserAccount.getFirst_name()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserAccount.getLast_name()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserAccount.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserAccount.getActive_study()).isEqualTo(UPDATED_ACTIVE_STUDY);
        assertThat(testUserAccount.getInstitutional_affiliation()).isEqualTo(UPDATED_INSTITUTIONAL_AFFILIATION);
        assertThat(testUserAccount.getStatus_id()).isEqualTo(UPDATED_STATUS_ID);
        assertThat(testUserAccount.getOwner_id()).isEqualTo(UPDATED_OWNER_ID);
        assertThat(testUserAccount.getDate_created().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testUserAccount.getDate_updated().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_UPDATED);
        assertThat(testUserAccount.getDate_lastvisit().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_DATE_LASTVISIT);
        assertThat(testUserAccount.getPasswd_timestamp().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_PASSWD_TIMESTAMP);
        assertThat(testUserAccount.getPasswd_challenge_question()).isEqualTo(UPDATED_PASSWD_CHALLENGE_QUESTION);
        assertThat(testUserAccount.getPasswd_challenge_answer()).isEqualTo(UPDATED_PASSWD_CHALLENGE_ANSWER);
        assertThat(testUserAccount.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserAccount.getUser_type_id()).isEqualTo(UPDATED_USER_TYPE_ID);
        assertThat(testUserAccount.getUpdate_id()).isEqualTo(UPDATED_UPDATE_ID);
        assertThat(testUserAccount.getEnabled()).isEqualTo(UPDATED_ENABLED);
        assertThat(testUserAccount.getAccount_non_locked()).isEqualTo(UPDATED_ACCOUNT_NON_LOCKED);
        assertThat(testUserAccount.getLock_counter()).isEqualTo(UPDATED_LOCK_COUNTER);
        assertThat(testUserAccount.getRun_webservices()).isEqualTo(UPDATED_RUN_WEBSERVICES);
    }

    @Test
    @Transactional
    public void deleteUserAccount() throws Exception {
        // Initialize the database
        userAccountRepository.saveAndFlush(userAccount);

        // Get the userAccount
        restUserAccountMockMvc.perform(delete("/api/userAccounts/{id}", userAccount.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        assertThat(userAccounts).hasSize(0);
    }
}
