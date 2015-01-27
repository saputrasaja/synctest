package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the UserRole entity.
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long>{

}
