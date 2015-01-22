package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the UserType entity.
 */
public interface UserTypeRepository extends JpaRepository<UserType,Long>{

}
