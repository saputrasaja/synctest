package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.ExportFormat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ExportFormat entity.
 */
public interface ExportFormatRepository extends JpaRepository<ExportFormat,Long>{

}
