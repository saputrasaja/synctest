package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.DatasetItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the DatasetItemStatus entity.
 */
public interface DatasetItemStatusRepository extends JpaRepository<DatasetItemStatus,Long>{

}
