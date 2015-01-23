package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the UserAccount entity.
 */
public interface UserAccountRepository extends JpaRepository<UserAccount,Long>{

}
