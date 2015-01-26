package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Dataset entity.
 */
public interface DatasetRepository extends JpaRepository<Dataset,Long>{

}
