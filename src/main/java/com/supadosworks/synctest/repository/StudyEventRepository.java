package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.StudyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the StudyEvent entity.
 */
public interface StudyEventRepository extends JpaRepository<StudyEvent,Long>{

}
