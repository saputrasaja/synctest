package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the StudyType entity.
 */
public interface StudyTypeRepository extends JpaRepository<StudyType,Long>{

}
