package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Status entity.
 */
public interface StatusRepository extends JpaRepository<Status,Long>{

}
